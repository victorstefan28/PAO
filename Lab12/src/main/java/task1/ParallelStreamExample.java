package task1;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            numbers.add(ThreadLocalRandom.current().nextInt(1, 101));
        }

        numbers.parallelStream().forEach(number -> {
            int square = number * number;
            System.out.println(number + "^2=" + square);
        });
    }
}
