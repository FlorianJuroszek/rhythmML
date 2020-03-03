package fr.polytech.rhythml.generator;

import fr.polytech.rhythml.Song;
import fr.polytech.rhythml.logical.*;
import fr.polytech.rhythml.logical.Note;
import org.jfugue.Pattern;
import org.jfugue.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Visitor generating midi file content
 */
public class ToWiring extends Visitor<StringBuffer> {

    private final Player player = new Player();

    /**
     * Key : name of the pattern given by the user
     * Value : the JFugue Pattern object
     */
    private List<Pattern> patternList = new ArrayList<>();

    public ToWiring() {
        this.result = new StringBuffer();
    }

    public void visit(final Song song) {
        this.result = new StringBuffer();

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
            player.play(songPattern);
            player.saveMidi(songPattern, new File(String.format("%s.midi", song.getName())));
            System.out.println(songPattern.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Track track) {
        StringBuilder stringBuilder = new StringBuilder();

        // For now we consider one pattern by track
        // Voice value will be set to 9 if the instrument is Drums
        stringBuilder.append(
                String.format("V%d ", track.getInstrument().value)
        );

        for (Section section : track.getSections()) {
            for (Note note : section.getNotes()) {
                if (note.getRepetition() != null) {
                    for (int i = 0; i < note.getRepetition(); i++) {
                        stringBuilder.append(
                                String.format("%s ", note.getName())
                        );
                    }
                } else {
                    stringBuilder.append(
                            String.format("%s ", note.getName())
                    );
                }
            }
        }

        // MOCK
        stringBuilder.append(String.format("%d", patternList.size() + 1));
        patternList.add(new Pattern(stringBuilder.toString()));
    }

    private void write(final String s) {
        this.result.append(String.format("%s\n", s));
    }

}
