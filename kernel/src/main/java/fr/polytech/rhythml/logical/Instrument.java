package fr.polytech.rhythml.logical;

public enum Instrument {
    Drums(9),
    Piano(0),
    Bass(32),
    Trumpet(56),
    Violin(40);

    public int value;
    Instrument(int value) {
        this.value = value;
    }
}
