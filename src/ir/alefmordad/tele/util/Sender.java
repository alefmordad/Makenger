package ir.alefmordad.tele.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Scanner;

public class Sender implements Runnable {

    private ObjectOutputStream oos;
    private Scanner console = new Scanner(System.in);
    private Client client;

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
                send(message);
            } catch (IOException e) {
            }
        }
    }

    public void send(Message message) throws IOException {
        this.oos.writeObject(message);
        this.oos.flush();
    }

    public void sendInfoToServer() throws IOException {
        this.oos.writeObject(client.getUser());
        this.oos.flush();
    }
}
