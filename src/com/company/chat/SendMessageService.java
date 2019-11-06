package com.company.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SendMessageService implements Runnable {
    private Socket socket;
    private String name;

    public SendMessageService(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        PrintWriter pWriter = null;
        Scanner scanner = null;
        try {
            pWriter = new PrintWriter(socket.getOutputStream());
            scanner = new Scanner(System.in);
            while (true) {
                String s = scanner.nextLine();
                pWriter.write(name + s + "\n");
                pWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            pWriter.close();
        }

    }
}