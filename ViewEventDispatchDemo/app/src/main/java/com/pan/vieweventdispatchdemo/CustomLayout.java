package com.pan.vieweventdispatchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Author : Pan
 * Date : 11/19/16
 */

public class CustomLayout extends LinearLayout {
    private static final String TAG = CustomLayout.class.getSimpleName();

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent Down");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent Move");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent UP");
                break;

            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent Down");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent Move");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent Up");
                break;

            default:
                break;
        }

        return super.onTouchEvent(event);
    }
}
