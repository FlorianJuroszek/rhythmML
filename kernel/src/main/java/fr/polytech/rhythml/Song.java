package fr.polytech.rhythml;

import fr.polytech.rhythml.generator.Visitable;
import fr.polytech.rhythml.generator.Visitor;
import fr.polytech.rhythml.logical.Track;

import java.util.List;

/**
 * Song main model
 */
public class Song implements NamedElement, Visitable {

    private String name;
    private List<Track> tracks;

    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
