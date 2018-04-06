package ir.alefmordad.makenger.core.tools;

import ir.alefmordad.makenger.core.entities.Message;
import ir.alefmordad.makenger.core.manager.MessageManager;
import ir.alefmordad.makenger.core.model.Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Sender implements Runnable {

    private ObjectOutputStream oos;
    private Scanner console = new Scanner(System.in);
    private Client client;
    private MessageManager messageManager = MessageManager.getInstance();

    public Sender(Client client, OutputStream outputStream) throws IOException {
        this.client = client;
        this.oos = new ObjectOutputStream(outputStream);
    }

    @Override
    public void run() {
        while (true) {
            try {
                String content = console.nextLine();
                Message message = new Message();
                message.setContent(content.substring(content.indexOf(" ") + 1));
                message.setDate(new Date());
                message.setSource(client.getUser());
                message.setDestination(new Mapper().map(content).getUser());
                messageManager.create(message);
                send(message);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(Message message) throws IOException {
        this.oos.writeObject(message);
        this.oos.flush();
    }

    public void sendUserInfoToServer() throws IOException {
        this.oos.writeObject(client.getUser());
        this.oos.flush();
    }

    public void sendLoggedIn(boolean b) throws IOException {
        oos.writeBoolean(b);
        oos.flush();
    }
}
