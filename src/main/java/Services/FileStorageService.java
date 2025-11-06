package Services;


import Models.Student;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class FileStorageService {
    private static final String DATA_FILE = "students.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private FileStorageService() {}

    private static class Holder {
        private static final FileStorageService INSTANCE = new FileStorageService();
    }

    public static FileStorageService getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Loads the list of students from students.json at startup.
     */
    public List<Student> loadStudents() {
        Path path = Paths.get(DATA_FILE);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<Student> students = gson.fromJson(reader, new TypeToken<List<Student>>(){}.getType());
            return students != null ? students : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Failed to load data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Saves the list of students automatically after any modification.
     */
    public void saveStudents(List<Student> students) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(DATA_FILE))) {
            gson.toJson(students, writer);
        } catch (IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
        }
    }
}

