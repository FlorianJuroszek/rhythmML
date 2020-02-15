package fr.polytech.rhythml.generator;

import fr.polytech.rhythml.Song;
import fr.polytech.rhythml.logical.Bar;
import fr.polytech.rhythml.logical.Note;
import fr.polytech.rhythml.logical.Section;
import fr.polytech.rhythml.logical.Track;

/**
 * Visitor generating midi file content
 */
public class ToWiring extends Visitor<StringBuffer> {

    public ToWiring() {
        this.result = new StringBuffer();
    }

    private void writeLine(String s) {
        result.append(String.format("%s\n", s));
    }

    public void visit(final Song song) {
        this.result = new StringBuffer();
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
    public void visit(Track track) {

    }

    private void write(final String s) {
        this.result.append(String.format("%s\n", s));
    }
}
