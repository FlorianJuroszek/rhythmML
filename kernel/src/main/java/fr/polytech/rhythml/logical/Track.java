package fr.polytech.rhythml.logical;

import fr.polytech.rhythml.NamedElement;
import fr.polytech.rhythml.generator.Visitable;
import fr.polytech.rhythml.generator.Visitor;

import java.util.HashMap;
import java.util.List;

public class Track implements NamedElement, Visitable {
    private String name;
    private List<Section> sections;
    private float bpm;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public float getBpm() {
        return bpm;
    }

    public void setBpm(float bpm) {
        this.bpm = bpm;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
