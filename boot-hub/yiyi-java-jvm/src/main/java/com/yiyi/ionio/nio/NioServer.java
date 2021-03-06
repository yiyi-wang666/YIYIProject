package com.yiyi.ionio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NioServer {

  //多路复用器
  private static Selector selector;
  //读写缓冲区
  private static ByteBuffer readBuffer = ByteBuffer.allocate(1024);
  private static ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

  public static void main(String[] args) {
    try {
      ServerSocketChannel server = ServerSocketChannel.open();
      server.socket().bind(new InetSocketAddress(7654));

      selector = Selector.open();
      server.configureBlocking(false);
      server.register(selector, SelectionKey.OP_ACCEPT);

      while(true){
        selector.select();
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        {
          while (iterator.hasNext()){
            //取出一个结果
            SelectionKey key = iterator.next();

            //判断是否是有效的请求
            if(key.isValid()){
              if (key.isAcceptable()) {
                accept(key);
              }

              if (key.isReadable()){
                read(key);
//                write(key);
              }
            }
            //移除
            iterator.remove();
          }
        }
      }

    }catch(Exception e){
      e.printStackTrace();
    }

  }


  private static void accept(SelectionKey key){
    try {
      //获取当前服务器通道
      ServerSocketChannel channel = (ServerSocketChannel) key.channel();
      //切换到客户端通道
      SocketChannel sc = channel.accept();
      //设置非阻塞
      sc.configureBlocking(false);
      //注册一个读事件，留意该客户端是否有数据进来
      sc.register(selector,SelectionKey.OP_READ,writeBuffer);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void write(SelectionKey key){
    SocketChannel socketChannel = (SocketChannel) key.channel();
    writeBuffer.clear();
    writeBuffer.put("你好客户端...".getBytes());
    writeBuffer.flip();// 这一步必须有

    try {
      socketChannel.write(writeBuffer);
    } catch (IOException e) {
      //当客户端出现连接异常的时候，关闭当前客户端通道，取消缓存key
      try {
        socketChannel.close();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      key.cancel();
//            e.printStackTrace();
    }
  }

  private static void forwardOtherClient(String message, SocketChannel self) {
    try {
      for (SelectionKey key : selector.keys()) {
        Channel channel = key.channel();
        if (channel instanceof SocketChannel && channel != self) {
          SocketChannel dest = (SocketChannel) channel;
          dest.write(ByteBuffer.wrap(message.getBytes()));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static void read(SelectionKey key){
    readBuffer.clear();
    //accept方法中注册的客户端通道
    SocketChannel channel = (SocketChannel) key.channel();
    try {
      //读取数据
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      int count = channel.read(readBuffer);
      //int count = channel.read(readBuffer);
      //判读是否有数据可读
      if(count == -1){
        key.channel().close();
        key.cancel();
        return;
      }
      //读取
//      String msg = new String(buffer.array());
//      System.out.println(msg + " from " + channel.getRemoteAddress());
      // 服务器向其他客户端转发消息
//      forwardOtherClient(msg, channel);

      readBuffer.flip();
      byte[] bytes = new byte[readBuffer.remaining()];
      readBuffer.get(bytes);
      String body = new String(bytes).trim();
      System.out.println("收到信息：" + body);
      write(key);
    } catch (IOException e) {
      System.out.println("read exception");
      try {
        //当客户端出现连接异常的时候，关闭当前客户端通道，取消缓存key
        channel.close();
        key.cancel();
      } catch (IOException e1) {
        System.out.println("read close exception");
        e1.printStackTrace();
      }
      //e.printStackTrace();
    }finally {
      try {
        //当客户端出现连接异常的时候，关闭当前客户端通道，取消缓存key
        channel.close();
        key.cancel();
      } catch (IOException e1) {
        System.out.println("read close finally exception");
        e1.printStackTrace();
      }
    }
  }

}
