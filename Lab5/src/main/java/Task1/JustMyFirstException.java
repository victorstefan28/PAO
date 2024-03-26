package Task1;

public class JustMyFirstException extends RuntimeException{
    public JustMyFirstException() {
        super("Oopsie! This is my first exception!");
    }

}

class Main
{
    public static void main(String[] args) {
        try {
            throw new JustMyFirstException();
        } catch (JustMyFirstException e) {
            System.out.println(e.getMessage());
        }
    }
}
