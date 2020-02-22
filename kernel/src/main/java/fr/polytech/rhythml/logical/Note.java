package fr.polytech.rhythml.logical;

import fr.polytech.rhythml.NamedElement;
import fr.polytech.rhythml.generator.Visitable;
import fr.polytech.rhythml.generator.Visitor;

public class Note {
    private String name;
    private String duration;
    private Integer repetition;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getRepetition() {
        return repetition;
    }

    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
    }
}
