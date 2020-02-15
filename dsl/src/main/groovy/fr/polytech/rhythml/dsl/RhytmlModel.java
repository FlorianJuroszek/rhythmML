package fr.polytech.rhythml.dsl;

import fr.polytech.rhythml.Song;
import fr.polytech.rhythml.generator.ToWiring;
import fr.polytech.rhythml.generator.Visitor;
import fr.polytech.rhythml.logical.*;
import groovy.lang.Binding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RhytmlModel {
    private List<Track> trackList;
    private List<Section> sectionList;
    private Binding binding;

    public RhytmlModel(Binding binding) {
        this.trackList = new ArrayList<Track>();
        this.sectionList = new ArrayList<Section>();
        this.binding = binding;
    }

    public void createTrack(String name) {
        Track track = new Track();
        track.setName(name);
        track.setBars(new HashMap<Bar, Integer>());
        this.binding.setVariable(name, track);
    }

    public void createSection(String name) {
        Section section = new Section();
        section.setName(name);
        this.binding.setVariable(name, section);
    }

    public Bar createBar(String name) {
        Bar bar = new Bar();
        bar.setName(name);
        bar.setNotes(new ArrayList<Note>());
        return bar;
    }

    public Note createNote(String name, String beat, String quaver, String semiquaver) {
        Note note = new Note();
        note.setName(name);
        note.setBeat(new NotePosition(beat));
        note.setQuaver(new NotePosition(quaver));
        note.setSemiquaver(new NotePosition(semiquaver));
        return note;
    }

    @SuppressWarnings("rawtypes")
    public Object generateCode(String songName) {
        Song song = new Song();
        song.setName(songName);
        song.setTracks(this.trackList);
        song.setSections(this.sectionList);
        Visitor codeGenerator = new ToWiring();
        song.accept(codeGenerator);

        return codeGenerator.getResult();
    }
}
