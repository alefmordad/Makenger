package ir.alefmordad.tele.util;

import java.io.Serializable;

public class Client implements Runnable, Serializable {

    private User user;
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
