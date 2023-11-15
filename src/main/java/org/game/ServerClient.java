package org.game;
import java.io.*;
import java.net.*;

public class ServerClient extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Server server;

    private boolean shouldRun = true;

    public ServerClient(Server server, Socket sock) throws IOException {
        clientSocket = sock;
        this.server = server;
        out = new PrintWriter(sock.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    }

    @Override
    public void run() {
        // First thing that is sent is the configuration the server currently holds
        for (ServerClient s : server.get_clients()) {
            out.println(s.clientSocket.getRemoteSocketAddress());
        }
        out.println();
    }

    public void stop_client() throws IOException {
        shouldRun = false;
        in.close();
        out.close();
        clientSocket.close();
    }
}
