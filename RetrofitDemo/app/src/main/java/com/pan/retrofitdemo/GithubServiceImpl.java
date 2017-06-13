package com.pan.retrofitdemo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author : Pan
 * Date : 11/29/16
 */

public class GithubServiceImpl{

    private GithubService mService;

    private String mBasic;

    public GithubServiceImpl() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(new HeaderInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.DISTRICT_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(GithubService.class);
    }

    public Call<List<RepoBean>> getReposList(String user) {
        return mService.getReposList(user);
    }

    public Call<OauthBean> login(String authorization) {
        return mService.login(Constant.CLIENT_ID, Constant.CLIENT_SECRET, authorization,
                Constant.BASIC_SCOPE, Constant.GRANT_TYPE_PASSWORD);
    }

    public Call<DistrictBean> getProvinceList() {
        return mService.getProvinceList(Constant.KEY);
    }

    public Call<DistrictBean> getChildren(String id) {
        return mService.getChildren(id, Constant.KEY);
    }

    public class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }
    }
}
