package com.pan.vieweventdispatchdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Author : Pan
 * Date : 11/24/16
 */

public class CustomViewActivity extends AppCompatActivity {
    private Button mChangeCircleColor;
    private Button mCount;
    private Button mChangeLineColor;

    private CircleView mCircleView;

    private int mCountText;

    private TimerTask mTask;
    private Timer mTimer;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (mCountText <= 10) {
                        mCircleView.setCount(mCountText + "");
                        mCountText++;
                        mCircleView.invalidate();
                    } else {
                        mCircleView.setCount("Hello");
                        mCircleView.invalidate();
                        mTimer.cancel();
                        mTask.cancel();
                        mTask = null;
                        mTimer = null;
                    }
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        init();
        registerListener();
    }

    private void init() {
        mChangeCircleColor = (Button) findViewById(R.id.btn_change_circle_color);
        mCount = (Button) findViewById(R.id.btn_count);
        mChangeLineColor = (Button) findViewById(R.id.btn_change_line_color);
        mCircleView = (CircleView) findViewById(R.id.circle_view);
    }

    private void registerListener() {
        mChangeCircleColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleView.setCircleColor(Color.CYAN);
                mCircleView.invalidate();
            }
        });

        mCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCountText > 10) {
                    mCountText = 0;
                }

                if (null == mTask) {
                    mTask = new MyTimerTask();
                }

                if (null == mTimer) {
                    mTimer = new Timer();
                }

                mTimer.schedule(mTask, 1000, 1000);
            }
        });

        mChangeLineColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleView.setLineColor(Color.DKGRAY);
                mCircleView.invalidate();
            }
        });
    }

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            Message message = mHandler.obtainMessage();
            message.what = 0;
            mHandler.sendMessage(message);
        }
    }
}
