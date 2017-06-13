package com.pan.vieweventdispatchdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private CustomButton mButtonOne;
    private CustomButton mButtonTwo;
    private CustomLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        init();
        registerListener();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
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

        return super.dispatchTouchEvent(ev);
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
                Log.d(TAG, "onTouchEvent UP");
                break;

            default:
                break;
        }

        return super.onTouchEvent(event);
    }

    private void init() {
        mButtonOne = (CustomButton) findViewById(R.id.btn_one);
        mButtonTwo = (CustomButton) findViewById(R.id.btn_two);
        mLayout = (CustomLayout) findViewById(R.id.parent);
    }

    private void registerListener() {
        mButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button One onClick");
            }
        });

        mButtonOne.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "Button One onTouch");
                return false;
            }
        });

        mButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button Two onClick");
            }
        });

        mButtonTwo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "Button Two onTouch");
                return false;
            }
        });

        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "CustomLayout onClick");
            }
        });

        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "CustomLayout onTouch");
                return false;
            }
        });
    }
}
