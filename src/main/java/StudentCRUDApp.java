import Models.Course;
import Models.Student;
import Services.StudentService;

import java.util.List;
import java.util.Scanner;

public class StudentCRUDApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int ch = readInt("Choose an option: ");
            switch (ch) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: displayBestStudent(); break;
                case 6: displayFailingStudents(); break;
                case 7:
                    System.out.println("Exiting.");
                    return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Display Best Student (highest average)");
        System.out.println("6. Display Failing Students (average < 10)");
        System.out.println("7. Exit");
    }

    private static void addStudent() {
        int id = readInt("Enter ID: ");
        if (studentService.getStudent(id) != null) {
            System.out.println("❌ Student exists.");
            return;
        }
        String name = readString("Enter name: ");
        String email = readString("Enter email: ");
        String result = studentService.addStudent(id, name, email);
        System.out.println(result);
        if (result.startsWith("✅")) {
            addCourses(id);
        }
    }

    private static void addCourses(int id) {
        while (true) {
            String cname = readString("Course name (blank to finish): ");
            if (cname.isEmpty()) break;
            int note = readIntRange("Note (0-20): ", 0, 20);
            String result = studentService.addOrUpdateCourse(id, cname, note);
            System.out.println(result);
        }
    }

    private static void viewStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        for (Student s : students) printStudent(s);
    }

    private static void printStudent(Student s) {
        System.out.println("ID: " + s.getId() + " Name: " + s.getName() + " Email: " + s.getEmail());
        for (Course c : s.getCourses())
            System.out.println("\t" + c.getName() + ": " + c.getNote());
        double avg = studentService.calculateAverageNote(s);
        System.out.printf("Average note: %.2f\n", avg);
    }

    private static void updateStudent() {
        int id = readInt("Enter ID to update: ");
        Student s = studentService.getStudent(id);
        if (s == null) {
            System.out.println("❌ No such student.");
            return;
        }
        String newName = readString("Enter new name (blank to keep): ");
        String newEmail = readString("Enter new email (blank to keep): ");
        // Use the service updateStudent method for updating name and email
        String updateResult = studentService.updateStudent(id, newName.isEmpty() ? s.getName() : newName,
                newEmail.isEmpty() ? s.getEmail() : newEmail);
        System.out.println(updateResult);
        if (updateResult.startsWith("✅")) {
            // clear courses and re-add courses for this student via service
            s.getCourses().clear();
            addCourses(id);
        }
    }

    private static void deleteStudent() {
        int id = readInt("Enter ID to delete: ");
        if (studentService.getStudent(id) == null) {
            System.out.println("❌ Student not found.");
            return;
        }
        String result = studentService.deleteStudent(id);
        System.out.println(result);
    }

    private static void displayBestStudent() {
        Student best = studentService.getBestStudent();
        if (best == null) {
            System.out.println("No students.");
            return;
        }
        System.out.println("Best student:");
        printStudent(best);
    }

    private static void displayFailingStudents() {
        List<Student> failing = studentService.getFailingStudents();
        if (failing.isEmpty()) {
            System.out.println("No failing students.");
            return;
        }
        System.out.println("Failing students:");
        for (Student s : failing) printStudent(s);
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static int readIntRange(String prompt, int min, int max) {
        while (true) {
            int n = readInt(prompt);
            if (n >= min && n <= max) return n;
            System.out.println("Input must be between " + min + " and " + max);
        }
    }
}
