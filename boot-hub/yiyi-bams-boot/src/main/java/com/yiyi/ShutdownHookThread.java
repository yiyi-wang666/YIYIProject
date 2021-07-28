package com.yiyi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShutdownHookThread extends Thread{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private  Thread mainThread;

    public ShutdownHookThread(Thread mainThread){
        this.mainThread = mainThread;
        Runtime.getRuntime().addShutdownHook(this);
    }

    public ShutdownHookThread(Runnable target) {
        System.out.println("shut down p======");
    }
}
