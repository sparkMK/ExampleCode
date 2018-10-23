package com.xingchen.core.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by zhangjn on 2018/7/26.
 */

public class UiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UEHandler.getInstance().initUEHandler(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
