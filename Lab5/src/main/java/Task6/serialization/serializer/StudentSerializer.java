package Task6.serialization.serializer;



import Task6.domain.Student;

import java.io.IOException;
import java.io.OutputStream;

public interface StudentSerializer {
    void serialize(OutputStream outputStream, Student student) throws IOException;
}
