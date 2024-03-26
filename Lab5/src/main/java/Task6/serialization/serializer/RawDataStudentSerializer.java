package Task6.serialization.serializer;


import Task6.domain.Student;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class RawDataStudentSerializer implements StudentSerializer {
    @Override
    public void serialize(OutputStream outputStream, Student student) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(student);

    }
}

