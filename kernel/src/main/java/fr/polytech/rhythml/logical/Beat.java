package fr.polytech.rhythml.logical;

import fr.polytech.rhythml.NamedElement;
import fr.polytech.rhythml.generator.Visitable;
import fr.polytech.rhythml.generator.Visitor;

import java.util.List;

public class Beat implements Visitable, NamedElement {

    private String name;

    private List<Beat> quavers;

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }
}
