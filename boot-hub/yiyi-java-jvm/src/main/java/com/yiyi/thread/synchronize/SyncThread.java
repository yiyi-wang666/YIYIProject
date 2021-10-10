package com.yiyi.thread.synchronize;

public class SyncThread implements Runnable{
  @Override
  public void run() {
    synchronized(SyncThread.class) {
      this.testSyncMethod();
    }
  }

  public void testSyncMethod(){
    System.out.println(Thread.currentThread().getName()
    +"======="+Thread.currentThread().getId());
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName()
            +"====end==="+Thread.currentThread().getId());
  }
}
