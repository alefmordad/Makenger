package ir.alefmordad.tele.util;

public class Mapper {

    public Client map(String msg) {
        String id = msg.split(" ")[0];
        Client client = new Client();
        User user = new User();
        user.setId(id);
        client.setUser(user);
        return client;
    }

}
