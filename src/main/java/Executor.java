import java.util.List;

public class Executor {

    private int counter;
    Printer lockP = new Printer();

    synchronized int increment() {
        return counter++;
    }

    int increment2() {
        synchronized (lockP) {
            return counter++;
        }
    }

    public static void main(String[] s) {
        Printer lockP = new Printer();
        Scanner lockS = new Scanner();


    }


}
