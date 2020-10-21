package com.fantow.Java网络IO相关.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class Server {

    private Selector selector;

    ServerSocketChannel channel;

    public Server() throws IOException {
        channel = ServerSocketChannel.open();
        selector = Selector.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(9998));
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException {
        System.out.println("服务端启动成功...");

        while(true){
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()){
                    System.out.println("创建连接");
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    System.out.println("server:" + server);
                    SocketChannel accept = server.accept();
                    accept.configureBlocking(false);
                    accept.register(selector,SelectionKey.OP_READ);
                }
                if(key.isReadable()){
                    System.out.println("准备读取消息...");
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);
                    byteBuffer.flip();
                    System.out.println("接收到消息：");
                    while(byteBuffer.hasRemaining()){
                        System.out.println(byteBuffer.get());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.listen();

    }

}
