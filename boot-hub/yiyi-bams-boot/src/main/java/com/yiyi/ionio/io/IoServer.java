package com.yiyi.ionio.io;

import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class IoServer {



  static int port = 8070;

  public static void main(String[] args) {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(port);
      while(true) {
        Socket socket = serverSocket.accept();

        System.out.println("server start...");
        //获取请求数据
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientInputStr = input.readLine();
        System.out.println("请求数据：" + clientInputStr);
        // 向客户端回复信息
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(clientInputStr + "return");
        out.flush();

        out.close();
        input.close();
      }
//      new Thread(new HandlerThread(socket)).start();

    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      if (serverSocket !=null) {
        try {
          serverSocket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      serverSocket = null;
    }

  }

  private static class HandlerThread implements Runnable {
    private Socket socket;
    public HandlerThread(Socket client) {
      socket = client;
    }

    @SneakyThrows
    @Override
    public void run() {
      try {
        //获取请求数据
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientInputStr =  input.readLine();
        System.out.println("请求数据："+clientInputStr);
        // 向客户端回复信息
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        out.print(clientInputStr+"return");

        out.close();
        input.close();
      } catch (Exception e) {
        e.printStackTrace();
      }finally {
        if (socket != null) {
          try {
            socket.close();
          } catch (Exception e) {
            socket = null;
            System.out.println("服务端 finally 异常:" + e.getMessage());
          }
        }
      }



    }
  }

}
