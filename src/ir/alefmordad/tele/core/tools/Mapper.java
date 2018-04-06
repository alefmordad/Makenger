package ir.alefmordad.tele.core.tools;

import ir.alefmordad.tele.core.model.Client;
import ir.alefmordad.tele.core.entities.User;

public class Mapper {

    public Client map(String msg) {
        String id = msg.split(" ")[0];
        Client client = new Client();
        client.setUser(new User(id));
        return client;
    }

}
