package ir.alefmordad.tele.util;

public class Client implements Runnable {

    protected Sender sender;
    protected Receiver receiver;

    public Sender getSender() {
        return sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    @Override
    public void run() {
        new Thread(sender).start();
        new Thread(receiver).start();
    }
}
