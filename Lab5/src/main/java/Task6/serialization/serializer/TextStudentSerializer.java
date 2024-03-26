package Task6.serialization.serializer;



import Task6.domain.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TextStudentSerializer implements StudentSerializer {
    @Override
    public void serialize(OutputStream outputStream, Student student) {
        throw new UnsupportedOperationException("");
    }
}
