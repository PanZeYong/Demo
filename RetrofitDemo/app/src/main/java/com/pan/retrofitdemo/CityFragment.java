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

public class CityFragment extends DistrictBaseFragment implements DistrictAdapter.OnItemClickListener{
    private static final String TAG = CityFragment.class.getSimpleName();

    private List<DistrictBean.ResultBean> mResultBeanList;

    private String mId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerListener();
        mId = getArguments().getString("id");
        getChildren();
    }

    @Override
    public void onItemClick(int position) {
        mCallback.show(Constant.CITY, mResultBeanList.get(position).getFullname());
        mActivity.replace(DistrictFragment.newInstance(mResultBeanList.get(position).getId()));
    }

    private void registerListener() {
        mAdapter.setOnItemClickListener(this);
    }

    private void getChildren() {
        mProgressBar.setVisibility(View.VISIBLE);
        new GithubServiceImpl().getChildren(mId).enqueue(new Callback<DistrictBean>() {
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

    public static CityFragment newInstance(String id) {
        CityFragment fragment = new CityFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }
}
