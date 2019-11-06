package com.company.pipe;

import java.io.IOException;
import java.io.PipedInputStream;

public class ReceiveThread implements Runnable {
    private PipedInputStream pipedInputStream;

    public ReceiveThread() {
        this.pipedInputStream = new PipedInputStream();
    }
    @Override
    public void run() {
        byte [] data = new byte[1024];
        try {
            while (true){
                int len = this.pipedInputStream.read(data);
                System.out.println(Thread.currentThread().getName() + "线程接收到消息：" + new String(data,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.pipedInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PipedInputStream getPipedInputStream(){
        return pipedInputStream;
    }
}
