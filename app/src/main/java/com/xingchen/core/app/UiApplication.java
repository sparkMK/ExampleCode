package com.xingchen.core.app;

import android.app.Application;

/**
 * Created by xingchen on 2018/7/31.
 */

public class UiApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        UEHandler.getInstance().initUEHandler(this);
    }
}
