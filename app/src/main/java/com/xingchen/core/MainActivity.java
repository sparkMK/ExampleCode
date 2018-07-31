package com.xingchen.core;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xingchen.core.utils.DateUtil;
import com.xingchen.core.widget.timediolog.TimeDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Calendar calendar = DateUtil.gerCalendarByView(selectDayBtn);

        Calendar calendar = DateUtil.getCalendarByString("2017-08-20", "yyyy-MM-dd");

        TimeDialog customBuilder = new TimeDialog(this, new TimeDialog.OnDateSetListener() {
            public void onDateSet(int year, int monthOfYear, int dayOfMonth) {
                currentTime = DateUtil.getSystemDateFormat(year, monthOfYear, dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), R.style.smart_lock_common_timedialog)
                .setYmd(true);
        customBuilder.createDialog().show();
    }
}
