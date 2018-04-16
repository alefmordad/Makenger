package ir.alefmordad.makenger.server;

import ir.alefmordad.makenger.core.util.exceptions.FakeUserException;

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
                Channel channel = new Channel(serverSocket.accept());
                new Thread(new Service(channel)).start();
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
