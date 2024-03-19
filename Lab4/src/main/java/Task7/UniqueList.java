package Task7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

class Tester {
    private int totalNumberOfTests;
    private int passedTests;

    public void shouldReturnTrue(Supplier<Boolean> supplier, String testName) {
        runTest(() -> shouldReturnBoolean(supplier, true), testName);
    }

    public void shouldReturnFalse(Supplier<Boolean> supplier, String testName) {
        runTest(() -> shouldReturnBoolean(supplier, false), testName);
    }

    public void shouldEqual(Supplier<Object> supplier, Object expectedResult, String testName) {
        shouldReturnTrue(() -> expectedResult.equals(supplier.get()), testName);
    }

    public <T> void shouldBeEmpty(List<T> actual) {
        shouldReturnTrue(actual::isEmpty, "List should be empty");
    }

    public void shouldEqual(Object actual, Object expected) {
        runTest(() -> {
            if (!expected.equals(actual)) {
                throw new TestFailedException(new InvalidResultTestFailureReason(expected, actual));
            }
        }, "Should be equal");
    }


    public <T> void shouldEqual(T[] actual, T[] expected) {
        runTest(() -> {
            if (!Arrays.equals(expected, actual)) {
                throw new TestFailedException(new InvalidResultTestFailureReason(Arrays.toString(expected), Arrays.toString(actual)));
            }
        }, "Should be equal");
    }

    public void shouldThrow(Runnable runnable, Class<? extends Exception> expectedExceptionType) {
        runTest(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                if (!expectedExceptionType.isInstance(e)) {
                    throw new TestFailedException(new ExceptionResultTestFailureReason("", e));
                }
            }
        }, "Should throw " + expectedExceptionType.getName());
    }

    public void shouldNotThrow(Runnable runnable, String testName) {
        runTest(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw new TestFailedException(new ExceptionResultTestFailureReason("", e));
            }
        }, testName);
    }

    public void showResults() {
        System.out.println("You passed " + passedTests + "/" + totalNumberOfTests + " tests.");
    }

    private void runTest(Runnable test, String testName) {
        try {
            totalNumberOfTests++;
            test.run();
            passedTests++;
            printTestSuccess(testName);
        } catch (TestFailedException tfe) {
            printTestFailure(testName, tfe.getTestFailureReason());
        } catch (Exception e) {
            printTestFailure(testName, e);
        }
    }

    private void printTestSuccess(String testName) {
        System.out.println("Test #" + (totalNumberOfTests - 1) + " with name: \"" + testName + "\" passed.\n");
    }

    private void printTestFailure(String testName, Exception e) {
        System.err.println("Test #" + (totalNumberOfTests - 1) + " (" + testName + ") failed because: Unknown exception occurred: " + e.getMessage());
    }

    private void printTestFailure(String testName, TestFailureReason testFailureReason) {
        System.err.println("Test #" + (totalNumberOfTests - 1) + " (" + testName + ") failed because: " + testFailureReason.getReason());
    }

    private void shouldReturnBoolean(Supplier<Boolean> supplier, boolean expectedResult) {

        try {
            Boolean result = supplier.get();
            if (result != expectedResult) {
                throw new TestFailedException(new InvalidResultTestFailureReason(expectedResult, result));
            }
        } catch (TestFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new TestFailedException(new ExceptionResultTestFailureReason(expectedResult, e));
        }
    }

    private static class TestFailedException extends RuntimeException {
        private final TestFailureReason testFailureReason;

        TestFailedException(TestFailureReason testFailureReason) {
            this.testFailureReason = testFailureReason;
        }

        public TestFailureReason getTestFailureReason() {
            return testFailureReason;
        }
    }

    private static abstract class TestFailureReason {
        protected final Object expectedResult;

        public TestFailureReason(Object expectedResult) {
            this.expectedResult = expectedResult;
        }

        public abstract String getReason();
    }

    private static class InvalidResultTestFailureReason extends TestFailureReason {

        private final Object actualResult;

        InvalidResultTestFailureReason(Object expectedResult, Object actualResult) {
            super(expectedResult);
            this.actualResult = actualResult;
        }

        @Override
        public String getReason() {
            return "Actual result does not match expected result. Expected: " + expectedResult + ", Got: " + actualResult;
        }
    }

    private static class ExceptionResultTestFailureReason extends TestFailureReason {

        private final Throwable cause;

        public ExceptionResultTestFailureReason(Object expectedResult, Throwable cause) {
            super(expectedResult);
            this.cause = cause;
        }

        @Override
        public String getReason() {
            return "Implementation threw an exception. Expected: " + expectedResult + ", Got: " + cause.getMessage();
        }
    }
}

public class UniqueList<T> implements List<T> {

    private final List<T> storage;

    public UniqueList() {
        storage = new ArrayList<>();
    }

    @Override
    public String toString() {
        return storage.toString();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return storage.stream().filter(o::equals).findFirst().orElse(null) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return storage.iterator();
    }

    @Override
    public Object[] toArray() {
        return storage.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return storage.toArray(a);
    }

    @Override
    public boolean add(T t) {
        if (contains(t)) {
            return false;
        }
        return storage.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return storage.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return storage.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean result = false;
        for (T t : c) {
            if(add(t))
                result = true;
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return storage.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return storage.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        return storage.retainAll(c);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public T get(int index) {
        return storage.get(index);
    }

    @Override
    public T set(int index, T element) {

        if (contains(element)) {
            return null;
        }
        return storage.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        if (contains(element)) {
            return;
        }
        storage.add(index, element);
    }

    @Override
    public T remove(int index) {
        return storage.remove(index);
    }

    @Override
    public int indexOf(Object o) {

        return storage.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {

        return storage.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return storage.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return storage.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return storage.subList(fromIndex, toIndex);
    }
}

class Main {
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

