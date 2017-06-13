package com.pan.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Author : Pan
 * Date : 11/29/16
 */

public interface GithubService {
    @GET("users/{user}/repos")
    Call<List<RepoBean>> getReposList(@Path("user") String user);

    @FormUrlEncoded
    @POST("/oauth2/token")
    Call<OauthBean> login(@Field("client_id") String clientId,
                       @Field("client_secret") String clientSecret,
                       @Header("Authorization" ) String authorization,
                       @Field("scope") String scope,
                       @Field("grant_type") String grantType);
    @GET("list/")
    Call<DistrictBean> getProvinceList(@Query("key") String key);

    @GET("getchildren?")
    Call<DistrictBean> getChildren(@Query("id") String id, @Query("key") String key);
}
