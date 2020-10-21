package com.fantow.Java网络IO相关.OIO;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(9999);

        System.out.println("服务端启动");
        while(true){
            Socket accept = socket.accept();

            System.out.println("连接到客户端");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(accept);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }

    public static void handler(Socket socket) throws IOException {

        byte[] bytes = new byte[1024];
        InputStream is = socket.getInputStream();

        while(true){
            int len = -1;
            while((len = is.read(bytes) ) != -1){
                System.out.println("客户端发送消息： " + new String(bytes));
            }
        }

    }
}
