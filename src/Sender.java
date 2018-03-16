import java.io.OutputStream;
import java.util.Formatter;
import java.util.Scanner;

public class Sender implements Runnable {

    private Formatter formatter;
    private Scanner console = new Scanner(System.in);

    public Sender(OutputStream outputStream) {
        this.formatter = new Formatter(outputStream);
    }

    @Override
    public void run() {
        while (true) {
            send(console.nextLine());
        }
    }

    public void send(String s) {
        formatter.format("%s%n", s).flush();
    }
}
