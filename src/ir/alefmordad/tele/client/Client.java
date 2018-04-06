package ir.alefmordad.tele.client;

import ir.alefmordad.tele.util.Receiver;
import ir.alefmordad.tele.util.Sender;
import ir.alefmordad.tele.util.User;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends ir.alefmordad.tele.util.Client {

    public Client(String ip, int port) throws IOException {
        System.out.println("Enter Id: ");
        User user = new User();
        user.setId(new Scanner(System.in).nextLine());
        this.setUser(user);

        Socket socket = new Socket(ip, port);
        sender = new Sender(this, socket.getOutputStream());
        receiver = new Receiver(socket.getInputStream());

        init();
    }

    private void init() throws IOException {
        sender.sendInfoToServer();
    }

}
