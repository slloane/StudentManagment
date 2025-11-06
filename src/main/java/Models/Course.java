package Models;

public class Course {
    private String name;
    private int note; // 0 to 20

    public Course(String name, int note) {
        this.name = name;
        this.note = note;
    }
    public String getName() { return name; }
    public int getNote() { return note; }
    public void setNote(int note) { this.note = note; }
}