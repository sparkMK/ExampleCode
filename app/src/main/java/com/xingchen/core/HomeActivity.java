package com.xingchen.core;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xingchen.core.app.BaseActivity;
import com.xingchen.core.example.MainActivity;

/**
 * Created by zhangjn on 2019/6/6.
 */

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getResources().getString(R.string.app_name));
        findViewById(R.id.home_uapp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }
}
