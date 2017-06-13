package com.pan.vieweventdispatchdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Author : Pan
 * Date : 11/26/16
 */

public class CustomViewGroupActivity extends Activity {
    private HorizontalScrollView mHorizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group);

        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();

        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.hs_scroll_view);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        final int screenWidth = displayMetrics.widthPixels;
        final int screenHeight = displayMetrics.heightPixels;

        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup)
                    inflater.inflate(R.layout.content_layout, mHorizontalScrollView, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.tv_title);
            textView.setText("Page : " + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mHorizontalScrollView.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.list);

        ArrayList<String> datas = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            datas.add("index : " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.adapter_item, R.id.tv_index, datas);

        listView.setAdapter(adapter);
    }
}
