package org.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Server extends Thread {
    private final ServerSocket serverSocket;
    private PrintWriter out;
    private BufferedReader in;

    private final HashSet<String> clients;
    private final List<ServerClient> clientList;

    private boolean shouldRun = true;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clients = new HashSet<>();
        clientList = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while(shouldRun) {
                Socket s = serverSocket.accept();
                new_client(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop_server() throws IOException {
        shouldRun = false;
        for (ServerClient s : clientList) {
            s.stop_client();
        }
    }

    public List<ServerClient> get_clients() {
        return clientList;
    }

    public void connect(String ip, int port) throws IOException {
        new_client(new Socket(ip, port));
    }

    private void new_client(Socket s) throws IOException {
        PrintWriter writer = new PrintWriter(s.getOutputStream());
        writer.println(serverSocket.getLocalSocketAddress());
        clients.add(s.getRemoteSocketAddress().toString());
        ServerClient client = new ServerClient(this, s);
        clientList.add(client);
        client.start();
    }
}
