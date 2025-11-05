import Strategy.AverageNoteStrategy;
import Strategy.NoteCalculationStrategy;

public class NoteCalculationFactory {
    public static NoteCalculationStrategy getStrategy(String type) {
        switch(type.toLowerCase()) {
            case "average": return new AverageNoteStrategy();

            default: throw new IllegalArgumentException("Unknown calculation type");
        }
    }
}
