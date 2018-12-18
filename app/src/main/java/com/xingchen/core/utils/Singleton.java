package com.xingchen.core.utils;

/**
 * Created by zhangjn on 2018/12/18.
 *
 * Android 源码中通用单例模板
 */

public abstract class Singleton<T> {

    private T mInstance;

    public Singleton(){

    }

    protected abstract T create();

    public final T getSingleton(){
        synchronized (this){
            if(this.mInstance == null){
                this.mInstance = this.create();
            }
            return mInstance;
        }
    }
}
