package com.cidi.nettytest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by CIDI zhengxuan on 2019/2/28
 * QQ:1309873105
 */
public class ThreadPoolUtils {

    //核心线程数
    private static int coreThreadSize=5;
    //最大线程数
    private static int maxThreadSize=80;
    private static ExecutorService executorService;


    public static void InitThreadPool(){
        if(executorService == null){
            synchronized (ExecutorService.class){
                if(executorService == null){
                    executorService=new ThreadPoolExecutor(coreThreadSize,maxThreadSize, Integer.MAX_VALUE, TimeUnit.MILLISECONDS,new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory());
                }
            }
        }

    }

    public static void InitThreadPool(int coreThreadSize, int maxThreadSize ){
        if(executorService == null){
            synchronized (ExecutorService.class){
                if(executorService == null){
                    executorService=new ThreadPoolExecutor(coreThreadSize,maxThreadSize,1000*6, TimeUnit.MILLISECONDS,new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory());
                }
            }
        }

    }

    //提交线程任务
    public static void execute(Runnable taskRunable){
        if(!executorService.isShutdown()){
            executorService.execute(taskRunable);
        }
    }

    //清空所有线程
    public static void clearAllThread(){
        executorService.shutdownNow();
    }






}
