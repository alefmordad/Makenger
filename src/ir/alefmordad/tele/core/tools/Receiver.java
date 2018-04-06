package ir.alefmordad.tele.core.tools;

import ir.alefmordad.tele.client.Tunnel;
import ir.alefmordad.tele.core.entities.Message;
import ir.alefmordad.tele.core.entities.User;
import ir.alefmordad.tele.core.manager.MessageManager;
import ir.alefmordad.tele.core.model.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.SQLException;

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
        try {
            messageManager.update(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User receiveInfoFromClient() throws IOException, ClassNotFoundException {
        return (User) ois.readObject();
    }

    public boolean receiveLoggedIn() throws IOException {
        return ois.readBoolean();
    }
}
