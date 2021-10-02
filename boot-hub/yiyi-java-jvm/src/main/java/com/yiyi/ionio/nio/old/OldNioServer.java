package com.yiyi.ionio.nio.old;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OldNioServer {
    public static void main(String[] args) {
        try {
            List<SocketChannel> channelList = new ArrayList<>();
            ServerSocketChannel server = ServerSocketChannel.open();
            server.socket().bind(new InetSocketAddress(7654));
            server.configureBlocking(false);

            while(true){
                Thread.sleep(1000);
                SocketChannel socketChannel = server.accept();
                if(socketChannel!=null){
                    socketChannel.configureBlocking(false);
                    channelList.add(socketChannel);
                }

                Iterator<SocketChannel> iterator = channelList.iterator();
                {
                    while (iterator.hasNext()){
                        SocketChannel sc = iterator.next();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                        int len = sc.read(byteBuffer);
                        if(len>0){
                            System.out.println("接受消息："+new String(byteBuffer.array()));
                        }else if(len == -1){
                            iterator.remove();
                            System.out.println("server 断开连接");
                        }
                    }
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
