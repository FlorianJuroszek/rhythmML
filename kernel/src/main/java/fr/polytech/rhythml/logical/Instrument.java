package fr.polytech.rhythml.logical;

public enum Instrument {
    Drums(9),
    Piano(1);

    public int value;
    Instrument(int value) {
        this.value = value;
    }
}
