package Task1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ReadFile1 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner scanner = new Scanner(fis);

            String line = scanner.nextLine();
            line = scanner.nextLine();
            System.out.println(line);
            scanner.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}




