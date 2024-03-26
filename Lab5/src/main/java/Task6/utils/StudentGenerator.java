package Task6.utils;

import Task6.domain.Student;


import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public final class StudentGenerator {
    private static final List<String> ALLOWED_COURSES = Arrays.asList("Advanced Mathematics", "Chinese", "Programming", "Music", "Physical Education", "Psychology", "Cooking", "Public Speaking");
    private static final List<String> ALLOWED_NAMES = Arrays.asList("John Lemon", "Shabira", "Mr. Gugu", "Bu Dinci", "Axl Petal", "Nemi Tomato", "Balena Tormez");
    private static final int MAXIMUM_COURSES_PER_STUDENT = 10;

    private final Random random;

    public StudentGenerator() {
        this.random = new Random();
    }

    public List<Student> generateStudents(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(i -> generateStudent())
                .toList();
    }

    private Student generateStudent() {
        Student student = new Student(ALLOWED_NAMES.get(random.nextInt(ALLOWED_NAMES.size())));
        IntStream.range(0, random.nextInt(MAXIMUM_COURSES_PER_STUDENT))
                .forEach(ignored -> student.addCourseInformation(
                        ALLOWED_COURSES.get(random.nextInt(ALLOWED_COURSES.size())),
                        random.nextDouble(0, 10))
                );
        return student;
    }
}
