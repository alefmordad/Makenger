package ir.alefmordad.tele.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Tunnel tunnel = new Tunnel(serverSocket.accept());
                new Thread(new Service(tunnel)).start();
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
            }
        }
    }
}
