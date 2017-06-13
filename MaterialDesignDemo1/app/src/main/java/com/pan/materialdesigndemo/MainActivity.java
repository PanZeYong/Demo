package com.pan.materialdesigndemo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{
    private TextView mRoundRect;
    private TextView mOval;
    private TextView mRect;

    private Button mEnterExplde;
    private Button mEnterSlide;
    private Button mEnterFade;
    private Button mShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
//        extractVibrantColor();
        init();
        clipping();
    }

    private void init() {
        mRoundRect = (TextView) findViewById(R.id.tv_round_rect);
        mOval = (TextView) findViewById(R.id.tv_oval);
        mRect = (TextView) findViewById(R.id.tv_rect);
//        mEnterExplde = (Button)findViewById(R.id.btn_enter_explode);
//        mEnterSlide = (Button)findViewById(R.id.btn_enter_slide);
//        mEnterFade = (Button)findViewById(R.id.btn_enter_fade);
//        mShare = (Button) findViewById(R.id.btn_share);

//        mEnterExplde.setOnClickListener(this);
//        mEnterSlide.setOnClickListener(this);
//        mEnterFade.setOnClickListener(this);
//        mShare.setOnClickListener(this);
    }

    private void clipping () {
        ViewOutlineProvider roundRect = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20);
            }
        };

        ViewOutlineProvider oval = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        };

        ViewOutlineProvider rect = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRect(0, 0, view.getWidth() * 2, view.getWidth());
            }
        };

        mRoundRect.setOutlineProvider(roundRect);
        mOval.setOutlineProvider(oval);
        mRect.setOutlineProvider(rect);
    }

    /**
     * Extract vibrant
     */
    private void extractVibrantColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                getWindow().setStatusBarColor(vibrant.getRgb());
                getWindow().setNavigationBarColor(vibrant.getRgb());
                getWindow().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
            }
        });
    }

    /**
     * Extract dark vibrant
     */
    private void extractDarkVibrantColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
                getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                getWindow().setStatusBarColor(vibrant.getRgb());
                getWindow().setNavigationBarColor(vibrant.getRgb());
                getWindow().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
            }
        });
    }

    /**
     * Extract dark vibrant
     */
    private void extractLightVibrantColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getLightVibrantSwatch();
                getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                getWindow().setStatusBarColor(vibrant.getRgb());
                getWindow().setNavigationBarColor(vibrant.getRgb());
                getWindow().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
            }
        });
    }

    /**
     * Extract muted
     */
    private void extractMutedColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getMutedSwatch();
                getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                getWindow().setStatusBarColor(vibrant.getRgb());
                getWindow().setNavigationBarColor(vibrant.getRgb());
                getWindow().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
            }
        });
    }

    /**
     * Extract dark muted
     */
    private void extractDarkMutedColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getDarkMutedSwatch();
                getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                getWindow().setStatusBarColor(vibrant.getRgb());
                getWindow().setNavigationBarColor(vibrant.getRgb());
                getWindow().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
            }
        });
    }

    /**
     * Extract light muted
     */
    private void extractLightMutedColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getLightMutedSwatch();
                if (null != vibrant) {
                    getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                    getWindow().setStatusBarColor(vibrant.getRgb());
                    getWindow().setNavigationBarColor(vibrant.getRgb());
                    getWindow().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                }

            }
        });
    }

    private void enterExplode() {
        Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
        intent.putExtra("flag", 0);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    private void enterSlide() {
        Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
        intent.putExtra("flag", 1);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    private void enterFade() {
        Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
        intent.putExtra("flag", 2);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    private void share() {
        Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
        intent.putExtra("flag", 3);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, mShare, "share").toBundle());
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_enter_explode:
//                enterExplode();
//                break;
//
//            case R.id.btn_enter_slide:
//                enterSlide();
//                break;
//
//            case R.id.btn_enter_fade:
//                enterFade();
//                break;
//
//            case R.id.btn_share:
//                share();
//                break;
//
//            default:
//                break;
//        }
//    }
}
