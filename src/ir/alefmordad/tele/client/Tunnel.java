package ir.alefmordad.tele.client;

import ir.alefmordad.tele.core.model.Client;
import ir.alefmordad.tele.core.tools.Receiver;
import ir.alefmordad.tele.core.tools.Sender;
import ir.alefmordad.tele.core.entities.User;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Tunnel extends Client {

    public Tunnel(String ip, int port) throws IOException {
        signIn();

        Socket socket = new Socket(ip, port);
        sender = new Sender(this, socket.getOutputStream());
        receiver = new Receiver(socket.getInputStream());

        init();
    }

    private void signIn() {
        System.out.println("Enter Id: ");
        this.setUser(new User(new Scanner(System.in).nextLine()));
    }

    private void init() throws IOException {
        sender.sendInfoToServer();
    }

}
