package fr.polytech.rhythml.logical;

import fr.polytech.rhythml.NamedElement;
import fr.polytech.rhythml.generator.Visitable;
import fr.polytech.rhythml.generator.Visitor;

public class Note implements NamedElement, Visitable {
    private String name;
    private int beat;
    private int quaver;
    private int semiquaver;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getBeat() {
        return beat;
    }

    public void setBeat(int beat) {
        this.beat = beat;
    }

    public int getQuaver() {
        return quaver;
    }

    public void setQuaver(int quaver) {
        this.quaver = quaver;
    }

    public int getSemiquaver() {
        return semiquaver;
    }

    public void setSemiquaver(int semiquaver) {
        this.semiquaver = semiquaver;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
