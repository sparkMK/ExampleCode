package com.xingchen.core.sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xingchen.core.R;

/**
 * https://github.com/zhou-you/EasySignSeekBar
 *
 * 本库主要提供一个漂亮而强大的自定义SeekBar，进度变化由提示牌 (sign)展示,具有强大的属性设置，支持设置section(节点)、mark(标记)、track(轨迹)、thumb（拖动块）、progress(进度)、sign（提示框）等功能
 */
public class EasySignSeekBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_sign_seek_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
