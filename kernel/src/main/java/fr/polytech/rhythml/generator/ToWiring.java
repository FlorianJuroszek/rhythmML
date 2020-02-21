package fr.polytech.rhythml.generator;

import fr.polytech.rhythml.Song;
import fr.polytech.rhythml.logical.*;

import fr.polytech.rhythml.logical.Note;
import org.jfugue.*;

import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
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
    public void visit(Bar bar) {

    }

    @Override
    public void visit(Note note) {

    }

    @Override
    public void visit(Section section) {

    }

    @Override
    public void visit(NotePosition notePosition) {
        //
    }

    @Override
    public void visit(Track track) {
        StringBuilder stringBuilder = new StringBuilder();

        // For now we consider one pattern by track
        stringBuilder.append(
                String.format("V%d ", patternList.size()+1)
        );

        for (Bar bar : track.getBars().keySet()) {
            // Dirty version
            for (Note note : bar.getNotes()) {
                stringBuilder.append(
                        String.format("%s%s ", note.getName(), this.getNoteDuration(note))
                );
            }

            // Clean version
            bar.accept(this);
        }

        // MOCK
        stringBuilder.append(String.format("%d", patternList.size()+1));
        patternList.add(new Pattern(stringBuilder.toString()));
    }

    private String getNoteDuration(Note note) {
        return "q";
    }

    private void write(final String s) {
        this.result.append(String.format("%s\n", s));
    }

}
