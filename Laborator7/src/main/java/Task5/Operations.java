package Task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

public class Operations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter N:");
        int N = 0;
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                N = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input");
                scanner.next();
            }
        }

        List<Double> numbers = new ArrayList<>();
        System.out.println("Enter " + N + " numbers:");
        while (numbers.size() < N) {
            if (scanner.hasNextDouble()) {
                numbers.add(scanner.nextDouble());
            } else {
                System.out.println("Invalid input");
                scanner.next();
            }
        }
        scanner.close();

        List<Function<Double, Double>> operations = new ArrayList<>();
        operations.add(x -> x * 2);
        operations.add(x -> x + 1);
        operations.add(x -> 1 / x);
        operations.add(x -> x * x);
        operations.add(Math::sin);
        String[] OperationsEnum = {"x * 2", "x + 1", "1 / x", "x * x", "sin(x)"};
        Random random = new Random();
        for (Double number : numbers) {
            Integer randIndex = random.nextInt(operations.size());
            Function<Double, Double> randomOperation = operations.get(randIndex);
            System.out.printf("Pentru numărul %f, rezultatul operației %s alese este: %f%n", number, OperationsEnum[randIndex] , randomOperation.apply(number));
        }
    }
}
