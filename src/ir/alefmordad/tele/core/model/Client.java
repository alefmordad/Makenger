package ir.alefmordad.tele.core.model;

import ir.alefmordad.tele.core.entities.User;
import ir.alefmordad.tele.core.tools.Receiver;
import ir.alefmordad.tele.core.tools.Sender;

import java.io.Serializable;

public class Client implements Runnable, Serializable {

    protected User user;
    protected Sender sender;
    protected Receiver receiver;

    public Sender getSender() {
        return sender;
    }
    public Receiver getReceiver() {
        return receiver;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        new Thread(sender).start();
        new Thread(receiver).start();
    }

}
