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
                                        buildNoteList(noteList instanceof String ? noteList : (String) noteList)
                                )
                                sections.add(section)
                                [section: closure]
                            }
                    ]
                }
        [section: closure]
    }

    static def buildNoteList(String notesString) {
        String[] noteFakeList = notesString.split(" ")
        List<Note> noteList = new ArrayList<>()
        for (String item : noteFakeList) {
            String[] repeat = item.split("x")
            if (repeat.length > 1) {
                noteList.get(noteList.size() - 1).setRepetition(Integer.valueOf(repeat[1]))
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
