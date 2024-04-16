package Task4;

import java.util.stream.IntStream;

public class LessThanNprinter {
    public static void main(String[] args) {
        int N = (int) (Math.random() * 1000 + 10);
        System.out.println(N);
        IntStream.rangeClosed(2, N)
                .filter(x -> x % 2 == 0)
                .mapToObj(x -> new Pair(x, x * x))
                .forEach(obj -> System.out.println(obj));
    }

    static class Pair {
        private int number;
        private int square;

        public Pair(int number, int square) {
            this.number = number;
            this.square = square;
        }

        @Override
        public String toString() {
            return String.format("{Number: %d, Square: %d}", number, square);
        }
    }
}
