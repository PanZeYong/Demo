package com.pan.materialdesigndemo;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;

/**
 * Author : panju
 * Date : 16-10-13
 */

public class TransitionActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_transition);
        setEnterTransition();
    }

    private void setEnterTransition() {
        switch (getIntent().getExtras().getInt("flag")) {
            case 0:
                getWindow().setEnterTransition(new Explode());
                getWindow().setExitTransition(new Slide());
                break;

            case 1:
                getWindow().setEnterTransition(new Slide());
                break;

            case 2:
                getWindow().setEnterTransition(new Fade());
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getWindow().setExitTransition(new Slide());
    }
}
