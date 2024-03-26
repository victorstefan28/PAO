package Task6.serialization;

import Task6.domain.Student;
import Task6.serialization.deserializer.StudentDeserializer;
import Task6.serialization.serializer.StudentSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class StudentStorage {
    private final StudentSerializer serializer;
    private final StudentDeserializer deserializer;

    public StudentStorage(StudentSerializer serializer, StudentDeserializer deserializer) {
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    public List<Student> readAllStudents(InputStream inputStream) throws IOException {
        List<Student> students = new ArrayList<>();
        Student student;

        while ((student = deserializer.deserializer(inputStream)) != null) {
            students.add(student);
        }

        return students;
    }

    public void writeAllStudents(OutputStream outputStream, List<Task6.domain.Student> students) throws IOException {
        for (Student student : students) {
            serializer.serialize(outputStream, student);
        }
    }
}
