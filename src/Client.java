import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {

    private Sender sender;
    private Receiver receiver;

    public Sender getSender() {
        return sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public Client(Socket socket) throws IOException {
        sender = new Sender(socket.getOutputStream());
        receiver = new Receiver(socket.getInputStream());
    }

    public Client(String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);
        sender = new Sender(socket.getOutputStream());
        receiver = new Receiver(socket.getInputStream());
    }

    @Override
    public void run() {
        new Thread(sender).start();
        new Thread(receiver).start();
    }
}
