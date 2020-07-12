package com.maple.cloud.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

public class MyApplication extends Application {
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initSDK();
    }

    public static MyApplication getInstance(){
        return instance;
    }

    private void initSDK(){
        Utils.init(this);
    }
}
