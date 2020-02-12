package fr.polytech.rhythml.generator;

import fr.polytech.rhythml.Song;

/**
 * Visitor generating midi file content
 */
public class ToWiring extends Visitor<StringBuffer> {

    public void visit(final Song song) {
        this.result = new StringBuffer();
    }

    private void write(final String s) {
        this.result.append(String.format("%s\n", s));
    }
}
