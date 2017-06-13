package com.pan.retrofitdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author : Pan
 * Date : 1/9/17
 */

public class ProvinceFragment extends DistrictBaseFragment implements DistrictAdapter.OnItemClickListener{
    private static final String TAG = ProvinceFragment.class.getSimpleName();

    private List<DistrictBean.ResultBean> mResultBeanList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerListener();
        getProvinceList();
    }

    @Override
    public void onItemClick(int position) {
        mCallback.show(Constant.PROVINCE, mResultBeanList.get(position).getFullname());
        mActivity.replace(CityFragment.newInstance(mResultBeanList.get(position).getId()));
    }

    private void registerListener() {
        mAdapter.setOnItemClickListener(this);
    }

    private void getProvinceList() {
        mProgressBar.setVisibility(View.VISIBLE);
        new GithubServiceImpl().getProvinceList().enqueue(new Callback<DistrictBean>() {
            @Override
            public void onResponse(Call<DistrictBean> call, Response<DistrictBean> response) {
                if (response.code() == 200) {
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.GONE);
                    mResultBeanList = response.body().getResult().get(0);
                    mAdapter.refresh(mResultBeanList);
                }
            }

            @Override
            public void onFailure(Call<DistrictBean> call, Throwable t) {
            }
        });
    }

    public static ProvinceFragment newInstance() {
        ProvinceFragment fragment = new ProvinceFragment();
        return fragment;
    }
}
