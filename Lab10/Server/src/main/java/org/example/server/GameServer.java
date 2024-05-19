package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 1256;
    private boolean running = true;

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (running) {
                Socket socket = serverSocket.accept();
                new ClientThread(socket, this).start();
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void stopServer() {
        this.running = false;
        System.out.println("Server stopped.");
    }

    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.startServer();
    }
}
