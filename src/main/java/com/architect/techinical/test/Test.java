package com.architect.techinical.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());

            Socket socket = null;
            try {
                socket = new Socket("localhost", 9090);
            } catch (IOException e) {
                e.printStackTrace();
            }
            OutputStream outputStream = null;
            try {
                outputStream = socket != null ? socket.getOutputStream() : null;
            } catch (IOException e) {
                e.printStackTrace();
            }

            //PrintWriter out=new PrintWriter(outputStream);
            for (int i=0; i<5; i++) {
                System.out.println("===>" + i);
                try {
                    outputStream.write(("msg"+i+"\n").getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                    Thread.sleep(1000L);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            /*out.println("msg"+i+"\n");
            out.flush();*/
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        for (int i=0;i<100;i++) {
            new Thread(runnable).start();
            new Thread(runnable).start();
            new Thread(runnable).start();
            new Thread(runnable).start();
            new Thread(runnable).start();
            new Thread(runnable).start();
            new Thread(runnable).start();
            new Thread(runnable).start();
            new Thread(runnable).start();
            new Thread(runnable).start();
        }


    }
}

