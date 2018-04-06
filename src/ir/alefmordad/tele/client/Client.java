package ir.alefmordad.tele.client;

import ir.alefmordad.tele.util.Receiver;
import ir.alefmordad.tele.util.Sender;

import java.io.IOException;
import java.net.Socket;

public class Client extends ir.alefmordad.tele.util.Client {

    public Client(String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);
        sender = new Sender(socket.getOutputStream());
        receiver = new Receiver(socket.getInputStream());
    }

}
