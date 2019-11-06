package com.company.pipe;

import java.io.IOException;

/**
 * 一个线程键盘输入，另一个线程打印
 */
public class PipeDemo {
    public static void main(String[] args) throws IOException {
        SendThread sendThread = new SendThread();
        ReceiveThread receiveThread = new ReceiveThread();

        sendThread.getPipedOutputStream().connect(receiveThread.getPipedInputStream());

        new Thread(sendThread,"消息发送线程！").start();

        new Thread(receiveThread,"消息接收线程！").start();

    }
}
