import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    private final String hostname;
    private final int port;

    public GameClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute() {
        try (Socket socket = new Socket(hostname, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {  // Use Scanner to read from standard input

            String text;
            do {
                System.out.print("Enter command: ");
                text = scanner.nextLine();  // Read the command from the user input
                writer.println(text);  // Send the command to the server

                String response = reader.readLine();  // Read the response from the server
                System.out.println(response);
            } while (!text.equalsIgnoreCase("exit"));

        } catch (IOException ex) {
            System.err.println("Client exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GameClient client = new GameClient("localhost", 1256);
        client.execute();
    }
}
