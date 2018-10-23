package com.xingchen.core.filedownloader;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.xingchen.core.R;

/**
 * Created by zhangjn on 2018/10/23.
 *
 * https://github.com/lingochamp/FileDownloader
 */

public class FileDownloaderActivity extends AppCompatActivity {

    int downloadId;

    private String url = "http://imgsrc.baidu.com/forum/w%3D580/sign=2d51fcce8ad4b31cf03c94b3b7d7276f/48084b36acaf2eddc470ae648c1001e9380193bf.jpg";
    private String path = Environment.getExternalStorageDirectory() + "/Download/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_downloader);

        FileDownloader.setup(this);
    }

    public void download(View v){
        startSingleTask(url, path);
    }

    private int startSingleTask(String url, String path){
        downloadId = FileDownloader.getImpl().create(url)
                .setPath(path)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {

                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {

                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {

                    }
                }).start();

        return downloadId;
    }

    @Override
    protected void onDestroy() {
        FileDownloader.getImpl().pause(downloadId);
        super.onDestroy();
    }
}
