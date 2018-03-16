import java.util.List;

public class Service implements Runnable {

    private Client client;
    private List<Client> clients;

    public Service(List<Client> clients) {
        this.client = clients.get(clients.size() - 1);
        this.clients = clients;
    }

    @Override
    public void run() {
        while (true) {
            String msg = client.getReceiver().receive();
            Client destination = findDestination(msg);
            destination.getSender().send(msg);
        }
    }

    private Client findDestination(String msg) {
        String letter = msg.split(" ")[0];
        Integer index = Integer.valueOf(letter);
        return clients.get(index);
    }
}
