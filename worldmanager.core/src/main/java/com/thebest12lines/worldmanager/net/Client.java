package com.thebest12lines.worldmanager.net;

import worldmanager.features.internal.CoreClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * An unused "client" class for the inter-process communication associated with worldmanager.
 * @author thebest12lines
 */
@CoreClass
public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage(String msg) throws IOException {
        out.println(msg);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Client client = new Client();
        client.startConnection("127.0.0.1", 53067); // Make sure this IP and port match your server
        while (true) {
            try {
                int input = sc.nextInt();
                System.out.println(input);
                if (input == 1) {
                    System.out.println("as");
                    client.sendMessage("action:guiStart"); // Example of sending a message to the server
                } else if (input == 2) {
                    System.out.println("f");
                    client.sendMessage("action:guiStop");
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }


        }


    }
}
