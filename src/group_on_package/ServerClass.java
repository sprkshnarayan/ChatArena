package group_on_package;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.*;

public class ServerClass {

    public static Map<String, Socket> map = new HashMap<String, Socket>();

    public static void main(String args[]) {
        try {
            ServerSocket listenSocket = new ServerSocket(5000);
            System.out.println("Server Started and listening for client");
            while (true) {
                Socket socket = listenSocket.accept();
                System.out.println("connection established\n");
                new ServerThread(socket).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
