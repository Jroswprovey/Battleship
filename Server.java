import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    private boolean[][] storedArray; // The stored 2D array in the server

    public Server() {
        storedArray = new boolean[10][10];

        // Initialize all spots in the array to false
        for (boolean[] booleans : storedArray) {
            Arrays.fill(booleans, false);
        }
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started and listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostName());

                // Create input and output streams
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                // Receive the 2D array from the client
                boolean[][] receivedArray = (boolean[][]) inputStream.readObject();

                // Replace the stored 2D array with the received array
                synchronized (storedArray) {
                    storedArray = receivedArray.clone();
                }

                System.out.println("Received array from the client and replaced the stored array.");

                // Send acknowledgment back to the client
                outputStream.writeObject("Array received and stored successfully.");

                // Close the streams and the socket
                inputStream.close();
                outputStream.close();
                socket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static String getIp() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80));
        socket.close();
        return ("Server IP: " + String.valueOf(socket.getLocalAddress()).substring(1));
    }
    public static void startServer() throws IOException {
        Server server = new Server();
        server.start(8080);
        System.out.println("Server Started: " + getIp());
    }
}