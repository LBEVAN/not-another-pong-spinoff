package com.pong.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author LBEVAN
 */
public class PongServer {

    private static final Logger LOGGER = Logger.getLogger(PongServer.class.getName());

    public static void main(String[] args) throws IOException {
        ServerSocket listener = null;
        try {
            LOGGER.log(Level.INFO, "Starting server.....");
            listener = new ServerSocket(8901);
            LOGGER.log(Level.INFO, "Server is running");

            while(true) {
                Socket socket = listener.accept();
                System.out.println(socket.toString());
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException on server: " + e);
        } finally {
            listener.close();
        }
    }
}
