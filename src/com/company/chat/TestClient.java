package com.company.chat;

import java.io.IOException;
import java.net.Socket;

/**
 * 一对一全双工通信 客户端
 */
public class TestClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8081);

            new Thread(new SendMessageService(socket,"客户端：")).start();
            new Thread(new ReceiveMessageService(socket)).start();

            System.out.println("请输入...");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
