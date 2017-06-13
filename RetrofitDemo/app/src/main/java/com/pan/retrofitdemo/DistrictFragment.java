package com.pan.retrofitdemo;

import android.app.Activity;
import android.content.Context;
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

public class DistrictFragment extends DistrictBaseFragment
        implements DistrictAdapter.OnItemClickListener{
    private static final String TAG = DistrictFragment.class.getSimpleName();

    private List<DistrictBean.ResultBean> mResultBeanList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerListener();
        getChildren(getArguments().getString("id"));
    }

    @Override
    public void onItemClick(int position) {
        mCallback.show(Constant.DISTRICT, mResultBeanList.get(position).getFullname());
    }

    private void registerListener() {
        mAdapter.setOnItemClickListener(this);
    }

    private void getChildren(String id) {
        mProgressBar.setVisibility(View.VISIBLE);
        new GithubServiceImpl().getChildren(id).enqueue(new Callback<DistrictBean>() {
            @Override
            public void onResponse(Call<DistrictBean> call, Response<DistrictBean> response) {
                if (response.code() == 200) {
                    mProgressBar.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mResultBeanList = response.body().getResult().get(0);
                    mAdapter.refresh(mResultBeanList);
                }
            }

            @Override
            public void onFailure(Call<DistrictBean> call, Throwable t) {

            }
        });
    }

    public static DistrictFragment newInstance(String id) {
        DistrictFragment fragment = new DistrictFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }
}
