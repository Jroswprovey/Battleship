import java.io.*;
import java.net.*;

public class socketChat {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6969);
            System.out.println("Waiting for connection on port " + serverSocket.getLocalPort() + "...");

            System.out.println("Enter the IP address of the peer:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String peerIPAddress = reader.readLine();
            Socket socket = new Socket(peerIPAddress, serverSocket.getLocalPort());
            System.out.println("Connected to peer on port " + socket.getPort() + ".");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader messageReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Start chatting! Type 'exit' to quit.");


            Thread receiveThread = new Thread(() -> {
                try {
                    BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));
                    while (true) {
                        String message = socketReader.readLine();
                        if (message == null || message.equals("exit")) {
                            break;
                        }
                        System.out.println("Peer: " + message);
                    }
                    System.out.println("Peer has disconnected.");
                    System.exit(0);
                } catch (IOException e) {
                    System.err.println("Error receiving message: " + e.getMessage());
                    System.exit(1);
                }
            });
            receiveThread.start();

            while (true) {
                String message = messageReader.readLine();
                if (message.equals("exit")) {
                    outputStream.write("exit\n".getBytes());
                    break;
                }
                outputStream.write((message + "\n").getBytes());
            }

            // Clean up
            serverSocket.close();
            socket.close();
            messageReader.close();
            System.out.println("Chat ended.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
