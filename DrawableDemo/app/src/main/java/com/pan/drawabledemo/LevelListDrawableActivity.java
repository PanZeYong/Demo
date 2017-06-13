package com.pan.drawabledemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Author : Pan
 * Date : 12/10/16
 */

public class LevelListDrawableActivity extends AppCompatActivity {
    private ImageView mImageView;
    private Button mChangeLevel;

    private int mLevel = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_list);
        init();
    }

    private void init() {
        mChangeLevel = (Button) findViewById(R.id.btn_change_level);
        mImageView = (ImageView) findViewById(R.id.iv_image_view);

        mChangeLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLevel < 4) {
                    mImageView.setImageLevel(++mLevel);
                } else {
                    mLevel = 1;
                    mImageView.setImageLevel(mLevel);
                }
            }
        });
    }
}
