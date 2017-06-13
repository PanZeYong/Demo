package com.pan.drawabledemo;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Author : Pan
 * Date : 12/10/16
 */

public class TransitionDrawableActivity extends AppCompatActivity {
    private Button mTransition;
    private Button mReserve;
    private ImageView mBackground;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transtion_drawable);
        init();
    }

    private void init() {
        mTransition = (Button) findViewById(R.id.btn_transition);
        mReserve = (Button) findViewById(R.id.btn_reserve);
        mBackground = (ImageView) findViewById(R.id.iv_background);

        final TransitionDrawable drawable = (TransitionDrawable) mBackground.getBackground();

        mTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable.startTransition(1000);
            }
        });

        mReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable.reverseTransition(1000);
            }
        });

    }
}
