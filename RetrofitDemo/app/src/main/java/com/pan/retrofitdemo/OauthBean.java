package com.pan.retrofitdemo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Author : Pan
 * Date : 12/12/16
 */

public class OauthBean {
    @SerializedName(Constant.KEY_TOKEN)
    private String accessToken;

    @SerializedName(Constant.KEY_EXPIRED_IN)
    private long expiresIn;

    @SerializedName(Constant.KEY_SCOPE)
    private ArrayList<String> scope;

    @SerializedName(Constant.KEY_REFRESH_TOKEN)
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public ArrayList<String> getScope() {
        return scope;
    }

    public void setScope(ArrayList<String> scope) {
        this.scope = scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "[" + "accessToken : " + accessToken + " " +
                "expiresIn : " + expiresIn + " " +
                "refreshToken : " + refreshToken + " " +
                "]";
    }
}
