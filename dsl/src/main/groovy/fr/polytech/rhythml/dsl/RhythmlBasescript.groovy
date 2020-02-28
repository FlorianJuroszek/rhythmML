package fr.polytech.rhythml.dsl

import fr.polytech.rhythml.logical.Note
import fr.polytech.rhythml.logical.Section

abstract class RhythmlBasescript extends Script {

    def song(String name) {

    }

    def instrument(String name) {
        List<Section> sections = new ArrayList<>()
        ((RhythmlBinding) this.getBinding()).getRhythmlModel().createTrack(name, sections)

        def closure
        closure =
                { sectionName ->
                    [
                            notes: { noteList ->
                                Section section = ((RhythmlBinding) this.getBinding()).getRhythmlModel().createSection(
                                        sectionName instanceof String ? sectionName : (String) sectionName,
                                        buildNoteList(noteList instanceof String ? noteList : (String) noteList, name)
                                )
                                sections.add(section)
                                [section: closure]
                            }
                    ]
                }
        [section: closure]
    }

    static def buildNoteList(String notesString, String instrumentName) {
        String[] noteFakeList = notesString.split(" ")
        List<Note> noteList = new ArrayList<>()
        for (String item : noteFakeList) {
            if (instrumentName == "Drums") {
                String[] sameTimeNotes = item.split("\\+")
                if (sameTimeNotes.length > 0) {
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
            String[] repeat = item.split("\\*")
            if (repeat.length > 1) {
                int repetition = 1
                if (instrumentName == "Drums") {
                    noteList.get(noteList.size() - 1).setRepetition(Integer.valueOf(repeat[1].substring(0, repeat[1].length() - 1)))
                } else {
                    noteList.get(noteList.size() - 1).setRepetition(Integer.valueOf(repeat[1]))
                }
            } else {
                noteList.add(new Note(item, "i", 1))
            }
        }
        return noteList
    }

// export name
    def export(String name) {
        println(((RhythmlBinding) this.getBinding()).getRhythmlModel().generateCode(name).toString())
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
