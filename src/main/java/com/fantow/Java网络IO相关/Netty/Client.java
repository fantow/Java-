package com.fantow.Java网络IO相关.Netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(9988))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect().sync();

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);

//        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioServerSocketChannel serverSocketChannel = new NioServerSocketChannel();

        eventLoopGroup.register(serverSocketChannel);

        ChannelFuture future = serverSocketChannel.bind(new InetSocketAddress(9000));
        ChannelFuture sync1 = future.sync();

        ChannelPipeline pipeline = serverSocketChannel.pipeline();

        pipeline.addLast();

        ChannelFuture channelFuture = serverSocketChannel.writeAndFlush(null);

        channelFuture.sync();

        serverSocketChannel.closeFuture().sync();

        try {
            Class<?> aClass = Class.forName("com.fantow.GC相关.Cleaner");



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}


class ProxyClass implements InvocationHandler {

    public static Object target;
    public static ClassLoader classLoader;

    public ProxyClass(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){

        return Proxy.newProxyInstance(classLoader,target.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        return null;
    }
}
