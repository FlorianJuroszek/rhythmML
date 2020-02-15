package fr.polytech.rhythml.logical;

import fr.polytech.rhythml.NamedElement;
import fr.polytech.rhythml.generator.Visitable;
import fr.polytech.rhythml.generator.Visitor;

import java.util.HashMap;

public class Track implements NamedElement, Visitable {
    private String name;
    private Section section;
    private HashMap<Bar, Integer> bars;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public HashMap<Bar, Integer> getBars() {
        return bars;
    }

    public void setBars(HashMap<Bar, Integer> bars) {
        this.bars = bars;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
