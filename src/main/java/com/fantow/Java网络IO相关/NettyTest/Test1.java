package com.fantow.Java网络IO相关.NettyTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;

public class Test1 {
    public static void main(String[] args) throws IOException {

        String file = "111.txt";

//        InputStream is = new BufferedInputStream(new FileInputStream(file));

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        bossGroup.execute(()->{
            System.out.println(11);
        });

        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);

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


    public static void clientMode(){

        NioEventLoopGroup workerGroup = new NioEventLoopGroup(8);

        Bootstrap bootstrap = new Bootstrap();

        NioSocketChannel channel = new NioSocketChannel();

//        channel.writeAndFlush();


    }

}
