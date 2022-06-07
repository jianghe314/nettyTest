package com.cidi.nettytest;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by CIDI zhengxuan on 2019/5/9
 * QQ:1309873105
 * 定时任务线程池
 */
public class ScheduledThreadPoolUtils {

    private static ScheduledExecutorService executorService;

    public static void InitScheduledThreadPool(int coreThreadSize){
        //executorService= new ScheduledThreadPoolExecutor(coreThreadSize);
        executorService=new ScheduledThreadPoolExecutor(coreThreadSize, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    //提交延时任务
    public static void execute(Runnable task,int delayTime){
        if(!executorService.isShutdown()){
            executorService.schedule(task,delayTime,TimeUnit.MILLISECONDS);
        }
    }

    //提交定时任务
    public static void executeAtFixedRate(Runnable task,int delayTime,int period){
        if(!executorService.isShutdown()){
            executorService.scheduleAtFixedRate(task,0,period,TimeUnit.MILLISECONDS);
        }
    }



    //清空所有线程
    public static void clearAllThread(){
        executorService.shutdownNow();
    }


}
