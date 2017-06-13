package com.pan.drawabledemo;

import android.graphics.drawable.ScaleDrawable;
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

public class ScaleDrawableActivity extends AppCompatActivity {
    private ImageView mImageView;
    private Button mScale;

    private int mPercentage = 20;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_drawable);
        init();
    }

    private void init() {
        mImageView = (ImageView) findViewById(R.id.iv_image_view);
        mScale = (Button) findViewById(R.id.btn_scale);

        final ScaleDrawable drawable = (ScaleDrawable) mImageView.getBackground();
        drawable.setLevel(1000);
        mScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPercentage < 100) {

                }
            }
        });
    }
}
