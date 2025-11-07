# Student Management Console App

A Java 8 console application for managing students, their courses, and grades. Data is stored in a human-readable JSON file (`students.json`). The project uses Maven for dependency management (including Gson for JSON).

---

## Features

- CRUD operations for students
- Each student has ID, name, email, and a list of courses with notes (0–20)
- Average note calculation per student
- Display best student (highest average)
- Display failing students (average < 10)
- Data automatically saved and loaded from `students.json`
- Interactive text-based menu

---

## Project Structure

```

src/main/java/main/...
├─ Factory/
├─ Models/
├─ Services/
├─ Strategy/
├─ Main.java
├─ Student.java
├─ Course.java
└─ ...
pom.xml

```

---

## Prerequisites

- Java 8 or higher
- Maven 3.x

---

## Run Instructions

1. **Clone the repository**
```

git clone https://github.com/slloane/StudentManagment.git
cd studentManagement

```

2. **Build the project**
```

mvn clean compile

```

3. **Run the application**
```

mvn exec:java

```

4. **Use the console menu to interact with the app.**

---

## Notes

- If you add or update dependencies, reload Maven in your IDE.
- The `target/` directory and other build artifacts are not versioned. Only source code and the `pom.xml` are tracked.
- Data is persisted in `students.json`, which will be created in the project directory.

---

## Troubleshooting

- If you get dependency errors, ensure your internet connection is active and reload Maven.
- For IDE integration with Maven, right-click the `pom.xml` and import as Maven project.
- Make sure Java 8 is installed (`java -version`).