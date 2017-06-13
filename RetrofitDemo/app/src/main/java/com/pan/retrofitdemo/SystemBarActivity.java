package com.pan.retrofitdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author : Pan
 * Date : 1/9/17
 */

public class SystemBarActivity extends AppCompatActivity {

    private static final String TAG = DistrictActivity.class.getSimpleName();

    private Button mGetProvince;

    private TextView mShow;

    private ImageView mImageView;

    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;

    private DistrictAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.district_activity);
        hideStatusAndNavigationBarsWithNoStickyImmersion();
//        hideStatusBarByFullScreen();
//        hideStatusBarByLayoutFullScreen();
//        hideStatusBarByLayoutAndFullScreen();
//        hideNavigationBar();
//        hideNavigationBarByLayout();
//        hideStatusBarAndNavigationBarByFullScreen();
//        hideStatusBarAndNavigationBarByFullScreenAndLayout();
//        hideStatusBarAndNavigationBarByLayout();
//        hideStatusBarAndNavigationBarByLayoutLayout();
//        hideStatusBarAndNavigationBar();
//        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
//            @Override
//            public void onSystemUiVisibilityChange(int visibility) {
//                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
//                    Log.d(TAG, "Visibility");
//                    getSupportActionBar().hide();
//                    decorView.setSystemUiVisibility(options);
//                } else {
//                    Log.d(TAG, "InVisibility");
//                }
//            }
//        });
        init();
    }

    private void init() {
//        mImageView = (ImageView) findViewById(R.id.image);
//        mShow = (TextView) findViewById(R.id.tv_show);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DistrictActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        mGetProvince = (Button) findViewById(R.id.btn_get_province);
//        mShow = (TextView) findViewById(R.id.tv_show);
//        mGetProvince.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getProvinceList();
//            }
//        });
    }

    /**
     * 隐藏状态栏：SYSTEM_UI_FLAG_FULLSCREEN
     */
    private void hideStatusBarByFullScreen() {
        View view = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_FULLSCREEN;
        view.setSystemUiVisibility(options);
        getSupportActionBar().hide();
    }

    /**
     * 隐藏状态栏：SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
     */
    private void hideStatusBarByLayoutFullScreen() {
        View view = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        view.setSystemUiVisibility(options);
        getSupportActionBar().hide();
    }

    /**
     * 隐藏状态栏：SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
     */
    private void hideStatusBarByLayoutAndFullScreen() {
        View view = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        view.setSystemUiVisibility(options);
        getSupportActionBar().hide();
    }

    /**
     * 隐藏导航栏：SYSTEM_UI_FLAG_HIDE_NAVIGATION
     */
    private void hideNavigationBar() {
        View view = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        view.setSystemUiVisibility(options);
        getSupportActionBar().hide();
    }

    /**
     * 隐藏导航栏：SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
     */
    private void hideNavigationBarByLayout() {
        View view = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        view.setSystemUiVisibility(options);
        getSupportActionBar().hide();
    }

    /**
     * 隐藏状态栏、导航栏：SYSTEM_UI_FLAG_FULLSCREEN、SYSTEM_UI_FLAG_HIDE_NAVIGATION
     */
    private void hideStatusBarAndNavigationBarByFullScreen() {
        View view = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        view.setSystemUiVisibility(options);
        getSupportActionBar().hide();
    }

    /**
     * 隐藏状态栏、导航栏：SYSTEM_UI_FLAG_FULLSCREEN、SYSTEM_UI_FLAG_HIDE_NAVIGATION
     */
    private void hideStatusBarAndNavigationBarByFullScreenAndLayout() {
        View view = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        view.setSystemUiVisibility(options);
        getSupportActionBar().hide();
    }

    /**
     * 隐藏状态栏、导航栏：SYSTEM_UI_FLAG_FULLSCREEN、SYSTEM_UI_FLAG_HIDE_NAVIGATION
     */
    private void hideStatusBarAndNavigationBarByLayout() {
        View view = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        view.setSystemUiVisibility(options);
        getSupportActionBar().hide();
    }

    /**
     * 隐藏状态栏、导航栏：SYSTEM_UI_FLAG_FULLSCREEN、SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
     */
    private void hideStatusBarAndNavigationBarByLayoutLayout() {
        View view = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        view.setSystemUiVisibility(options);
        getSupportActionBar().hide();
    }

    /**
     * 隐藏状态栏、导航栏：SYSTEM_UI_FLAG_FULLSCREEN、SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
     */
    private void hideStatusBarAndNavigationBar() {
        View view = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        view.setSystemUiVisibility(options);
        getSupportActionBar().hide();
    }

    private void hideStatusAndNavigationBarsWithNoStickyImmersion() {
        View decorView = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(options);
        getSupportActionBar().hide();
    }



    private void getProvinceList() {
        new GithubServiceImpl().getProvinceList().enqueue(new Callback<DistrictBean>() {
            @Override
            public void onResponse(Call<DistrictBean> call, Response<DistrictBean> response) {
                DistrictBean districtBean = response.body();
                List<DistrictBean.ResultBean> resultBeanList = districtBean.getResult().get(0);
                for (DistrictBean.ResultBean bean : resultBeanList) {
                    Log.d(TAG, bean.toString());
                }
            }

            @Override
            public void onFailure(Call<DistrictBean> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
