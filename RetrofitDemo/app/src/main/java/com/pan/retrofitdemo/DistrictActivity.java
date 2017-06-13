package com.pan.retrofitdemo;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import org.w3c.dom.Text;

/**
 * Author : Pan
 * Date : 1/5/17
 */

public class DistrictActivity extends AppCompatActivity implements DistrictFragment.ICallback,
        TencentLocationListener{
    private static final String TAG = DistrictActivity.class.getSimpleName();

    private TextView mProvince;
    private TextView mCity;
    private TextView mDistrict;
    private TextView mCurrentLocation;

    private Context mContext;

    private TencentLocationRequest mLocationRequest;
    private TencentLocationManager mLocationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.district_activity);

        this.mContext = this;

        init();
        if (checkNetworkIsConnected()) {
            location();
        }
//        checkGPS();
    }

    @Override
    public void show(int type, String name) {
        if (Constant.PROVINCE == type) {
            mProvince.setTextColor(getResources().getColor(R.color.green));
            mCity.setTextColor(getResources().getColor(R.color.gray));
            mDistrict.setTextColor(getResources().getColor(R.color.gray));
            mProvince.setText(name);
        } else if (Constant.CITY == type) {
            mProvince.setTextColor(getResources().getColor(R.color.green));
            mCity.setTextColor(getResources().getColor(R.color.green));
            mDistrict.setTextColor(getResources().getColor(R.color.gray));
            mCity.setText(name);
        } else if (Constant.DISTRICT == type) {
            mProvince.setTextColor(getResources().getColor(R.color.green));
            mCity.setTextColor(getResources().getColor(R.color.green));
            mDistrict.setTextColor(getResources().getColor(R.color.green));
            mDistrict.setText(name);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void init() {
        mProvince = (TextView) findViewById(R.id.tv_province);
        mCity = (TextView) findViewById(R.id.tv_city);
        mDistrict = (TextView) findViewById(R.id.tv_district);
        mCurrentLocation = (TextView) findViewById(R.id.current_location);

        replace(ProvinceFragment.newInstance());
    }

    private void checkGPS() {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        intent.addCategory("android.intent.category.ALTERNATIVE");
        intent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(this, 0, intent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d(TAG, gps + "");
    }

    private boolean checkNetworkIsConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (null != networkInfo) {
            if (networkInfo.isConnected()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private void location() {
        mLocationRequest = TencentLocationRequest.create();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_POI);
        mLocationRequest.setAllowCache(false);

        mLocationManager = TencentLocationManager.getInstance(this);
        int status = mLocationManager.requestLocationUpdates(mLocationRequest, this);
        Log.d(TAG, status + "");
        checkListenerIsRegistered(status);
    }

    private void checkListenerIsRegistered(int status) {
        switch (status) {
            case 0:
                break;

            case 1:
                Toast.makeText(this, "设备缺少使用腾讯定位SDK需要的基本条件", Toast.LENGTH_SHORT).show();
                break;

            case 2:
                Toast.makeText(this, "配置的 key 不正确", Toast.LENGTH_SHORT).show();
                break;

            case 3:
                Toast.makeText(this, "加载libtencentloc.so失败", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    protected void replace(Fragment fragment) {
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.replace(R.id.contain, fragment);
        transition.commitAllowingStateLoss();
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int error, String s) {
        if (TencentLocation.ERROR_OK == error) {
            mCurrentLocation.setVisibility(View.VISIBLE);
            mCurrentLocation.setText("当前位置：" + tencentLocation.getProvince()
                    + "-" + tencentLocation.getCity()
                    + "-" + tencentLocation.getDistrict());
            Log.d(TAG, "Latitude : " + tencentLocation.getLatitude() + "\n" +
                    "Longitude : " + tencentLocation.getLongitude() + "\n" +
                    "Address : " + tencentLocation.getAddress() + "\n" +
                    "City : " + tencentLocation.getCity() + "\n" +
                    "District : " + tencentLocation.getDistrict() + "\n" +
                    "Name : " + tencentLocation.getName() + "\n" +
                    "Nation : " + tencentLocation.getNation() + "\n" +
                    "Province : " + tencentLocation.getProvince() + "\n" +
                    "Street : " + tencentLocation.getStreet() + "\n" +
                    "Town : " + tencentLocation.getTown() + "\n" +
                    "Village : " + tencentLocation.getVillage());
        } else if (TencentLocation.ERROR_NETWORK == error) {
            Toast.makeText(mContext, "请检查网络连接", Toast.LENGTH_SHORT).show();
        } else if (TencentLocation.ERROR_BAD_JSON == error) {
            Toast.makeText(mContext, "GPS,Wifi或基站错误", Toast.LENGTH_SHORT).show();
        } else if (TencentLocation.ERROR_WGS84 == error) {
            Toast.makeText(mContext, "无法将WGS84坐标转换成GCJ-02坐标", Toast.LENGTH_SHORT).show();
        } else if (TencentLocation.ERROR_UNKNOWN == error) {
            Toast.makeText(mContext, "未知原因", Toast.LENGTH_SHORT).show();
        }

        mLocationManager.removeUpdates(this);
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }
}
