package Task3;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class FileGenerator {
    public static void main(String[] args) {

        String filePrefix = "./out/fisier_";
        String fileSuffix = ".txt";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:ms n");  /// n = nanosecond

        for (int i = 1; i <= 10; i++) {
            String fileName = filePrefix + i + fileSuffix;
            try {
                FileWriter writer = new FileWriter(fileName);
                String currentTime = dtf.format(LocalDateTime.now());
                writer.write("Index: " + i + ", Timestamp: " + currentTime);
                writer.close();
                System.out.println(fileName + " has been created.");
            } catch (IOException e) {
                System.out.println("Error in file" + fileName);
                e.printStackTrace();
            }
        }
    }
}


