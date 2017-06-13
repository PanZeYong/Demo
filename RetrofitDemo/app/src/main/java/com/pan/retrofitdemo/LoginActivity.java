package com.pan.retrofitdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author : Pan
 * Date : 12/11/16
 */

public class LoginActivity extends AppCompatActivity {
    private final static String TAG = LoginActivity.class.getSimpleName();

    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        mUsername = (EditText) findViewById(R.id.et_username);
        mPassword = (EditText) findViewById(R.id.et_password);
        mLogin = (Button) findViewById(R.id.btn_login);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if (null != username && null != password) {
            String credentials = username + ":" + password;
            String authorization =  "Bearer " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    new GithubServiceImpl().login(authorization).enqueue(new Callback<OauthBean>() {
                @Override
                public void onResponse(Call<OauthBean> call, Response<OauthBean> response) {
                    OauthBean bean = response.body();
                    Log.d("TAG", bean.toString());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<OauthBean> call, Throwable t) {
                    Log.d(TAG, t.getMessage());
                }
            });
        }
    }
}
