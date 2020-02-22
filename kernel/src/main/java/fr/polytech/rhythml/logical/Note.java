package fr.polytech.rhythml.logical;

public class Note {
    private String name;
    private String duration;
    private Integer repetition;

    public Note() {
    }

    public Note(String name, String duration, Integer repetition) {
        this.name = name;
        this.duration = duration;
        this.repetition = repetition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getRepetition() {
        return repetition;
    }

    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
    }
}
