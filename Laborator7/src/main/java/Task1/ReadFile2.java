package Task1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile2 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line = reader.readLine(); // Citim și ignorăm prima linie
            line = reader.readLine();
            System.out.println(line);

            reader.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
