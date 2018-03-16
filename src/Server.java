import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {
    private List<Client> clients = new ArrayList<>();
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Client client = new Client(serverSocket.accept());
                clients.add(client);
                new Thread(new Service(clients)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
