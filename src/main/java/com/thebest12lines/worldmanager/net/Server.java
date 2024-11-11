package com.thebest12lines.worldmanager.net;

import com.thebest12lines.worldmanager.annotation.CoreClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/**
 * The server class associated with inter-process communication with C++ libraries.
 * @author thebest12lines
 */

@CoreClass
public class Server {
    private ArrayList<Socket> clientSockets;
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        if (clientSockets == null) {
            clientSockets = new ArrayList<>();
        }
        serverSocket = new ServerSocket(port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            clientSockets.add(clientSocket);
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }

    public void listen(String handleType, BiConsumer<Socket, String> handler) {
        ClientHandler.handlerMap.put(handleType, handler);
    }

    public void stop() throws IOException {
        if (clientSockets == null || serverSocket == null) {
            throw new RuntimeException("Client socket or server socket is null!");
        }
        for (Socket clientSocket : clientSockets) {
            clientSocket.close();
        }
        serverSocket.close();
    }

    private static class ClientHandler implements Runnable {
        private static final java.util.Map<String, BiConsumer<Socket, String>> handlerMap = new java.util.HashMap<>();
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String message;

                while ((message = in.readLine()) != null) {
                    // Check for handle type in message
                    String[] parts = message.split(":");
                    if (parts.length > 1) {
                        String handleType = parts[0];
                        String payload = parts[1];
                        BiConsumer<Socket, String> handler = handlerMap.get(handleType);
                        if (handler != null) {
                            handler.accept(clientSocket, payload);
                        } else {
                            out.println("Unknown handler: " + handleType);
                        }
                    } else {
                        out.println("Invalid message format");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.listen("handler", (socket, message) -> {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                out.println(Integer.parseInt(message)*Integer.parseInt(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        server.start(1234);
    }
}
