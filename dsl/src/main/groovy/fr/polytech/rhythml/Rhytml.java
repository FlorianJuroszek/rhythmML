package fr.polytech.rhythml;

import fr.polytech.rhythml.dsl.RhythmlDSL;

import java.io.File;

public class Rhytml {
    public static void main(String[] args) {
        RhythmlDSL dsl = new RhythmlDSL();
        if (args.length > 0) {
            dsl.eval(new File(args[0]));
        } else {
            System.out.println("/!\\ Missing arg: Please specify the path to a Groovy script file to execute");
        }
    }
}
