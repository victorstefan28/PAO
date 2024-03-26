package Task6.serialization.deserializer;

import Task6.domain.Student;


import java.io.*;

public class RawDataStudentDeserializer implements StudentDeserializer{
    @Override
    public Student deserializer(InputStream inputStream) {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (Student) objectInputStream.readObject();
        }
        catch(EOFException e) {
            System.out.println("End of file");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        catch(IOException e) {
            System.out.println("IO Exception");
        }
        return null;
    }
}
