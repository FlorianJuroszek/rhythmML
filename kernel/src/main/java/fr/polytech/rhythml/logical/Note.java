package fr.polytech.rhythml.logical;

import fr.polytech.rhythml.NamedElement;
import fr.polytech.rhythml.generator.Visitable;
import fr.polytech.rhythml.generator.Visitor;

public class Note implements NamedElement, Visitable {
    private String name;
    private NotePosition beat;
    private NotePosition quaver;
    private NotePosition semiquaver;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public NotePosition getBeat() {
        return beat;
    }

    public void setBeat(NotePosition beat) {
        this.beat = beat;
    }

    public NotePosition getQuaver() {
        return quaver;
    }

    public void setQuaver(NotePosition quaver) {
        this.quaver = quaver;
    }

    public NotePosition getSemiquaver() {
        return semiquaver;
    }

    public void setSemiquaver(NotePosition semiquaver) {
        this.semiquaver = semiquaver;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
