import ir.alefmordad.makenger.client.Main;
import ir.alefmordad.makenger.server.Serve;

import java.io.IOException;
import java.sql.SQLException;

public class CentralMain {
    public static void main(String[] args) throws IOException, SQLException {
        new Serve().main(null);
        new Main().main(null);
    }
}
