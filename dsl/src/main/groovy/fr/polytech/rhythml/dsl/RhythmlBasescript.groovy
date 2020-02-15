package fr.polytech.rhythml.dsl

import fr.polytech.rhythml.logical.Bar
import fr.polytech.rhythml.logical.Section
import fr.polytech.rhythml.logical.Track

abstract class RhythmlBasescript extends Script {

    def track(String name) {
        ((RhythmlBinding) this.getBinding()).getRhythmlModel().createTrack(name)
    }

    def section(String name) {
        ((RhythmlBinding) this.getBinding()).getRhythmlModel().createSection(name)
    }

    def bar(String name) {
        Bar bar = ((RhythmlBinding) this.getBinding()).getRhythmlModel().createBar(name)

        def closure
        closure = { noteName ->
            [beat: { beat ->
                [quaver: { quaver ->
                    quaver = quaver instanceof String ? quaver : (String) quaver
                    if (quaver != "none") {
                        [semiquaver: { semiquaver ->
                            semiquaver = semiquaver instanceof String ? semiquaver : (String) semiquaver
                            if (semiquaver != "none") {
                                bar.getNotes().add(
                                        ((RhythmlBinding) this.getBinding()).getRhythmlModel().createNote(
                                                noteName instanceof String ? noteName : (String) noteName,
                                                beat instanceof String ? beat : (String) beat,
                                                quaver instanceof String ? quaver : (String) quaver,
                                                semiquaver instanceof String ? semiquaver : (String) semiquaver))
                                [and: closure]
                            } else {
                                bar.getNotes().add(
                                        ((RhythmlBinding) this.getBinding()).getRhythmlModel().createNote(
                                                noteName instanceof String ? noteName : (String) noteName,
                                                beat instanceof String ? beat : (String) beat,
                                                quaver instanceof String ? quaver : (String) quaver,
                                                "1/1"))
                                [and: closure]
                            }
                        }]
                    } else {
                        bar.getNotes().add(
                                ((RhythmlBinding) this.getBinding()).getRhythmlModel().createNote(
                                        noteName instanceof String ? noteName : (String) noteName,
                                        beat instanceof String ? beat : (String) beat,
                                        "1/1",
                                        "1/1"))
                        [and: closure]
                    }
                }]
            }]
        }
        ["with notes:": closure]
    }

    def forTrack(trackName) {
        Track track = trackName instanceof String ? (Track) ((RhythmlBinding) this.getBinding()).getVariable(trackName) : (Track) trackName

        def closure
        closure = { barName ->
            ["^": { repeat ->
                track.getBars().put(
                        barName instanceof String ? (Bar) ((RhythmlBinding) this.getBinding()).getVariable(barName) : (Bar) barName,
                        repeat instanceof String ? (Integer) ((RhythmlBinding) this.getBinding()).getVariable(repeat) : (Integer) repeat)
            }]
        }
        [section: { sectionName ->
            track.setSection(sectionName instanceof String ? (Section) ((RhythmlBinding) this.getBinding()).getVariable(sectionName) : (Section) sectionName)
            [withBars: closure]
        }]
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
