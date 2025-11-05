import Models.Course;

import java.util.List;

public class AverageNoteStrategy implements NoteCalculationStrategy {
    @Override
    public double calculate(List<Course> courses) {
        if (courses == null || courses.isEmpty()) return 0.0;
        return courses.stream().mapToInt(Course::getNote).average().orElse(0);
    }
}
