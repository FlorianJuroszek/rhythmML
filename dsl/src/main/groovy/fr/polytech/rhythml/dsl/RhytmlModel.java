package fr.polytech.rhythml.dsl;

import fr.polytech.rhythml.Song;
import fr.polytech.rhythml.generator.ToWiring;
import fr.polytech.rhythml.generator.Visitor;
import fr.polytech.rhythml.logical.*;
import groovy.lang.Binding;

import java.util.ArrayList;
import java.util.List;

public class RhytmlModel {
    private Song song;
    private List<Track> trackList;
    private Binding binding;

    public RhytmlModel(Binding binding) {
        this.trackList = new ArrayList<Track>();
        this.binding = binding;
        this.song = new Song();
    }

    public Song getSong() { return this.song; }

    public void createTrack(String name, List<Section> sections) {
        Track track = new Track();
        track.setName(name);
        track.setTempo(120);
        track.setSections(sections);
        track.setInstrument(Instrument.valueOf(name));
        this.trackList.add(track);
        this.binding.setVariable(name, track);
    }

    public Section createSection(String name, List<Note> notes) {
        Section section = new Section();
        section.setName(name);
        section.setNotes(notes);
        return section;
    }

    @SuppressWarnings("rawtypes")
    public Object generateCode(String songName) {
        song.setName(songName);
        song.setTracks(this.trackList);
        Visitor codeGenerator = new ToWiring();
        song.accept(codeGenerator);

        return codeGenerator.getResult();
    }
}
