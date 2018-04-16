package ir.alefmordad.makenger.server;

import ir.alefmordad.makenger.core.entities.User;
import ir.alefmordad.makenger.core.util.exceptions.FakeUserException;
import ir.alefmordad.makenger.core.manager.UserManager;
import ir.alefmordad.makenger.core.model.Client;
import ir.alefmordad.makenger.core.tools.Receiver;
import ir.alefmordad.makenger.core.tools.Sender;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class Channel extends Client {

    private UserManager userManager = UserManager.getInstance();

    public Channel(Socket socket) throws IOException, ClassNotFoundException, SQLException {
        sender = new Sender(this, socket.getOutputStream());
        receiver = new Receiver(this, socket.getInputStream());
        init();
    }

    public void init() throws IOException, ClassNotFoundException, SQLException {
        setUser(receiver.receiveUserInfoFromClient());
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
