package com.xingchen.core.app;

import android.app.Application;

/**
<<<<<<< HEAD
 * Created by zhangjn on 2018/7/26.
 */

public class UiApplication extends Application {
=======
 * Created by xingchen on 2018/7/31.
 */

public class UiApplication extends Application{
>>>>>>> origin/master

    @Override
    public void onCreate() {
        super.onCreate();
<<<<<<< HEAD
=======
        UEHandler.getInstance().initUEHandler(this);
>>>>>>> origin/master
    }
}
