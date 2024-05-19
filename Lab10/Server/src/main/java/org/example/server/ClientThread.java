package org.example.server;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private GameServer server;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String text;
            do {
                text = reader.readLine();
                String message = (text.equals("stop")) ? "Server stopped" : "Server received the request: " + text;
                writer.println(message);

                if (text.equals("stop")) {
                    server.stopServer();
                }
            } while (!text.equals("stop"));

            socket.close();
        } catch (IOException ex) {
            System.err.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
