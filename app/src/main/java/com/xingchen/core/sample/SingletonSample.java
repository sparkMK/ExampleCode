package com.xingchen.core.sample;

import android.util.Log;

import com.xingchen.core.utils.Singleton;
import com.xingchen.core.utils.SingletonUtil;

/**
 * Created by zhangjn on 2018/12/18.
 */

public class SingletonSample {

    public void sysHello(){
        Log.d("xingchen", "SingletonSample");
    }

    Singleton<SingletonSample> singletonSample = new Singleton<SingletonSample>() {
        @Override
        protected SingletonSample create() {
            return new SingletonSample();
        }
    };

    private void test(){
        singletonSample.getSingleton().sysHello();

        SingletonUtil.getSingleton(SingletonSample.class).sysHello();
    }


}
