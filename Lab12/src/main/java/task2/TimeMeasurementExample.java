package task2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
        }
    }
}

public class TimeMeasurementExample {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        int[] sizes = {10, 1000, 10000, 10000000};
        String fileName = "time_measurements.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int size : sizes) {
                List<Integer> numbers = generateNumbers(size);

                long sequentialTime = measureSequential(numbers);
                long threadTime = measureThread(numbers);
                long parallelStreamTime = measureParallelStream(numbers);
                long completableFutureTime = measureCompletableFuture(numbers);

                writer.write("N=: " + size + "\n");
                writer.write("Secvential: " + sequentialTime + " ns\n");
                writer.write("Thread: " + threadTime + " ns\n");
                writer.write("Parallel Stream: " + parallelStreamTime + " ns\n");
                writer.write("CompletableFuture: " + completableFutureTime + " ns\n\n");
            }
        }
    }

    private static List<Integer> generateNumbers(int size) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            numbers.add(ThreadLocalRandom.current().nextInt(1, 101));
        }
        return numbers;
    }

    private static long measureSequential(List<Integer> numbers) {
        long start = System.nanoTime();
        for (Integer number : numbers) {
            int square = number * number;
        }
        long end = System.nanoTime();
        return end - start;
    }

    private static long measureThread(List<Integer> numbers) throws InterruptedException {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int chunkSize = numbers.size() / availableProcessors;
        List<Thread> threads = new ArrayList<>();

        long start = System.nanoTime();
        for (int i = 0; i < availableProcessors; i++) {
            int startIdx = i * chunkSize;
            int endIdx = (i == availableProcessors - 1) ? numbers.size() : startIdx + chunkSize;
            List<Integer> sublist = numbers.subList(startIdx, endIdx);
            Thread thread = new SquareCalculator(sublist);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.nanoTime();
        return end - start;
    }

    private static long measureParallelStream(List<Integer> numbers) {
        long start = System.nanoTime();
        numbers.parallelStream().forEach(number -> {
            int square = number * number;
        });
        long end = System.nanoTime();
        return end - start;
    }

    private static long measureCompletableFuture(List<Integer> numbers) throws ExecutionException, InterruptedException {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        long start = System.nanoTime();
        for (Integer number : numbers) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                int square = number * number;
            });
            futures.add(future);
        }

        for (CompletableFuture<Void> future : futures) {
            future.get();
        }
        long end = System.nanoTime();
        return end - start;
    }
}

