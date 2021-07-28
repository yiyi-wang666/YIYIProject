package com.yiyi.ionio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NioServer {

  public static void main(String[] args) {
    try {
      ServerSocketChannel server = ServerSocketChannel.open();
      server.socket().bind(new InetSocketAddress(7654));

    }catch(Exception e){
      e.printStackTrace();
    }

  }

}
