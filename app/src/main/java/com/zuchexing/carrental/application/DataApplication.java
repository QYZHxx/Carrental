package com.zuchexing.carrental.application;

import android.app.Application;

import com.amap.api.location.AMapLocation;
import com.zuchexing.carrental.map.IMap;
import com.zuchexing.carrental.map.MapUtil;

/**
 * Created by 情谊纵横 on 2016/4/27.
 */
public class DataApplication extends Application implements IMap { //全局变量

    private String city;//当前定位是哪个城市
    MapUtil mapUtil;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void onCreate() {
        mapUtil = new MapUtil(getApplicationContext(), this);
        mapUtil.startLocation();
        super.onCreate();
    }

    @Override
    public void getAMapLocation(AMapLocation mapLocation) {
        setCity(mapLocation.getCity());
        System.out.println(mapLocation.getCity()+"get");
        mapUtil.stopLocation();
    }
}
