package com.yiyi.thread.memoryreordering;
/*
* 指令重排序
* */
public class MemoryReorderingTest {

  public static int a = 0;
  public static int b = 0;
  public static int x = 0;
  public static int y = 0;

  public static void main(String[] args) throws InterruptedException {
    int i = 0;
    while(true){
      x = 0; y = 0;
      a = 0; b = 0;
      Thread t1 = new Thread(()->{
        a=1;
        x=b;
      });

      Thread t2 = new Thread(()->{
        b=1;
        y=a;
      });

      t1.start();
      t2.start();
      t1.join();
      t2.join();
      i++;
      if(x==0 && y==0 ){
        System.out.print("x="+x+";y="+y+";执行"+i+"次");
        break;
      }

    }
  }

}
