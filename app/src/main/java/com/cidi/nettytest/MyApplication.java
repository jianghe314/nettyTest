package com.cidi.nettytest;

import android.app.Application;
import android.content.Context;

/**
 * Created by CIDI zhengxuan on 2019/10/15
 * QQ:1309873105
 */
public class MyApplication extends Application {

    private static  Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        ThreadPoolUtils.InitThreadPool();
        ScheduledThreadPoolUtils.InitScheduledThreadPool(5);
        NettyUDPSocket.initNettyUdpSocket();
    }

    public static Context getMyApplicationContext(){
        return context;
    }
}
