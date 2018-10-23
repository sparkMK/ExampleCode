package com.xingchen.core.filedownloader;

import android.os.Bundle;
import android.os.Environment;
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
 *
 * https://github.com/lingochamp/FileDownloader
 */

public class FileDownloaderActivity extends AppCompatActivity {

    int downloadId;

    private FileDownloadListener downloadListener;

    private String url = "http://imgsrc.baidu.com/forum/w%3D580/sign=2d51fcce8ad4b31cf03c94b3b7d7276f/48084b36acaf2eddc470ae648c1001e9380193bf.jpg";
    private String path = Environment.getExternalStorageDirectory() + "/Download/93333.jpg";

    String[] URLS = {
            // 随机小资源一般不超过10
            "http://imgsrc.baidu.com/forum/w%3D580/sign=2d51fcce8ad4b31cf03c94b3b7d7276f/48084b36acaf2eddc470ae648c1001e9380193bf.jpg",
            "http://cdn.llsapp.com/user_images/26ebf7deb8eb1f66056cbdac31aa18209d2f7daf_1436262740.jpg",
            "http://cdn.llsapp.com/yy/image/a1de0e33-c3f3-4795-b2b9-4dafbcf06bee.jpg",
            "http://cdn.llsapp.com/yy/image/cc4bc37d-ef77-4469-a8e9-2c70105a3f94.jpg"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_downloader);

        FileDownloader.setup(this);

        downloadListener = new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                if (task.getListener() != downloadListener) {
                    return;
                }
                Toast.makeText(FileDownloaderActivity.this, "pending", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(FileDownloaderActivity.this, "completed", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(FileDownloaderActivity.this, "error", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void warn(BaseDownloadTask task) {
                if (task.getListener() != downloadListener) {
                    return;
                }
                Toast.makeText(FileDownloaderActivity.this, "warn", Toast.LENGTH_SHORT).show();
            }
        };
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
                        Toast.makeText(FileDownloaderActivity.this, "pending", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        Toast.makeText(FileDownloaderActivity.this, "progress", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        Toast.makeText(FileDownloaderActivity.this, "completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        Toast.makeText(FileDownloaderActivity.this, "paused", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        Toast.makeText(FileDownloaderActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        Toast.makeText(FileDownloaderActivity.this, "warn", Toast.LENGTH_SHORT).show();
                    }
                }).start();

        return downloadId;
    }


    public void miltrixDownload(View view){
        //final FileDownloadListener parallelTarget = createListener();
        final List<BaseDownloadTask> taskList = new ArrayList<>();
        int i = 0;
        for (String url : URLS) {
            taskList.add(FileDownloader.getImpl().create(url)
                    .setTag(++i).setPath(Environment.getExternalStorageDirectory() + "/Download/" + "pp" + i + ".jpg", false));
        }
        new FileDownloadQueueSet(downloadListener)
                .setCallbackProgressTimes(1)
                .downloadTogether(taskList)
                .start();
    }

    public void singleDownload(View view){
        FileDownloader.getImpl().create(URLS[2])
                .setPath(path)
                .setListener(downloadListener)
                .setTag(1)
                .start();
    }


    private FileDownloadListener createListener() {
        return new FileDownloadListener() {

            @Override
            protected boolean isInvalid() {
                return isFinishing();
            }

            @Override
            protected void pending(final BaseDownloadTask task, final int soFarBytes, final int totalBytes) {

            }

            @Override
            protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                super.connected(task, etag, isContinue, soFarBytes, totalBytes);
            }

            @Override
            protected void progress(final BaseDownloadTask task, final int soFarBytes, final int totalBytes) {

            }

            @Override
            protected void blockComplete(final BaseDownloadTask task) {

            }

            @Override
            protected void retry(BaseDownloadTask task, Throwable ex, int retryingTimes, int soFarBytes) {
                super.retry(task, ex, retryingTimes, soFarBytes);

            }

            @Override
            protected void completed(BaseDownloadTask task) {

            }

            @Override
            protected void paused(final BaseDownloadTask task, final int soFarBytes, final int totalBytes) {

            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {

            }

            @Override
            protected void warn(BaseDownloadTask task) {

            }
        };
    }

    @Override
    protected void onDestroy() {
        FileDownloader.getImpl().pause(downloadId);

        FileDownloader.getImpl().pauseAll();
        super.onDestroy();
    }
}
