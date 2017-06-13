package com.pan.drawabledemo;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Author : Pan
 * Date : 12/10/16
 */

public class ClipDrawableActivity extends AppCompatActivity {
    private ImageView mTop;
    private ImageView mBottom;
    private ImageView mCenterVertical;
    private ImageView mFillVertical;
    private ImageView mCenter;
    private ImageView mFill;
    private ImageView mClipVertical;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_drawable);
        init();
    }

    private void init() {
        mTop = (ImageView) findViewById(R.id.iv_top);
        ClipDrawable topDrawable = (ClipDrawable) mTop.getBackground();
        topDrawable.setLevel(7000);

        mBottom = (ImageView) findViewById(R.id.iv_bottom);
        ClipDrawable bottomDrawable = (ClipDrawable) mBottom.getBackground();
        bottomDrawable.setLevel(7000);

        mCenterVertical = (ImageView) findViewById(R.id.iv_center_vertical);
        ClipDrawable centerVerticalDrawable = (ClipDrawable) mCenterVertical.getBackground();
        centerVerticalDrawable.setLevel(7000);

        mFillVertical = (ImageView) findViewById(R.id.iv_fill_vertical);
        ClipDrawable fillVerticalDrawable = (ClipDrawable) mFillVertical.getBackground();
        fillVerticalDrawable.setLevel(7000);

        mCenter = (ImageView) findViewById(R.id.iv_center);
        ClipDrawable centerDrawable = (ClipDrawable) mCenter.getBackground();
        centerDrawable.setLevel(7000);

        mFill = (ImageView) findViewById(R.id.iv_fill);
        ClipDrawable fillDrawable = (ClipDrawable) mFill.getBackground();
        fillDrawable.setLevel(7000);

        mClipVertical = (ImageView) findViewById(R.id.iv_clip_vertical);
        ClipDrawable clipVerticalDrawable = (ClipDrawable) mClipVertical.getBackground();
        clipVerticalDrawable.setLevel(7000);
    }
}
