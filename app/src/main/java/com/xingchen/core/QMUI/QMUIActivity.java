package com.xingchen.core.QMUI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogBuilder;
import com.xingchen.core.R;

/**
 * Created by zhangjn on 2018/10/30.
 *
 * https://github.com/QMUI/QMUI_Android
 *
 * 官网：https://qmuiteam.com/android
 *
 */

public class QMUIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_qmui);

        QMUIDialog.MultiCheckableDialogBuilder.setOnProvideDefaultTheme(new QMUIDialogBuilder.OnProvideDefaultTheme() {
            @Override
            public int getThemeForBuilder(QMUIDialogBuilder builder) {
                return 0;
            }
        });
    }
}
