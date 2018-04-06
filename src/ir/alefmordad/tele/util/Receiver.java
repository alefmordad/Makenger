package ir.alefmordad.tele.util;

import java.io.InputStream;
import java.util.Scanner;

public class Receiver implements Runnable {

    private Scanner scanner;

    public Receiver(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(receive());
        }
    }

    public String receive() {
        return scanner.nextLine();
    }
}
