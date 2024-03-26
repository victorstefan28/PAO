package Task6.serialization.deserializer;



import Task6.domain.Student;

import java.io.IOException;
import java.io.InputStream;

public interface StudentDeserializer {
    Student deserializer(InputStream inputStream) throws IOException;
}
