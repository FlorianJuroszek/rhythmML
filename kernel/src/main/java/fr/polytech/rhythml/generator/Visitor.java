package fr.polytech.rhythml.generator;

import fr.polytech.rhythml.Song;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class to declare visit operations
 *
 * @param <T> the visitable type
 */
public abstract class Visitor<T> {

    public abstract void visit(Song song);

    /***********************
     ** Helper mechanisms **
     ***********************/

    protected Map<String,Object> context = new HashMap<>();

    protected T result;

    public T getResult() {
        return result;
    }
}
