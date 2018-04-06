package ir.alefmordad.tele.server;

import ir.alefmordad.tele.core.entities.Message;
import ir.alefmordad.tele.core.entities.User;

import java.io.IOException;
import java.util.List;

public class Service implements Runnable {

    private Tunnel tunnel;
    private List<Tunnel> tunnels;

    public Service(List<Tunnel> tunnels) {
        this.tunnel = tunnels.get(tunnels.size() - 1);
        this.tunnels = tunnels;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message msg = tunnel.getReceiver().receive();
                Tunnel destination = findClient(msg.getDestination());
                destination.getSender().send(msg);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private Tunnel findClient(User user) {
       for (Tunnel tunnel : tunnels) {
           if (tunnel.getUser().getId().equals(user.getId())){
               return tunnel;
           }
       }
       return null;
    }

}
