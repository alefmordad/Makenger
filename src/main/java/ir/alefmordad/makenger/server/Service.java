package ir.alefmordad.makenger.server;

import ir.alefmordad.makenger.core.entities.Message;
import ir.alefmordad.makenger.core.entities.User;
import ir.alefmordad.makenger.core.manager.MessageManager;
import ir.alefmordad.makenger.core.manager.UserManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service implements Runnable {

    private Channel channel;
    private static List<Channel> channels = new ArrayList<>();
    private MessageManager messageManager = MessageManager.getInstance();
    private UserManager userManager = UserManager.getInstance();

    public Service(Channel channel) {
        this.channel = channel;
        Service.channels.add(channel);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message msg = channel.getReceiver().receive();
                if (!isUserValid(msg.getDestination()))
                    messageManager.delete(msg);
                else {
                    msg.setSent(true);
                    List<Channel> destinations;
                    if (msg.getDestination().getIsGroup())
                        destinations = findGroupDestinations(msg.getDestination());
                    else
                        destinations = findDestinations(msg.getDestination());
                    for (Channel destination : destinations) {
                        destination.getSender().send(msg);
                    }
                }
            } catch (IOException e) {
                channels.remove(channel);
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isUserValid(User destination) throws SQLException {
        return userManager.read(destination.getId()) != null;
    }

    private List<Channel> findDestinations(User user) {
        List<Channel> destinations = new ArrayList<>();
        for (Channel channel : channels) {
            if (channel.getUser().getId().equals(user.getId())) {
                destinations.add(channel);
            }
        }
        return destinations;
    }

    private List<Channel> findGroupDestinations(User groupUsers) throws SQLException {
        List<User> users = userManager.list(groupUsers);
        List<Channel> destinations = new ArrayList<>();
        for (User user : users)
            for (Channel channel : channels) {
                if (channel.getUser().getId().equals(user.getId())) {
                    destinations.add(channel);
                }
            }
        return destinations;
    }

}
