package com.pan.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Author : Pan
 * Date : 2/20/17
 */

public class FloatActionButtonActivity extends AppCompatActivity {
    private static final String TAG = FloatActionButtonActivity.class.getCanonicalName();

    private FloatingActionButton mFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);

        init();
        registerListener();
    }

    private void init() {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mFab.setBackgroundTintList
                    (getResources().getColorStateList(R.color.floating_action_button, null));
        } else {
            mFab.setBackgroundTintList
                    (getResources().getColorStateList(R.color.floating_action_button));
        }
    }

    private void registerListener() {
        mFab.show(new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onShown(FloatingActionButton fab) {
                super.onShown(fab);
                Log.d(TAG, "show onShown");
            }

            @Override
            public void onHidden(FloatingActionButton fab) {
                super.onHidden(fab);
                Log.d(TAG, "show onHidden");
            }
        });

        mFab.hide(new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onShown(FloatingActionButton fab) {
                super.onShown(fab);
                Log.d(TAG, "hide onShown");
            }

            @Override
            public void onHidden(FloatingActionButton fab) {
                super.onHidden(fab);
                Log.d(TAG, "hide onHidden");
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "FloatingActionButton", Snackbar.LENGTH_SHORT)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mFab.hide();
                            }
                        })
                        .show();
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FloatActionButtonActivity.class);
        context.startActivity(intent);
    }
}
