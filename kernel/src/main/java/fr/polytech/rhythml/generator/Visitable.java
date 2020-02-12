package fr.polytech.rhythml.generator;

/**
 * Visitable interface that defines an entry point
 * which enables an object to be visited
 */
public interface Visitable {

    /**
     * Method that accept a visitor
     *
     * @param visitor which do some operations
     */
    void accept(final Visitor visitor);
}
