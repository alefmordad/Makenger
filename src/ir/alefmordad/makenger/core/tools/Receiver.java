package ir.alefmordad.makenger.core.tools;

import ir.alefmordad.makenger.client.Tunnel;
import ir.alefmordad.makenger.core.entities.Message;
import ir.alefmordad.makenger.core.entities.User;
import ir.alefmordad.makenger.core.manager.MessageManager;
import ir.alefmordad.makenger.core.model.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;

public class Receiver implements Runnable {

    private ObjectInputStream ois;
    private MessageManager messageManager = MessageManager.getInstance();
    private Boolean isClientSide;

    public Receiver(Client client, InputStream inputStream) throws IOException {
        this.isClientSide = client instanceof Tunnel;
        ois = new ObjectInputStream(inputStream);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = receive();
                if (isClientSide) {
                    message.setSeen(true);
                    updateMessage(message);
                }
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Message receive() throws IOException, ClassNotFoundException {
        Message message = (Message) ois.readObject();
        if (isClientSide) {
            message.setReceived(true);
            updateMessage(message);
        }
        return message;
    }

    private void updateMessage(Message message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    messageManager.update(message);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public User receiveUserInfoFromClient() throws IOException, ClassNotFoundException {
        return (User) ois.readObject();
    }

    public boolean receiveLoggedIn() throws IOException {
        return ois.readBoolean();
    }

    public void fetch(User user) throws SQLException {
        List<Message> unReceivedMessages = messageManager.fetch(user);
        unReceivedMessages.forEach(m -> System.out.println(m));
        unReceivedMessages.forEach(m -> m.setReceived(true));
        unReceivedMessages.forEach(m -> m.setSeen(true));
        unReceivedMessages.forEach(m -> updateMessage(m));
    }
}
