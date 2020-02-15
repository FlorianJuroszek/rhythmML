package fr.polytech.rhythml.logical;

import fr.polytech.rhythml.generator.Visitable;
import fr.polytech.rhythml.generator.Visitor;

public class NotePosition implements Visitable {
    private Integer numerator;
    private Integer denominator;

    public NotePosition(String fraction) {
        this.numerator = Integer.valueOf(fraction.split("/")[0]);
        this.denominator = Integer.valueOf(fraction.split("/")[1]);
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
