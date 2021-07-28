package com.yiyi.thread.cacheline;

public class CacheLineTest {

  private static class T {
    public volatile long x = 0L;
  }

  public static T[] arr = new T[2];

  static{
    arr[0] = new T();
    arr[1] = new T();
  }


  public static void main(String[] args) throws InterruptedException {


      Thread t1 = new Thread(() -> {
        for(long i=0L;i<1000_0000L;i++) {
          arr[0].x = i;
        }
      });

      Thread t2 = new Thread(() -> {
        for(long i=0L;i<1000_0000L;i++) {
          arr[1].x = i;
        }
      });

      long startTime = System.currentTimeMillis();
      t1.start();
      t2.start();
      t1.join();
      t2.join();
      long endTime = System.currentTimeMillis();

       System.out.print(endTime-startTime);
  }

}
