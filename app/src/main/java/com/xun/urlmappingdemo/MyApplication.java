package com.xun.urlmappingdemo;

import android.app.Application;

/**
 * Created by xunwang on 2017/6/20.
 */

public class MyApplication extends Application{
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        c.s();
    }

    public static MyApplication getInstance(){
        return instance;
    }
}
