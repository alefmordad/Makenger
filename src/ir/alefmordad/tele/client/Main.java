package ir.alefmordad.tele.client;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Tunnel tunnel = new Tunnel("localhost", 9090);
        new Thread(tunnel).start();
    }

}
