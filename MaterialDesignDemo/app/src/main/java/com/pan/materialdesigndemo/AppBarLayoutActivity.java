package com.pan.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Pan
 * Date : 2/20/17
 */

public class AppBarLayoutActivity extends AppCompatActivity {
    private static final String TAG = AppBarLayoutActivity.class.getCanonicalName();

    private AppBarLayout mAppBar;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFab;

    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
//        Util.setColor(this, R.color.colorAccent);

        init();
        registerListener();
    }

    private void init() {
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mToolbar.setTitle("AppBarLayout");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);

        mAdapter.refresh(initData());
    }

    private void registerListener() {}

    private List<ViewPagerFragment> initData() {
        List<ViewPagerFragment> data = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            data.add(ViewPagerFragment.newInstance());
        }

        return data;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AppBarLayoutActivity.class);
        context.startActivity(intent);
    }
}
