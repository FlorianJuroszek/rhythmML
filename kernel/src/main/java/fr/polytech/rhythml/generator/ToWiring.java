package fr.polytech.rhythml.generator;

import fr.polytech.rhythml.Song;
import fr.polytech.rhythml.logical.*;
import fr.polytech.rhythml.logical.Instrument;
import fr.polytech.rhythml.logical.Note;
import fr.polytech.rhythml.logical.Track;
import org.jfugue.Pattern;
import org.jfugue.Player;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Visitor generating midi file content
 */
public class ToWiring extends Visitor<StringBuffer> {

    private Player player = new Player();

    private int currentTrack = 0;

    /**
     * Key : name of the pattern given by the user
     * Value : the JFugue Pattern object
     */
    private List<Pattern> patternList = new ArrayList<>();

    private Song song;

    public ToWiring() {
        this.result = new StringBuffer();
    }

    public void visit(final Song song) {
        this.result = new StringBuffer();
        this.song = song;
        // First step visit tracks
        for (Track track : song.getTracks()) {
            track.accept(this);
        }

        // Second step build patterns according to visited components
        Pattern songPattern = new Pattern();
        for (Pattern pattern : patternList) {
            songPattern.add(pattern);
        }

        // Last step play pattern and generate midi file
        try {
            Synthesizer synth = MidiSystem.getSynthesizer(); synth.open();
            ClassLoader classLoader = getClass().getClassLoader();
            Soundbank soundbank = MidiSystem.getSoundbank( new File(classLoader.getResource("gm.sf2").getFile()));
            synth.loadAllInstruments(soundbank);

            this.player = new Player(synth);
            player.saveMidi(songPattern, new File(String.format("%s.midi", song.getName())));
            player.play(songPattern);

            synth.close();
        } catch (IOException | NullPointerException | MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Track track) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(
                String.format("T%d ", song.getTempo())
        );
        // For now we consider one pattern by track
        // Voice value will be set to 9 if the instrument is Drums
        if (track.getInstrument().equals(Instrument.Drums)) {
            stringBuilder.append(
                    String.format("V%d ", 9)
            );
        } else {
            int nextTrack = currentTrack == 8 ? currentTrack + 2 : currentTrack + 1;

            if (nextTrack > 15) {
                System.out.println("Error (too many channels defined)");
            }

            stringBuilder.append(
                    String.format("V%d ", nextTrack)
            );
        }

        // TODO move this logic in instrument and channel by counter
        if (!track.getInstrument().equals(Instrument.Drums)) {
            stringBuilder.append(
                    String.format("I%d ", track.getInstrument().value)
            );
        }

        for (Section section : track.getSections()) {
            for (Note note : section.getNotes()) {
                if (note.getRepetition() != null) {
                    for (int i = 0; i < note.getRepetition(); i++) {
                        if (track.getInstrument().equals(Instrument.Drums)) {
                            stringBuilder.append(
                                    String.format("%s" + "i ", note.getName())
                            );
                        } else {
                            stringBuilder.append(
                                    String.format("%s ", note.getName())
                            );
                        }
                    }
                } else {
                    if (track.getInstrument().equals(Instrument.Drums)) {
                        stringBuilder.append(
                                String.format("%s" + "i ", note.getName())
                        );
                    } else {
                        stringBuilder.append(
                                String.format("%s ", note.getName())
                        );
                    }
                }
            }
        }

        // MOCK
        stringBuilder.append(String.format("%d", patternList.size() + 1));
        System.out.println(stringBuilder.toString());
        patternList.add(new Pattern(stringBuilder.toString()));
    }

    private void write(final String s) {
        this.result.append(String.format("%s\n", s));
    }

}
