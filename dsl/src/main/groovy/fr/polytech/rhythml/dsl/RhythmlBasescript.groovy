package fr.polytech.rhythml.dsl

import fr.polytech.rhythml.logical.Note
import fr.polytech.rhythml.logical.Section

abstract class RhythmlBasescript extends Script {
    private String songName;

    def song(String name) {
        this.songName = name
    }

    def tempo(Integer value) {
        ((RhythmlBinding) this.getBinding()).getRhythmlModel().getSong().setTempo(value instanceof String ? Integer.valueOf(value) : value)
    }

    def instrument(String name) {
        List<Section> sections = new ArrayList<>()
        ((RhythmlBinding) this.getBinding()).getRhythmlModel().createTrack(name, sections)

        def sectionN
        def closure
        closure =
                { sectionName ->
                    [
                            notes: { noteList ->
                                sectionN = ((RhythmlBinding) this.getBinding()).getRhythmlModel().createSection(
                                        sectionName instanceof String ? sectionName : (String) sectionName,
                                        buildNoteList(noteList instanceof String ? noteList : (String) noteList, name)
                                )
                                sections.add(sectionN)
                                [section: closure]
                            }
                    ]
                }
        [section: closure]
    }

    static def buildNoteList(String notesString, String instrumentName) {
        boolean isRepeated = false
        boolean isClosed = true
        List<Note> repeatedNotes = new ArrayList<>()

        String[] noteFakeList = notesString.split(" ")
        List<Note> noteList = new ArrayList<>()
        for (String item : noteFakeList) {
            if (item == "(") {
                if (!isRepeated) {
                    isRepeated = true
                    isClosed = false
                } else {
                    throw new Error("Already repeating notes")
                }
            } else if (item == ")") {
                if (!isClosed) {
                    isClosed = true
                } else {
                    throw new Error("Already closed repeating notes")
                }
            } else {
                if (instrumentName == "Drums") {
                    String[] sameTimeNotes = item.split("\\+")
                    if (sameTimeNotes.length > 1) {
                        item = ""
                        for (int i = 0; i < sameTimeNotes.length; i++) {
                            sameTimeNotes[i] = "[" + sameTimeNotes[i] + "]"
                            if (i < sameTimeNotes.length - 1) {
                                item += sameTimeNotes[i] + "+"
                            } else {
                                item += sameTimeNotes[i]
                            }
                        }
                    } else {
                        item = "[" + item + "]"
                    }
                }
                String[] repeatNote = item.split("x")
                if (repeatNote.length > 1) {
                    def repetition = instrumentName == "Drums" ? Integer.valueOf(repeatNote[1].substring(0, repeatNote[1].length() - 1)) : Integer.valueOf(repeatNote[1])

                    if (isRepeated && isClosed) {
                        for (int i = 0; i < repetition; i++) {
                            noteList.addAll(repeatedNotes)
                        }
                        repeatedNotes = new ArrayList<>()
                        isRepeated = false
                        isClosed = true
                    } else {
                        if (isRepeated) {
                            repeatedNotes.get(repeatedNotes.size() - 1).setRepetition(repetition)
                        } else {
                            noteList.get(noteList.size() - 1).setRepetition(repetition)
                        }
                    }
                } else {
                    if (isRepeated) {
                        repeatedNotes.add(new Note(item, "i", 1))
                    } else {
                        noteList.add(new Note(item, "i", 1))
                    }
                }


            }
        }
        return noteList
    }

    def play() {
        println(((RhythmlBinding) this.getBinding()).getRhythmlModel().generateCode(this.songName).toString())
    }

// disable run method while running
    int count = 0

    abstract void scriptBody()

    def run() {
        if (count == 0) {
            count++
            scriptBody()
        } else {
            println "Run method is disabled"
        }
    }
}
