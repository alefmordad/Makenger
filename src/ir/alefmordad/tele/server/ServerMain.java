package ir.alefmordad.tele.server;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) throws IOException {
        Server server = new Server(9090);
        new Thread(server).start();
    }

}
