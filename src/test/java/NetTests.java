import static org.junit.jupiter.api.Assertions.assertEquals;

import org.game.Client;
import org.game.Server;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class NetTests {
    @Test
    void connectionShouldPass() {
        try {
            Server server = new Server(8080);
            server.start();
            Client c1 = new Client("127.0.0.1", 8080);
            System.out.println("c1");
            c1.get_config();
            Client c2 = new Client("127.0.0.1", 8080);

            System.out.println("c2");
            c2.get_config();
            server.stop_server();

        } catch (IOException e) {
            assert(false);
        }

    }
}
