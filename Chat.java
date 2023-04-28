import java.net.*;
import java.io.*;

public class Chat {
    private static final int PORT = 694201337;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                // Wait for a client to connect
                clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostName());

                // Start a new chat session
                Thread thread = new Thread(new ChatSession(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ChatSession implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ChatSession(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Ask the client if they want to join or create a chat session
                out.println("Do you want to join or create a chat session? (join/create)");
                String choice = in.readLine().trim();

                if (choice.equalsIgnoreCase("create")) {
                    // Ask the client to set a password for the chat session
                    out.println("Please enter a password for the chat session:");
                    String password = in.readLine().trim();

                    // Start a new chat session with the given password
                    startChatSession(password);
                } else if (choice.equalsIgnoreCase("join")) {
                    // Ask the client to enter the password for the chat session
                    out.println("Please enter the password for the chat session:");
                    String password = in.readLine().trim();

                    // Join an existing chat session with the given password
                    joinChatSession(password);
                } else {
                    // Invalid choice, close the connection
                    out.println("Invalid choice, closing connection.");
                    out.close();
                    in.close();
                    socket.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void startChatSession(String password) throws IOException {
            // Notify the client that a new chat session has been created
            out.println("New chat session created. Waiting for a client to join.");

            // Wait for another client to join the chat session
            Socket otherSocket = null;
            PrintWriter otherOut = null;
            BufferedReader otherIn = null;

            try {
                // Listen for incoming client connections
                ServerSocket serverSocket = new ServerSocket(0); // choose any available port
                out.println("Listening on port " + serverSocket.getLocalPort());

                // Wait for another client to connect to the chat session
                otherSocket = serverSocket.accept();
                System.out.println("Client connected to chat session: " + otherSocket.getInetAddress().getHostName());

                // Get input and output streams for the other client
                otherOut = new PrintWriter(otherSocket.getOutputStream(), true);
                otherIn = new BufferedReader(new InputStreamReader(otherSocket.getInputStream()));

                // Send the chat session password to the other client
                otherOut.println(password);

                // Start the chat session between the two clients
                while (true) {
                    // Read input from this client
                    String inputLine = in.readLine();

                    if (inputLine.equals("exit")) {
                        // Client wants to end the chat session
                        break;
                    }

                    // Send input to the other client
                    otherOut.println(inputLine);

                    // Read input from the other client
                    String otherInputLine = otherIn.readLine();

                    if (otherInputLine.equals("exit")) {
                        // Other client wants to end the chat session
                        break;
                    }

                    // Send other client's input to this client
                    out.println(otherInputLine);
                }
            } finally {
                // Close the connection and streams
                if (otherSocket != null) {
                    otherSocket.close();
                }
                if (otherOut != null) {
                    otherOut.close();
                }
                if (otherIn != null) {
                    otherIn.close();
                }
            }
        }

        private void joinChatSession(String password) throws IOException {
            // Ask the client to enter the IP address and port number of the chat session
            out.println("Please enter the IP address and port number of the chat session (ip:port):");
            String address = in.readLine().trim();
            String[] parts = address.split(":");
            String ipAddress = parts[0];
            int port = Integer.parseInt(parts[1]);

            // Connect to the chat session
            Socket chatSocket = new Socket(ipAddress, port);
            PrintWriter chatOut = new PrintWriter(chatSocket.getOutputStream(), true);
            BufferedReader chatIn = new BufferedReader(new InputStreamReader(chatSocket.getInputStream()));

            // Send the chat session password to the server
            chatOut.println(password);

            // Start the chat session with the server
            while (true) {
                // Read input from this client
                String inputLine = in.readLine();

                if (inputLine.equals("exit")) {
                    // Client wants to end the chat session
                    break;
                }

                // Send input to the server
                chatOut.println(inputLine);

                // Read input from the server
                String chatInputLine = chatIn.readLine();

                if (chatInputLine.equals("exit")) {
                    // Chat session has ended
                    out.println("The chat session has ended.");
                    break;
                }

                // Send server's input to this client
                out.println(chatInputLine);
            }

            // Close the connection and streams
            chatOut.close();
            chatIn.close();
            chatSocket.close();
        }
    }
}
