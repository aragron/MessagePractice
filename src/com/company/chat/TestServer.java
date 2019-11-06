package com.company.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一对一全双工通信 服务端
 */
public class TestServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);

        while (true) {
            Socket socket=serverSocket.accept();

            new Thread(new SendMessageService(socket,"服务器端：")).start();
            new Thread(new ReceiveMessageService(socket)).start();

            System.out.println("请输入...");

        }
    }
}
