package com.xingchen.core.filedownloader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloader;
import com.xingchen.core.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjn on 2018/10/23.
 */

public class FileDownloaderMiltrixActivity extends AppCompatActivity {

    private FileDownloadListener downloadListener;

    boolean isSerial = true;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_downloader);

        downloadListener = new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                if (task.getListener() != downloadListener) {
                    return;
                }
                Toast.makeText(FileDownloaderMiltrixActivity.this, "pending", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                if (task.getListener() != downloadListener) {
                    return;
                }
            }

            @Override
            protected void completed(BaseDownloadTask task) {
                if (task.getListener() != downloadListener) {
                    return;
                }
                Toast.makeText(FileDownloaderMiltrixActivity.this, "completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                if (task.getListener() != downloadListener) {
                    return;
                }
            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {
                if (task.getListener() != downloadListener) {
                    return;
                }
                Toast.makeText(FileDownloaderMiltrixActivity.this, "error", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void warn(BaseDownloadTask task) {
                if (task.getListener() != downloadListener) {
                    return;
                }
                Toast.makeText(FileDownloaderMiltrixActivity.this, "warn", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void download(View v){
        startMitrixTask();
    }

    private void startMitrixTask(){

        final FileDownloadQueueSet queueSet = new FileDownloadQueueSet(downloadListener);

        final List<BaseDownloadTask> tasks = new ArrayList<>();

        int count = URLS.length;

        for (int i = 0; i < count; i++) {
            tasks.add(FileDownloader.getImpl().create(URLS[i]).setTag(i + 1));
        }
        queueSet.disableCallbackProgressTimes(); // do not want each task's download progress's callback,
        // we just consider which task will completed.

        // auto retry 1 time if download fail
        queueSet.setAutoRetryTimes(1);
        if (isSerial) {
            queueSet.downloadSequentially(tasks);
        } else {
            queueSet.downloadTogether(tasks);
        }
        queueSet.start();
    }

    @Override
    protected void onDestroy() {
        FileDownloader.getImpl().pause(downloadListener);
        super.onDestroy();
    }

    String[] URLS = {
            // 随机小资源一般不超过10

            "http://imgsrc.baidu.com/forum/w%3D580/sign=2d51fcce8ad4b31cf03c94b3b7d7276f/48084b36acaf2eddc470ae648c1001e9380193bf.jpg",

            "http://cdn.llsapp.com/user_images/26ebf7deb8eb1f66056cbdac31aa18209d2f7daf_1436262740.jpg",
            "http://cdn.llsapp.com/yy/image/a1de0e33-c3f3-4795-b2b9-4dafbcf06bee.jpg",
            "http://cdn.llsapp.com/yy/image/cc4bc37d-ef77-4469-a8e9-2c70105a3f94.jpg",
            // 重复
            "http://cdn.llsapp.com/yy/image/cc4bc37d-ef77-4469-a8e9-2c70105a3f94.jpg",
            "http://cdn.llsapp.com/yy/image/dd72c879-b1c4-4fb9-b871-d57dfa3aa709.jpg",

            "http://imgsrc.baidu.com/forum/w%3D580/sign=431115875243fbf2c52ca62b807fca1e/f310728b4710b9124db1c671c2fdfc03934522c9.jpg",
            "http://imgsrc.baidu.com/forum/w%3D580/sign=9e4d62b6c83d70cf4cfaaa05c8ddd1ba/347a02087bf40ad183aaf76c562c11dfa8eccef0.jpg",

            "http://imgsrc.baidu.com/forum/w%3D580/sign=84ab2ceab219ebc4c0787691b227cf79/d751352ac65c1038aed9dd4db3119313b17e899f.jpg",

            "http://imgsrc.baidu.com/forum/w%3D580/sign=ba6411eb0823dd542173a760e108b3df/7491f603738da9774865eba4b151f8198718e3fc.jpg",


            "http://imgsrc.baidu.com/forum/w%3D580/sign=4116378f79f0f736d8fe4c093a54b382/08d2d539b6003af3d0f5dd90342ac65c1138b6f0.jpg",

            "http://imgsrc.baidu.com/forum/w%3D580/sign=01fa14f81e30e924cfa49c397c0a6e66/1f3eb80e7bec54e7302313eeb8389b504ec26a77.jpg",
            "http://imgsrc.baidu.com/forum/w%3D580/sign=5781ecc0bba1cd1105b672288913c8b0/692d11dfa9ec8a13cc7a5f10f603918fa1ecc0db.jpg",
            "http://imgsrc.baidu.com/forum/w%3D580/sign=811b9291f2deb48ffb69a1d6c01d3aef/9565034f78f0f736d32244f10b55b319eac41366.jpg",
            // repeat case.

            "http://imgsrc.baidu.com/forum/w%3D580/sign=54d963edd52a283443a636036bb4c92e/a90b304e251f95cae548e146c8177f3e66095285.jpg",

            "http://imgsrc.baidu.com/forum/w%3D580/sign=764614d000e9390156028d364bed54f9/3725ab18972bd4073f657f0d7a899e510eb309a4.jpg",


            "http://imgsrc.baidu.com/forum/w%3D580/sign=5da230f68644ebf86d716437e9f8d736/823fb13533fa828b90dc9416fc1f4134960a5a4c.jpg",



    };

}
