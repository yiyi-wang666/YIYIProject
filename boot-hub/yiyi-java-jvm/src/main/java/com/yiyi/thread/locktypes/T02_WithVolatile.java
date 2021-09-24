package com.yiyi.thread.locktypes;

import java.util.LinkedList;
import java.util.List;

public class T02_WithVolatile {

  volatile List list = new LinkedList<>();

  public synchronized void add(Object o){
    list.add(o);
  }

  public int size(){
    return list.size();
  }

  public static void main(String[] args) {
    T02_WithVolatile withVolatile = new T02_WithVolatile();
    Thread t1 = new Thread(()->{
      for(int i=0;i<10;i++){
        System.out.println("线程1 : "+i);
        withVolatile.add(new Object());

      }
    },"T1");

    Thread t2 = new Thread(()->{
      while(true){
        if(withVolatile.size()==5){
          break;
        }

      }
      System.out.println("线程2 结束");
    },"T2");

    t1.start();
    t2.start();
  }

}