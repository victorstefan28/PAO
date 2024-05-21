package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class CompletableFutureExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            numbers.add(ThreadLocalRandom.current().nextInt(1, 101));
        }

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (Integer number : numbers) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                int square = number * number;
                System.out.println(number + "^2=" + square);
            });
            futures.add(future);
        }
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}

