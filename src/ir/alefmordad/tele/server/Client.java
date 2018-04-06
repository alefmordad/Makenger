package ir.alefmordad.tele.server;

import ir.alefmordad.tele.util.Receiver;
import ir.alefmordad.tele.util.Sender;

import java.io.IOException;
import java.net.Socket;

public class Client extends ir.alefmordad.tele.util.Client {

    public Client(Socket socket) throws IOException, ClassNotFoundException {
        sender = new Sender(this, socket.getOutputStream());
        receiver = new Receiver(socket.getInputStream());
        init();
    }

    public void init() throws IOException, ClassNotFoundException {
        this.setUser(receiver.receiveInfoFromClient());
    }

}
