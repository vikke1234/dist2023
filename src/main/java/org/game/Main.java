package org.game;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server(8080);
            server.start();
            Server server1 = new Server(8081);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}