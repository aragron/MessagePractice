package com.company.pipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedOutputStream;

public class SendThread implements Runnable {
    private PipedOutputStream pipedOutputStream;
    public SendThread(){
        this.pipedOutputStream = new PipedOutputStream();
    }
    public PipedOutputStream getPipedOutputStream(){
        return pipedOutputStream;
    }
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                System.out.println("请输入信息：");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String message = br.readLine();
                this.pipedOutputStream.write((Thread.currentThread().getName() + "信息发送：" + message + "\r\n").getBytes());
                this.pipedOutputStream.flush();
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        try {
            this.pipedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
