package fr.polytech.rhythml;

import fr.polytech.rhythml.generator.Visitable;
import fr.polytech.rhythml.generator.Visitor;

/**
 * Song main model
 */
public class Song implements NamedElement, Visitable {

    private String name;
    

    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
