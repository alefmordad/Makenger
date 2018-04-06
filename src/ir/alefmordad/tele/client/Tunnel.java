package ir.alefmordad.tele.client;

import ir.alefmordad.tele.util.Receiver;
import ir.alefmordad.tele.util.Sender;
import ir.alefmordad.tele.util.User;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Tunnel extends ir.alefmordad.tele.util.Client {

    public Tunnel(String ip, int port) throws IOException {
        signUp();

        Socket socket = new Socket(ip, port);
        sender = new Sender(this, socket.getOutputStream());
        receiver = new Receiver(socket.getInputStream());

        init();
    }

    private void signUp() {
        System.out.println("Enter Id: ");
        User user = new User();
        user.setId(new Scanner(System.in).nextLine());
        this.setUser(user);
    }

    private void init() throws IOException {
        sender.sendInfoToServer();
    }

}
