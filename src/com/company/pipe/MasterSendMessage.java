package com.company.pipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedOutputStream;

public class MasterSendMessage {
    public static void main(String[] args) throws IOException {

        ReceiveThread receiveThread = new ReceiveThread();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        pipedOutputStream.connect(receiveThread.getPipedInputStream());
        new Thread(receiveThread,"【消息接收子线程】").start();

        try {
            while (true) {
                Thread.sleep(1000);
                System.out.println("请输入信息：");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String message = br.readLine();
                System.out.println(Thread.currentThread().getName()+"主线程接收到控制台输入："+message);
                //pipedOutputStream.write((Thread.currentThread().getName() + "信息发送：" + message + "\r\n").getBytes());
                pipedOutputStream.write(( message + "\r\n").getBytes());
                pipedOutputStream.flush();
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        try {
            pipedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
