/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group_on_package;

import java.net.Socket;

/**
 *
 * @author SURAJ PRAKASH
 */
public class ServerThread extends Thread {

    private Socket socket = null;
    // map

    public ServerThread(Socket st) {
        this.socket = st;
    }

    public void run() {
        try {
            ServerReceiveThread receivingThread = new ServerReceiveThread(socket);
            //Starting thread that will continuosly listen for any input from server            
            receivingThread.start();

        } catch (Exception e) {

        }
    }
}
