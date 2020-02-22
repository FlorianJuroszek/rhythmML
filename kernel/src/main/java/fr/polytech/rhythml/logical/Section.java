package fr.polytech.rhythml.logical;

import fr.polytech.rhythml.NamedElement;
import fr.polytech.rhythml.generator.Visitable;
import fr.polytech.rhythml.generator.Visitor;

import java.util.List;

/**
 * Musical section model
 */
public class Section implements NamedElement, Visitable {
    private String name;
    private List<Note> notes;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
