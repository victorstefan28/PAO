package Task5;

public class Task5 {
    public static void bar() {
        try {

            throw new ClassCastException();
        }catch(ClassCastException e) {
            System.out.println("success");
        }
        catch(RuntimeException e) {
            System.out.println("fail");
        }
    }
    public static void main(String[] args) {
        bar();
    }
}
