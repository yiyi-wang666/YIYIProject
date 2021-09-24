package com.yiyi.ionio.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class IoClient {

  public static void main(String[] args) {
    Socket socket = null;
    BufferedReader in = null;
    PrintWriter out = null;
    try {
      socket = new Socket("localhost",7654);

      out  = new PrintWriter(socket.getOutputStream(),true);
      out.println("测试啦1\n");
      out.flush();

      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String response = in.readLine();
      System.out.println("返回:"+response);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
