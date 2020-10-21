package com.fantow.Java网络IO相关.NIO;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel channel1 = SocketChannel.open();
        channel1.connect(new InetSocketAddress("localhost",9998));

        while(true){
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            buffer.put("abc123".getBytes());
            buffer.flip();
            channel1.write(buffer);
        }
    }
}
