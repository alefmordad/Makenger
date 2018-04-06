package ir.alefmordad.tele.server;

import ir.alefmordad.tele.core.entities.Message;
import ir.alefmordad.tele.core.entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service implements Runnable {

    private Tunnel tunnel;
    private static List<Tunnel> tunnels = new ArrayList<>();

    public Service(Tunnel tunnel) {
        this.tunnel = tunnel;
        Service.tunnels.add(tunnel);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message msg = tunnel.getReceiver().receive();
                Tunnel destination = findClient(msg.getDestination());
                destination.getSender().send(msg);
            } catch (IOException e) {
                tunnels.remove(tunnel);
                System.out.println(tunnels.size());
                break;
            } catch (ClassNotFoundException e) {
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
