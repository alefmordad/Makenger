package ir.alefmordad.tele.server;

import ir.alefmordad.tele.core.entities.Message;
import ir.alefmordad.tele.core.entities.User;
import ir.alefmordad.tele.core.manager.MessageManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service implements Runnable {

    private Tunnel tunnel;
    private static List<Tunnel> tunnels = new ArrayList<>();
    private MessageManager messageManager = MessageManager.getInstance();

    public Service(Tunnel tunnel) {
        this.tunnel = tunnel;
        Service.tunnels.add(tunnel);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message msg = tunnel.getReceiver().receive();
                msg.setSent(true);
                List<Tunnel> destinations = findDestinations(msg.getDestination());
                for (Tunnel destination : destinations) {
                    destination.getSender().send(msg);
                }
                if (destinations.size() == 0)
                    messageManager.delete(msg.getId());
            } catch (IOException e) {
                tunnels.remove(tunnel);
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Tunnel> findDestinations(User user) {
        List<Tunnel> destinations = new ArrayList<>();
        for (Tunnel tunnel : tunnels) {
            if (tunnel.getUser().getId().equals(user.getId())) {
                destinations.add(tunnel);
            }
        }
        return destinations;
    }

}
