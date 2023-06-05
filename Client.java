import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the server IP address: ");
        String serverAddress = scanner.nextLine();
        int serverPort = 8080;

        try {
            // Connect to the server
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server: " + serverAddress);

            // Create input and output streams
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Create a 10x10 2D array with true or false values
            boolean[][] arrayToSend = new boolean[10][10];
            // Populate the array with some values (true or false)
            // ...

            // Send the array to the server
            outputStream.writeObject(arrayToSend);
            System.out.println("Sent array to the server.");

            // Receive acknowledgment from the server
            String acknowledgment = (String) inputStream.readObject();
            System.out.println("Server response: " + acknowledgment);

            // Close the streams and the socket
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
