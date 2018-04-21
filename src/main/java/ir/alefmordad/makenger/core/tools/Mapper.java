package ir.alefmordad.makenger.core.tools;

import ir.alefmordad.makenger.core.model.Client;
import ir.alefmordad.makenger.core.entities.User;

public class Mapper {

    public Client map(String msg) {
        Client client = new Client();
        client.setUser(new User(msg.split(" ")[0]));
        return client;
    }

}
