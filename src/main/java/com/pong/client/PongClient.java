package com.pong.client;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author LBEVAN
 */
public class PongClient {

    private static final Logger LOGGER = Logger.getLogger(PongClient.class.getName());

    private Socket socket;

    public PongClient(String serverAddress) {
        try {
            socket = new Socket(serverAddress, 8901);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException starting client: " + e);
        }
    }

    public static void main(String[] args) {
        while(true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            PongClient client = new PongClient(serverAddress);
        }
    }
}
