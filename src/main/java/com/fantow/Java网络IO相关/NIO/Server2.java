package com.fantow.Java网络IO相关.NIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);

        while(true){

            Socket socket = server.accept();
            System.out.println("建立连接...");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                        while(true){
                            System.out.println("阻塞...");
                            String line = reader.readLine();
                            System.out.println("收到：" + line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }



    }
}
