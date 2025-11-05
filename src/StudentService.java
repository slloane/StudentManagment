import Models.Course;
import Models.Student;

import java.util.*;

public class StudentService {
    private List<Student> students = new ArrayList<>();

    // 1️⃣ Add Student
    public String addStudent(String id, String name, String email) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return "❌ Student ID already exists.";
            }
        }
        students.add(new Student(id, name, email));
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
    public String updateStudent(String id, String newName, String newEmail) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                if (newName != null && !newName.isEmpty()) s.setName(newName);
                if (newEmail != null && !newEmail.isEmpty()) s.setEmail(newEmail);
                return "✅ Student updated successfully!";
            }
        }
        return "❌ Student not found.";
    }

    // 4️⃣ Delete Student
    public String deleteStudent(String id) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId().equals(id)) {
                iterator.remove();
                return "🗑️ Student deleted successfully!";
            }
        }
        return "❌ Student not found.";
    }

    // 5️⃣ Add or Update Course Note
    public String addOrUpdateCourse(String id, Course course) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                s.addCourse(course);
                return "✅ Course note added/updated.";
            }
        }
        return "❌ Student not found.";
    }
}




