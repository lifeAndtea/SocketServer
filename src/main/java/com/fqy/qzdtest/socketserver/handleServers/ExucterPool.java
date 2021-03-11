package com.fqy.qzdtest.socketserver.handleServers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExucterPool {

    private ExecutorService executorService;

    public ExucterPool(int coreSize,int queueSize){
        /*
        * int corePoolSize,
          int maximumPoolSize,
          long keepAliveTime,
          TimeUnit unit,
          BlockingQueue<Runnable> workQueue
         */
        this.executorService = new ThreadPoolExecutor(coreSize,10,40, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void addTask(Runnable target){
        executorService.execute(target);
    }

}
