package com.pan.settingsdemo;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference);
    }

//    @Override
//    public void onBuildHeaders(List<Header> target) {
//        super.onBuildHeaders(target);
//        loadHeadersFromResource(R.xml.preference, target);
//    }
}
