package ir.alefmordad.makenger.server;

import java.io.IOException;

public class Serve {

    public static void main(String[] args) throws IOException {
        Server server = new Server(9090);
        new Thread(server).start();
    }

}
