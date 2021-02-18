package com.zhengtu.verticalviewpagelibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MyViewPage;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * 可以上下滑动的ViewPage
 */
public class VerticalViewPager extends MyViewPage {

    public VerticalViewPager(@NonNull Context context) {
        super(context);
    }

    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapTouchEvent(MotionEvent.obtain(ev)));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(swapTouchEvent(MotionEvent.obtain(ev)));
    }

    private MotionEvent swapTouchEvent(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();
       // event.setLocation((event.getY() / height) * width, ((event.getX() / width) * height));
        float newX = (event.getY() / height) * width;
        float newY = (event.getX() / width) * height;

        if (event.getY() > event.getX()) {//这个为了可以斜着滑动到下一个
            newY = 0f;
        }
        event.setLocation(newX, newY);
        return event;
    }
}
