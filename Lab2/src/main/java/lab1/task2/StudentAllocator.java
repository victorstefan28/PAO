package lab1.task2;

import java.util.Arrays;
import java.util.Random;

public class StudentAllocator {

    public static void main(String[] args)
    {
        Student[] studentsTest = createStudents();

        System.out.println(Arrays.toString(studentsTest));

        System.out.println("Studenti care au trecut: '\n");

        Course cursMatematica = new Course("Matematica", 5, studentsTest);

        cursMatematica.printAllPassingStudents();

    }

    public static Student[] createStudents() {
        int size = 10;
        Student[] students = new Student[size];

        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(generateRandomName(), generateRandomGrade());
        }

        return students;
    }

    private static String generateRandomName() {
        String[] names = {"Marian", "Ion", "Andrei", "Ioana", "Darius"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }

    private static int generateRandomGrade() {
        return new Random().nextInt(10) + 1;
    }
}
