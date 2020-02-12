package fr.polytech.rhythml;

/**
 * A named element
 */
public interface NamedElement {

    /**
     * Set the name of an element
     *
     * @param name of the element
     */
    void setName(final String name);

    /**
     * Get the element's name
     *
     * @return name as a string
     */
    String getName();
}
