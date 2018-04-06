package ir.alefmordad.tele.server;

import ir.alefmordad.tele.util.Message;
import ir.alefmordad.tele.util.User;

import java.io.IOException;
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
            try {
                Message msg = client.getReceiver().receive();
                Client destination = findClient(msg.getDestination());
                destination.getSender().send(msg);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private Client findClient(User user) {
       for (Client client : clients) {
           if (client.getUser().getId().equals(user.getId())){
               return client;
           }
       }
       return null;
    }

}
