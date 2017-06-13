package com.pan.materialdesigndemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Author : Pan
 * Date : 2/21/17
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<ViewPagerFragment> mData;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void refresh(List<ViewPagerFragment> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab " + (position + 1);
    }
}
