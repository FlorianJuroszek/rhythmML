package fr.polytech.rhythml;

import fr.polytech.rhythml.dsl.RhythmlDSL;

import java.io.File;
import java.io.IOException;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import javax.sound.midi.InvalidMidiDataException;

public class Rhytml {
    public static void main(String[] args) {
        Player player = new Player();
        //Pattern pattern = new Pattern("V9 [BASS_DRUM]q+[CLOSED_HI_HAT]i [CLOSED_HI_HAT]i [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]i [BASS_DRUM]q+[CLOSED_HI_HAT]i [CLOSED_HI_HAT]i [CLOSED_HI_HAT]i+[ELECTRIC_SNARE]i [CLOSED_HI_HAT]i |");
        //pattern.add("V4 e2i+G2i+B2i Rq e4i+G4i+eB4i Rh | e5i+G5i+B6i Rq e4i+G4i+B4i Rh");

        Pattern pattern = new Pattern("V1 I2 e2i+B2i+G2i Rq e2i+B2i+G2i Rh e2i+B2i+G2i Rq e2i+B2i+G2i Rh");
        // pattern.add("V1 I2 Ri Bq Bq Bq Bq Gq Bq");

        pattern.repeat(8);
        //MidiFileManager.savePatternToMidi(pattern, new File("TEST.midi"));

        player.play(pattern);
        try {
            Pattern pattern2 = MidiFileManager.loadPatternFromMidi(new File("TEST.midi"));
            System.out.println(pattern2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

        RhythmlDSL dsl = new RhythmlDSL();
        if (args.length > 0) {
            dsl.eval(new File(args[0]));
        } else {
            System.out.println("/!\\ Missing arg: Please specify the path to a Groovy script file to execute");
        }
    }
}
