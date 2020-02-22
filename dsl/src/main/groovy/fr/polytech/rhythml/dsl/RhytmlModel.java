package fr.polytech.rhythml.dsl;

import fr.polytech.rhythml.Song;
import fr.polytech.rhythml.generator.ToWiring;
import fr.polytech.rhythml.generator.Visitor;
import fr.polytech.rhythml.logical.*;
import groovy.lang.Binding;

import java.util.ArrayList;
import java.util.List;

public class RhytmlModel {
    private List<Track> trackList;
    private Binding binding;

    public RhytmlModel(Binding binding) {
        this.trackList = new ArrayList<Track>();
        this.binding = binding;
    }

    public void createTrack(String name, List<Section> sections) {
        Track track = new Track();
        track.setName(name);
        //FIXME manual for now but will change later
        track.setBpm(120);
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

    public Note createNote(String name, String beat, String quaver, String semiquaver) {
        Note note = new Note();
        note.setName(name);
        /*note.setBeat(new NotePosition(beat));
        note.setQuaver(new NotePosition(quaver));
        note.setSemiquaver(new NotePosition(semiquaver));*/
        return note;
    }

    @SuppressWarnings("rawtypes")
    public Object generateCode(String songName) {
        Song song = new Song();
        song.setName(songName);
        song.setTracks(this.trackList);
        Visitor codeGenerator = new ToWiring();
        song.accept(codeGenerator);

        return codeGenerator.getResult();
    }
}
