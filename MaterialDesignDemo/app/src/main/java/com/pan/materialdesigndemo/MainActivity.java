package com.pan.materialdesigndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mFloatingActionButton;
    private Button mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        registerListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_floating_action_button:
                FloatActionButtonActivity.startActivity(this);
                break;

            case R.id.btn_app_bar_layout:
                AppBarLayoutActivity.startActivity(this);
                break;
        }
    }

    private void init() {
        mFloatingActionButton = (Button) findViewById(R.id.btn_floating_action_button);
        mAppBarLayout = (Button) findViewById(R.id.btn_app_bar_layout);
    }

    private void registerListener() {
        mFloatingActionButton.setOnClickListener(this);
        mAppBarLayout.setOnClickListener(this);
    }
}
