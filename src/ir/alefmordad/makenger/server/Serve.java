package ir.alefmordad.makenger.server;

import java.io.IOException;

public class Serve {

    public static void main(String[] args) throws IOException {
        new Thread(new Server(9090)).start();
    }

}
