package com.xingchen.core.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;

/**
 * Created by zhangjn on 2018/7/26.
 */

public class UiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UEHandler.getInstance().initUEHandler(this);

        FileDownloader.setupOnApplicationOnCreate(this)
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000) // set connection timeout.
                        .readTimeout(15_000) // set read timeout.
                ))
                .commit();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
