package ir.alefmordad.tele.server;

import ir.alefmordad.tele.core.manager.UserManager;
import ir.alefmordad.tele.core.model.Client;
import ir.alefmordad.tele.core.tools.Receiver;
import ir.alefmordad.tele.core.tools.Sender;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class Tunnel extends Client {

    private UserManager userManager = UserManager.getInstance();

    public Tunnel(Socket socket) throws IOException, ClassNotFoundException {
        sender = new Sender(this, socket.getOutputStream());
        receiver = new Receiver(this, socket.getInputStream());
        init();
    }

    public void init() throws IOException, ClassNotFoundException {
        this.setUser(receiver.receiveInfoFromClient());
        try {
            userManager.create(getUser());
        } catch (SQLException e) {
        }
    }

}
