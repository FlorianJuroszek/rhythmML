package fr.polytech.rhythml.logical;

import java.util.List;

/**
 * Musical section model
 */
public class Section {
    private String name;
    private List<Note> notes;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
