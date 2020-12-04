package com.fantow.Java网络IO相关.NIO;

import java.io.*;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.0.65",9999);


        OutputStream outputStream = socket.getOutputStream();

        InputStream is = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        while(true){
            String str = reader.readLine();

            writer.write(str);
            System.out.println("发送...");
        }
    }
}
