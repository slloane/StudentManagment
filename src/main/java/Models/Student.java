package Models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private String email;
    private List<Course> courses = new ArrayList<>();
    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Course> getCourses() { return courses; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    public void addCourse(String courseName, int note) {
        Course course = new Course(courseName,note);
        this.courses.add(course);
    }

    @Override
    public String toString() {
        if (courses == null || courses.isEmpty()) {
            return "ID: " + id + " | Name: " + name + " | Email: " + email + " | Courses: none";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Course c : courses) {
                sb.append(c.getName()).append(": ").append(c.getNote()).append(", ");
            }
            // Remove the trailing comma and space
            if (sb.length() > 2) {
                sb.setLength(sb.length() - 2);
            }
            return "ID: " + id + " | Name: " + name + " | Email: " + email + " | Courses: " + sb.toString();
        }
    }
}
