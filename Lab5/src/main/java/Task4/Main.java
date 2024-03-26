package Task4;

public class Main {
    public static void foo() {
        try {
            System.out.println("1");
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("2");
        }

        System.out.println("3");
    }

    public static void main(String[] args) {
        foo();
    }
}
