package com.company.chat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveMessageService implements Runnable {
    private Socket socket;

    public ReceiveMessageService(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(socket.getInputStream());
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}