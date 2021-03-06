package ir.alefmordad.makenger.core.model;

import ir.alefmordad.makenger.core.entities.User;
import ir.alefmordad.makenger.core.tools.Receiver;
import ir.alefmordad.makenger.core.tools.Sender;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        return user.equals(client.user);
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }

    @Override
    public void run() {
        new Thread(sender).start();
        new Thread(receiver).start();
    }

}
