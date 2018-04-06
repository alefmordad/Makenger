package ir.alefmordad.tele.core.tools;

import ir.alefmordad.tele.core.entities.Message;
import ir.alefmordad.tele.core.entities.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class Receiver implements Runnable {

    private ObjectInputStream ois;

    public Receiver(InputStream inputStream) throws IOException {
        ois = new ObjectInputStream(inputStream);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(receive());
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
            }
        }
    }

    public Message receive() throws IOException, ClassNotFoundException {
        return (Message) ois.readObject();
    }

    public User receiveInfoFromClient() throws IOException, ClassNotFoundException {
        return (User) ois.readObject();
    }
}
