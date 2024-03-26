package Task6;


import Task6.domain.Student;
import Task6.serialization.StudentStorage;
import Task6.utils.StudentGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import static Task6.utils.FileUtils.openFileForReading;
import static Task6.utils.FileUtils.openFileForWriting;


public final class StudentSerializationTester {

    private final StudentStorage storage;

    public StudentSerializationTester(StudentStorage storage) {
        this.storage = storage;
    }

    public void runTests() {
        try {
            testWithSingleStudent();
            testWithMultipleStudents();
        } catch (IOException e) {
            System.err.println("Something threw IOException:\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    private void testWithSingleStudent() throws IOException {
        String filePath = "./singleStudent.out";
        List<Student> studentList = (new StudentGenerator()).generateStudents(1);
        String testName = "single student serialization/deserialization";

        runSerializationDeserializationTest(filePath, studentList, testName);
    }

    private void testWithMultipleStudents() throws IOException {
        String filePath = "./multipleStudents.out";
        List<Student> studentList = (new StudentGenerator()).generateStudents(10);
        String testName = "single student serialization/deserialization";

        runSerializationDeserializationTest(filePath, studentList, testName);
    }

    private void runSerializationDeserializationTest(String filePath, List<Student> studentList, String testName) throws IOException {
        writeStudentList(filePath, studentList);

        List<Student> studentsFromFile = readStudentList(filePath);
        System.out.println("Read students from file: " + filePath + " " + studentsFromFile.toString());
        if (studentList.equals(studentsFromFile)) {
            System.out.println("Passed test: " + testName + "!");
        } else {
            System.err.println("Failed test: " + testName + "!");
        }
    }

    private List<Student> readStudentList(String filePath) throws IOException {
        List<Student> studentsFromFile;
        try (InputStream fileIn = openFileForReading(filePath)) {
            studentsFromFile = storage.readAllStudents(fileIn);
        }
        return studentsFromFile;
    }

    private void writeStudentList(String filePath, List<Student> studentList) throws IOException {
        try (OutputStream fileOut = openFileForWriting(filePath)) {
            storage.writeAllStudents(fileOut, studentList);
            System.out.println("Wrote students to file: " + filePath);
        }
    }

}
