package com.pan.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    private Button mGetRopsList;
    private TextView mShowRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        registerListen();
    }

    private void initView() {
        mGetRopsList = (Button) findViewById(R.id.btn_get_repo);
        mShowRepos = (TextView) findViewById(R.id.tv_show_repo);
    }

    private void registerListen() {
        mGetRopsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GithubServiceImpl().getReposList("PanZeYong").enqueue(new Callback<List<RepoBean>>() {
                    @Override
                    public void onResponse(Call<List<RepoBean>> call, Response<List<RepoBean>> response) {
                        for (RepoBean repoBean : response.body()) {
                            mShowRepos.setText(mShowRepos.getText() + "\n" + "Name : " + repoBean.getName() + "   " +
                                    "Id : " + repoBean.getId());
                        }
                        Log.d(TAG, response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<List<RepoBean>> call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                    }
                });
            }
        });
    }
}
