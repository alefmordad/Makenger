package ir.alefmordad.makenger.server;

import ir.alefmordad.makenger.core.exceptions.FakeUserException;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;

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
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FakeUserException e) {
                System.err.println(e);
            }
        }
    }
}
