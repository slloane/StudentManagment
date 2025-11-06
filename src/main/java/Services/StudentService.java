package Services;

import Models.Course;
import Models.Student;
import Strategy.AverageNoteStrategy;
import Strategy.NoteCalculationStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
    private List<Student> students = new ArrayList<>();
    private FileStorageService storage = FileStorageService.getInstance();
    private NoteCalculationStrategy noteStrategy = new AverageNoteStrategy();

    public StudentService() {
        this.students = storage.loadStudents();
    }
    // 1️⃣ Add Student
    public String addStudent(int id, String name, String email) {
        for (Student s : students) {
            if (s.getId()==id) {
                return "❌ Student ID already exists.";
            }
        }
        students.add(new Student(id, name, email));
        save();  // Save after modification
        return "✅ Student added successfully!";
    }

    // 2️⃣ View Students
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\n--- Student List ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // 3️⃣ Update Student
    public String updateStudent(int id, String newName, String newEmail) {
        for (Student s : students) {
            if (s.getId()==id) {
                if (newName != null && !newName.isEmpty()) s.setName(newName);
                if (newEmail != null && !newEmail.isEmpty()) s.setEmail(newEmail);
                save();  // Save after modification
                return "✅ Student updated successfully!";
            }
        }
        return "❌ Student not found.";
    }

    // 4️⃣ Delete Student
    public String deleteStudent(int id) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId() == id) {
                iterator.remove();
                save();  // Save after modification
                return "🗑️ Student deleted successfully!";
            }
        }
        return "❌ Student not found.";
    }

    // 5️⃣ Add or Update Course Note
    public String addOrUpdateCourse(int id, String courseName, int note) {
        for (Student s : students) {
            if (s.getId()==id) {
                s.addCourse(courseName,note);
                save();  // Save after modification
                return "✅ Course note added/updated.";
            }
        }
        return "❌ Student not found.";
    }
    public Student getStudent(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    private void save() {
        storage.saveStudents(getAllStudents());
    }

    public double calculateAverageNote(Student student) {
        return noteStrategy.calculate(student.getCourses());
    }

    public Student getBestStudent() {
        return students.stream()
                .max(Comparator.comparingDouble(this::calculateAverageNote))
                .orElse(null);
    }

    public List<Student> getFailingStudents() {
        return students.stream()
                .filter(s -> calculateAverageNote(s) < 10)
                .collect(Collectors.toList());
    }

}




