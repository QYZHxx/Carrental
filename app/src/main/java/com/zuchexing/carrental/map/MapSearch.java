package com.zuchexing.carrental.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.bmob.CarUtil;
import com.zuchexing.carrental.bmob.ICar;
import com.zuchexing.carrental.customlayout.TitleLayout;

import java.util.List;

/**
 * Created by 情谊纵横 on 2016/4/19.
 */
//地图搜索周边车辆
public class MapSearch extends AppCompatActivity implements IMap,ICar {


    AMapLocation mapLocation;
    UiSettings ui;
    MapView mapView;
    AMap aMap;
    MapUtil mapUtil;
    TitleLayout title;
    List<Car> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_search);
        mapView = (MapView) findViewById(R.id.map);
        title=(TitleLayout)findViewById(R.id.map_title);
        title.setTitle("地图查找");
        title.setIsHidderCollateImage(true);
        mapView.onCreate(savedInstanceState);
        init();
    }

    private void updatePosttion(AMapLocation location){//根据定位更新地图
        LatLng pos=new LatLng(location.getLatitude(),location.getLongitude());
        System.out.println("city:" + location.getCity());
        CameraUpdate cu= CameraUpdateFactory.changeLatLng(pos);
        aMap.moveCamera(cu);
        aMap.clear();

        //绘制周边车辆
        CarUtil carUtil=new CarUtil(MapSearch.this,MapSearch.this);
        carUtil.findCar(location.getCity());

        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(pos);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_dot));
        markerOptions.draggable(true);
        markerOptions.title(location.getStreet());
        markerOptions.perspective(true);
        System.out.println(location.getStreet());

        Marker marker=aMap.addMarker(markerOptions);

        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                System.out.println(marker.getTitle()+"xx"+"id"+marker.getId());

                aMap.moveCamera(CameraUpdateFactory.changeLatLng( marker.getPosition()));
                return true;
            }
        });

        mapUtil.stopLocation();//停止定位
        System.out.println("停止定位");

    }
    private void init() {//初始化
        mapUtil=new MapUtil(MapSearch.this,MapSearch.this);
        mapUtil.startLocation();//开始定位
        System.out.println("开始定位");
        if (aMap == null) {
            aMap = mapView.getMap();
            CameraUpdate cu= CameraUpdateFactory.zoomTo(15);
            aMap.moveCamera(cu);
            CameraUpdate titleUpdate=CameraUpdateFactory.changeTilt(20);
            aMap.moveCamera(titleUpdate);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void getAMapLocation(AMapLocation mapLocation) {//接口的回调
        this.mapLocation=mapLocation;
        updatePosttion(mapLocation);//定位
    }


    @Override
    public void getCar(List<Car> cars) {//绘制车辆覆盖点
        this.cars=cars;
        for (int i=0;i<cars.size();i++){
            LatLng pos=new LatLng(Double.parseDouble(cars.get(i).getCarLatitude()),
                    Double.parseDouble(cars.get(i).getCarLongitude()));
            MarkerOptions markerOptions=new MarkerOptions();
            markerOptions.position(pos);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
            markerOptions.perspective(true);
            markerOptions.draggable(true);
            markerOptions.title(cars.get(i).getCarName());
            aMap.addMarker(markerOptions);
        }
    }
}
