package com.xingchen.core.widget;

/**
 * Created by xingchen on 2018/8/5.
 */

import android.view.MotionEvent;

final class WheelViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    final WheelView wheelView;

    WheelViewGestureListener(WheelView wheelview) {
        wheelView = wheelview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        wheelView.scrollBy(velocityY);
        return true;
    }
}
