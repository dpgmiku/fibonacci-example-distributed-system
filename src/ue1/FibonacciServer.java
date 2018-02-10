/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ue1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miku
 */
public class FibonacciServer extends Thread {

    final static int PORT_NUMBER = 5678;

    private static int fibonacci(int n) {
        if (n < 0 || n > 40) {
            throw new IllegalArgumentException("negative n ist bei den Folgen nicht zugelassen");
        }
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static void handleConnection(Socket client) {
        try {
            final DataInputStream in = new DataInputStream(client.getInputStream());
            final DataOutputStream out = new DataOutputStream(client.getOutputStream());
            try {

                Integer zahl = in.readInt();
                System.out.println(zahl);
                out.writeInt(fibonacci(zahl));
               

            } catch (IllegalArgumentException e) {
                out.writeInt(-2);
                System.out.println("ungültiger Zahlenbereich");
            } catch (EOFException e) {
             System.out.println("Client disconnected");
            }

        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {

        ServerSocket serverSocket;
        System.out.println("Server versucht zum starten");

        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            System.out.println("Server gestartet");

            while (true) {
                Socket client = null;
                try {
                    client = serverSocket.accept();
                    System.out.println("Client accepted");

                    handleConnection(client);
                } catch (IOException e) {

                } finally {
                    if (client != null) {
                        try {
                            client.close();
                        } catch (IOException e) {
                        }
                    }

                }

            }
        } catch (IOException ex) {

        }

    }

}
