package Task6;

import Task6.serialization.StudentStorage;
import Task6.serialization.deserializer.RawDataStudentDeserializer;
import Task6.serialization.deserializer.TextStudentDeserializer;
import Task6.serialization.serializer.RawDataStudentSerializer;
import Task6.serialization.serializer.TextStudentSerializer;

import java.io.IOException;

public class Main {

    private final static boolean runBonus = false;

    public static void main(String[] args) {
        StudentStorage storage;
        if (runBonus) {
            storage = getStorageForBonus();
        } else {
            storage = getStorageForTask6();
        }

        StudentSerializationTester tester = new StudentSerializationTester(storage);
        tester.runTests();

        runCustomTests(storage);
    }

    private static void runCustomTests(StudentStorage storage) {
        // TODO: Aici va puteti scrie teste proprii pentru verificarea corectitudinii
    }

    private static StudentStorage getStorageForTask6() {
        return new StudentStorage(new RawDataStudentSerializer(), new RawDataStudentDeserializer());
    }

    private static StudentStorage getStorageForBonus() {
        return new StudentStorage(new TextStudentSerializer(), new TextStudentDeserializer());
    }
}
