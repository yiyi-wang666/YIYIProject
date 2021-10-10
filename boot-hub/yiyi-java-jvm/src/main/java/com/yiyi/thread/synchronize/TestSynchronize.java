package com.yiyi.thread.synchronize;

public class TestSynchronize {

  public static void main(String[] args) {
    Thread syncT1 = new Thread(new SyncThread());
    syncT1.start();
    syncT1.interrupt();
//    syncT1.start();

    Thread syncT2 = new Thread(new SyncThread());
    syncT2.start();
  }
}
