package ir.alefmordad.tele.server;

import ir.alefmordad.tele.core.entities.User;
import ir.alefmordad.tele.core.exceptions.FakeUserException;
import ir.alefmordad.tele.core.manager.UserManager;
import ir.alefmordad.tele.core.model.Client;
import ir.alefmordad.tele.core.tools.Receiver;
import ir.alefmordad.tele.core.tools.Sender;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class Tunnel extends Client {

    private UserManager userManager = UserManager.getInstance();

    public Tunnel(Socket socket) throws IOException, ClassNotFoundException, SQLException {
        sender = new Sender(this, socket.getOutputStream());
        receiver = new Receiver(this, socket.getInputStream());
        init();
    }

    public void init() throws IOException, ClassNotFoundException, SQLException {
        setUser(receiver.receiveInfoFromClient());
        User readUser = userManager.read(user.getId());
        if (readUser == null)
            signUp();
        else
            authenticate(readUser);
        sender.sendLoggedIn(true);
    }

    private void authenticate(User readUser) throws IOException {
        if (!readUser.getPassword().equals(user.getPassword())) {
            sender.sendLoggedIn(false);
            throw new FakeUserException(user);
        }
    }

    private void signUp() throws SQLException {
        userManager.create(user);
    }

}
