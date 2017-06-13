package com.pan.settingsdemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.Preference;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Author : Pan
 * Date : 11/14/16
 */

public class SettingsItemPreference extends Preference {
    private TextView mTitle;
    private ImageView mIcon;

    public SettingsItemPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setLayoutResource(R.layout.settings_item);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        mTitle = (TextView) view.findViewById(R.id.tv_title);
        mIcon = (ImageView) view.findViewById(R.id.iv_icon);

        setTitle(getTitleRes());
        setIcon(getIcon());
    }

    @Override
    public void setTitle(CharSequence title) {
        updateTitle((String) title);
    }

    @Override
    public void setTitle(int titleResId) {
        updateTitle(getContext().getString(titleResId));
    }

    @Override
    public void setIcon(Drawable icon) {
        updateIcon(icon);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setIcon(int iconResId) {
        updateIcon(getContext().getDrawable(iconResId));
    }

    public void updateTitle(String title) {
        if (null != mTitle && null != title) {
            mTitle.setText(title);
        }
    }

    public void updateIcon(Drawable drawable) {
        if (null != mIcon && null != drawable) {
            mIcon.setImageDrawable(drawable);
        }
    }
}
