package com.pan.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author : Pan
 * Date : 1/9/17
 */

public class Network {
    private static final String TAG = Network.class.getSimpleName();
    private static List<DistrictBean.ResultBean> mResultBeanList;
    public static List<DistrictBean.ResultBean> getProvinceList() {
        new GithubServiceImpl().getProvinceList().enqueue(new Callback<DistrictBean>() {
            @Override
            public void onResponse(Call<DistrictBean> call, Response<DistrictBean> response) {
                if (response.code() == 200) {
                    List<List<DistrictBean.ResultBean>> result = response.body().getResult();
                    mResultBeanList = result.get(0);
                }
            }

            @Override
            public void onFailure(Call<DistrictBean> call, Throwable t) {

            }
        });

        return mResultBeanList;
    }
}
