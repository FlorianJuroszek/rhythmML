package fr.polytech.rhythml;

import fr.polytech.rhythml.dsl.RhythmlDSL;

import java.io.File;
import java.io.IOException;

import org.jfugue.*;

import javax.sound.midi.InvalidMidiDataException;

public class Rhytml {
    public static void main(String[] args) {
        /*
        Pattern pattern1 = new Pattern("V9 [BASS_DRUM]q+[CLOSED_HI_HAT]i [CLOSED_HI_HAT]i [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]i [BASS_DRUM]q+[CLOSED_HI_HAT]i [CLOSED_HI_HAT]i [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]i |");
        pattern1.repeat(4);
        //pattern.add("V4 e2i+G2i+B2i Rq e4i+G4i+eB4i Rh | e5i+G5i+B6i Rq e4i+G4i+B4i Rh");

        //Pattern pattern = new Pattern("V1 I2 e2i+B2i+G2i Rq e2i+B2i+G2i Rh e2i+B2i+G2i Rq e2i+B2i+G2i Rh");
        // pattern.add("V1 I2 Ri Bq Bq Bq Bq Gq Bq");
        //Pattern pattern = new Pattern("V2 I35 D4q A4q D2q D4q D4q A4q A2q A4q");

        Pattern pattern2 = new Pattern("V1 I35 Di Ai Di Di Di Ai Ai Ai | Di Ai Di Di Di Ai Ai Ai | Di Ai Di Di Di Ai Ai Ai");
        //Pattern pattern2 = new Pattern("V8 I0 Ri Bi Bi Bi Bi Bi Gi Bi | Ri Bi- Ri Bs Gs Bi Gi Bi Ri");

        //MidiFileManager.savePatternToMidi(pattern, new File("TEST.midi"));

        Player player = new Player();
        player.play(pattern2);
        */

        RhythmlDSL dsl = new RhythmlDSL();
        if (args.length > 0) {
            dsl.eval(new File(args[0]));
        } else {
            System.out.println("/!\\ Missing arg: Please specify the path to a Groovy script file to execute");
        }
    }
}
