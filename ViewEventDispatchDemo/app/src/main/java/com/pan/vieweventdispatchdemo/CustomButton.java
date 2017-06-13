package com.pan.vieweventdispatchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Author : Pan
 * Date : 11/18/16
 */

public class CustomButton extends Button {
    private static final String TAG = CustomButton.class.getSimpleName();

    public CustomButton(Context context, AttributeSet attrs) {
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

        return false;
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
