package com.fantow.Java网络IO相关.NettyTest;

import io.netty.buffer.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.*;
import java.nio.charset.Charset;

public class Test1 {
    public static void main(String[] args) throws IOException {

        String file = "111.txt";

//        InputStream is = new BufferedInputStream(new FileInputStream(file));

        System.out.println("输入:");
        InputStream is1 = new BufferedInputStream(System.in);
        byte[] bytes = new byte[1024];
        int len = -1;
        while(is1.read(bytes) != -1){
            System.out.println(new String(bytes));
        }

        Channel channel = new NioServerSocketChannel();
        channel.eventLoop().execute(null);

    }
}
