package com.yiyi.thread.startmethod;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

  static class Cabllable1 implements Callable{

    @Override
    public Object call() throws Exception {
      return 1;
    }
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    FutureTask<Integer> f = new FutureTask<>(new Cabllable1());
    Thread t = new Thread(f) ;
    t.start();

    Integer result = f.get();

    System.out.print(result);

  }
}
