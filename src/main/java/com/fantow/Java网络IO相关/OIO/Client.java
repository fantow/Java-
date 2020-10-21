package com.fantow.Java网络IO相关.OIO;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",9999);

//        InputStream is = socket.getInputStream();
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        while(true){
            System.out.println("输入：");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            writer.write(br.readLine());

            writer.close();
        }
    }
}
