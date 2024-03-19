import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Tester {
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
