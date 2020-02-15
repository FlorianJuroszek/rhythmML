package fr.polytech.rhythml.logical;

import fr.polytech.rhythml.generator.Visitable;
import fr.polytech.rhythml.generator.Visitor;

public class NotePosition implements Visitable {
    private Integer numerator;
    private Integer denominator;

    public NotePosition(String fraction) {
        String[] result = fraction.split("/");
        System.out.println("result : " + result.length + " " + result[0]);
        this.numerator = Integer.valueOf(result[0]);
        this.denominator = Integer.valueOf(result[1]);
    }

    public Integer getNumerator() {
        return numerator;
    }

    public void setNumerator(Integer numerator) {
        this.numerator = numerator;
    }

    public Integer getDenominator() {
        return denominator;
    }

    public void setDenominator(Integer denominator) {
        this.denominator = denominator;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
