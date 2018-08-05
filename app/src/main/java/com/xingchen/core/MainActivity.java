package com.xingchen.core;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xingchen.core.utils.DateUtil;
import com.xingchen.core.widget.BottomDialog;
import com.xingchen.core.widget.WheelView;
import com.xingchen.core.widget.timediolog.TimeDialog;

import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    String currentTime;
    String[] PLANETS = {"a", "b", "c"};
    BottomDialog bottomDialog;
    ImageView imageView;

    TranslateAnimation mHiddenAction;
    TranslateAnimation mShowAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.showView);
        /**
         * https://github.com/wangpeiming110/WheelView
         */
        WheelView wva = (WheelView) findViewById(R.id.wheel_view);
        wva.setItems(Arrays.asList(PLANETS),1);//init selected position is 1 初始选中位置为1
        wva.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                Log.i(TAG, "selectedIndex: " + selectedIndex + ", item: " + item);
           //0 ~ len-1
            }
        });

        findViewById(R.id.showBottomDialog).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               // showBottomDialog();
                if(imageView.getVisibility() == View.GONE){
                    showView();
                }else{
                    hiddenView();
                }
            }
        });
       // commonShow();

        // 显示动画
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, -0.0f);
        mShowAction.setRepeatMode(Animation.REVERSE);
        mShowAction.setDuration(500);

        // 隐藏动画
        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
    }

    private void showBottomDialog(){
        View outerView1 = LayoutInflater.from(this).inflate(R.layout.dialog_select_date_time, null);
//日期滚轮
        final WheelView wv1 = (WheelView) outerView1.findViewById(R.id.wv1);
        wv1.setItems(Arrays.asList(PLANETS),0);

        //联动逻辑效果
        wv1.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index,String item) {
            }
        });
        TextView tv_ok = (TextView) outerView1.findViewById(R.id.tv_ok);
        TextView tv_cancel = (TextView) outerView1.findViewById(R.id.tv_cancel);
        //点击确定
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                bottomDialog.dismiss();
                String mSelectDate = wv1.getSelectedItem();
                Toast.makeText(MainActivity.this, "selectDateTime: "+mSelectDate, Toast.LENGTH_SHORT).show();
            }
        });
        //点击取消
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                bottomDialog.dismiss();
            }
        });
        //防止弹出两个窗口
        if (bottomDialog !=null && bottomDialog.isShowing()) {
            return;
        }

        bottomDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        //将布局设置给Dialog
        bottomDialog.setContentView(outerView1);
        bottomDialog.show();//显示对话框
    }

    private void showTimeDialog(){
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

    private void commonShow(){
        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        imageView.setBackgroundResource(R.drawable.selector_pickerview_btn);
        imageView.startAnimation(animation);
    }

    private void showView(){
        imageView.startAnimation(mShowAction);
        imageView.setVisibility(View.VISIBLE);
    }

    private void hiddenView(){
        imageView.startAnimation(mHiddenAction);
        imageView.setVisibility(View.GONE);
    }
}
