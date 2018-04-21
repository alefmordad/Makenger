package ir.alefmordad.makenger.client;

import ir.alefmordad.makenger.core.entities.User;
import ir.alefmordad.makenger.core.util.exceptions.FakeUserException;
import ir.alefmordad.makenger.core.model.Client;
import ir.alefmordad.makenger.core.tools.Receiver;
import ir.alefmordad.makenger.core.tools.Sender;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class Tunnel extends Client {

    public Tunnel(String ip, int port) throws IOException, SQLException {
        readUserInfo();
        Socket socket = new Socket(ip, port);
        sender = new Sender(this, socket.getOutputStream());
        receiver = new Receiver(this, socket.getInputStream());
        signIn();
        receiver.fetch(this.user);
    }

    private void readUserInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Id : ");
        User user = new User(scanner.nextLine());
        System.out.print("Enter Password : ");
        user.setPassword(scanner.nextLine());

        this.setUser(user);
    }

    private void signIn() throws IOException {
        sender.sendUserInfoToServer();
        if (!receiver.receiveLoggedIn())
            throw new FakeUserException(user);
    }

}
