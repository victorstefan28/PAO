package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class SquareCalculator extends Thread {
    private List<Integer> numbers;

    public SquareCalculator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        for (Integer number : numbers) {
            int square = number * number;
            System.out.println(number + "^2=" + square);
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            numbers.add(ThreadLocalRandom.current().nextInt(1, 101));
        }

        int chunkSize = numbers.size() / availableProcessors;
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < availableProcessors; i++) {
            int start = i * chunkSize;
            int end = (i == availableProcessors - 1) ? numbers.size() : start + chunkSize;
            List<Integer> sublist = numbers.subList(start, end);
            Thread thread = new SquareCalculator(sublist);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
