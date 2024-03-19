import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tester tester = new Tester();

        UniqueList<Integer> list = new UniqueList<>();

        try {
            tester.shouldReturnTrue(() -> list.add(5), "Should add 5 to the list");
            tester.shouldReturnFalse(() -> list.add(5), "Should not add 5 to the list (duplicate)");

            tester.shouldReturnTrue(() -> list.addAll(Arrays.asList(10, 25, 40)), "Should add [10, 25, 40] to the list");
            tester.shouldEqual(list, Arrays.asList(5, 10, 25, 40));
            tester.shouldEqual(list::size, 4, "Should return size of 4");

            tester.shouldReturnTrue(() -> list.containsAll(Arrays.asList(5, 10, 25, 40)), "Should contain all elements of [5, 10, 25, 40]");
            tester.shouldReturnTrue(() -> list.containsAll(Arrays.asList(5, 40)), "Should contain all elements of [5, 40]");
            tester.shouldReturnTrue(() -> list.contains(5), "Should contain 5");

            tester.shouldReturnTrue(() -> list.remove(0) == 5, "Should remove first element and it should be 5");
            tester.shouldReturnFalse(() -> list.contains(5), "Should not contain 5");

            tester.shouldNotThrow(() -> list.add(0, 5), "Should add 5 on first index");
            tester.shouldEqual(list, Arrays.asList(5, 10, 25, 40));

            tester.shouldReturnTrue(() -> list.addAll(Arrays.asList(0, 10, 25, 40)), "Should add 0 to the list");
            tester.shouldEqual(list, Arrays.asList(5, 10, 25, 40, 0));

            tester.shouldReturnTrue(() -> list.removeAll(Arrays.asList(5, 10, 25, 40)), "Should remove [5, 10, 25, 40]");
            tester.shouldEqual(list, List.of(0));

            tester.shouldReturnTrue(() -> list.remove(Integer.valueOf(0)), "Should remove 0");
            tester.shouldBeEmpty(list);

            tester.shouldReturnTrue(() -> list.addAll(Arrays.asList(5, 10, 25, 40, 0)), "Should add [5, 10, 25, 40, 0]");
            tester.shouldEqual(list.toArray(), Arrays.asList(5, 10, 25, 40, 0).toArray());

            tester.shouldEqual(list.toArray(new Integer[list.size()]), list.toArray());

            tester.shouldThrow(() -> list.set(-1, 5), IndexOutOfBoundsException.class);
            tester.shouldThrow(() -> list.set(0, 0), IllegalArgumentException.class);
            tester.shouldEqual(list.set(0, 6), 5);

            tester.shouldEqual(list.indexOf(999), -1);
            tester.shouldEqual(list.indexOf(5), -1);
            tester.shouldEqual(list.indexOf(6), 0);
            tester.shouldEqual(list.indexOf(0), 4);

            tester.shouldEqual(list.indexOf(0), list.lastIndexOf(0));

            tester.shouldNotThrow(list::clear, "Should clear list");
            tester.shouldBeEmpty(list);
        } catch (UnsupportedOperationException ignored) { }

        tester.showResults();
    }
}

