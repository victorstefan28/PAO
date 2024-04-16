package Task2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderImpl {
    public static void main(String[] args) {
        String filePath = "input.txt";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine(); // Citim și ignorăm prima linie
            line = bufferedReader.readLine();
            System.out.println(line);


            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
