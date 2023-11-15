package org.game;
import java.net.*;
import java.io.*;


public class Client {
    private Socket sock;

    public Client(String ip, int port) throws IOException {
        this(new Socket(ip, port));
    }

    public Client(Socket server) {
        sock = server;
    }

    public String get_config() throws IOException {
        //PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        String s = in.readLine();
        while(!s.equals("")) {
            System.out.println(s);
            s = in.readLine();
        }
        return "";
    }
}