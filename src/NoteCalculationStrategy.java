package Strategy;

import Models.Course;

import java.util.List;

public interface NoteCalculationStrategy {
    double calculate(List<Course> courses);
}
