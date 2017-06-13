package com.pan.drawabledemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Author : Pan
 * Date : 12/10/16
 */

public class BitmapDrawableActivity extends AppCompatActivity {
    private ImageView mImageView;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_drawable);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        mImageView = (ImageView) findViewById(R.id.iv_image_view);
        mImageView.setImageDrawable(getDrawable(R.drawable.drawable_bitmap));
    }
}
