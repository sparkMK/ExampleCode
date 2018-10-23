package com.xingchen.core.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * Created by zhangjn on 2018/10/23.
 */

public class DemoUtil {

    public static File getParentFile(@NonNull Context context) {

        final File externalSaveDir = context.getExternalCacheDir();
        if (externalSaveDir == null) {
            return context.getCacheDir();
        } else {
            return externalSaveDir;
        }
    }
}
