package com.zuchexing.carrental.lookup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.map.IMap;
import com.zuchexing.carrental.map.MapSearch;
import com.zuchexing.carrental.map.MapUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by 情谊纵横 on 2016/4/19.
 */
public class LookUpFragment extends Fragment implements View.OnClickListener, IMap {


    Context context;
    TextView city_name, address;
    ImageView image_map;
    LinearLayout btn_map_search, car_find, find_store;
    AdvertFragment advertFragment;
    FragmentManager fManager;
    View view;
    MapUtil util;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lookup_frag, null);
        city_name = (TextView) view.findViewById(R.id.city_name);
        address = (TextView) view.findViewById(R.id.lookup_address);
        image_map = (ImageView) view.findViewById(R.id.lookup_map);
        util = new MapUtil(context, this);
        util.startLocation();
        initView();
        return view;
    }

    public void initView() {
        fManager = getFragmentManager();
        FragmentTransaction transaction = fManager.beginTransaction();
        advertFragment = new AdvertFragment();
        transaction.add(R.id.advert_frag, advertFragment);
        transaction.show(advertFragment);
        transaction.commit();
        btn_map_search = (LinearLayout) view.findViewById(R.id.btn_map_search);
        find_store = (LinearLayout) view.findViewById(R.id.lookup_store_find);
        car_find = (LinearLayout) view.findViewById(R.id.lookup_car_find);
        getMap();
        find_store.setOnClickListener(this);
        btn_map_search.setOnClickListener(this);
        car_find.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lookup_store_find:
//                String[] d=new String[]{"28.144602","28.142602","28.145602","28.146602","28.140602"};
//                String[] j=new String[]{"112.989948","112.987948","112.986948","112.982948","112.981948"};
//                for (int i=0;i<5;i++){
//                    Car car=new Car();
//                    car.setCarName("奔驰x"+(i+1));
//                    car.setMyUser(BmobUser.getCurrentUser(context, MyUser.class));
//                    car.setCarCity("长沙市");
//                    car.setCarLatitude(d[i]);
//                    car.setCarLongitude(j[i]);
//                    car.setCarRentPrice(100);
//                    car.save(context, new SaveListener() {
//                        @Override
//                        public void onSuccess() {
//                            System.out.println("添加成功");
//                        }
//
//                        @Override
//                        public void onFailure(int i, String s) {
//
//                        }
//                    });
//                }
                upload();
                Intent it = new Intent(context, FindingCarStore.class);
                startActivity(it);
                break;
            case R.id.btn_map_search:
                Intent it1 = new Intent(context, MapSearch.class);
                startActivity(it1);
                System.out.println("跳转成功");
                break;
            case R.id.lookup_car_find:
                BmobUser.loginByAccount(context, "18229869277", "18229869277", new LogInListener<MyUser>() {

                    @Override
                    public void done(MyUser user, BmobException e) {
                        if (user != null) {
                            Log.i("smile", "用户登陆成功");
                        }
                    }
                });
                Intent it2 = new Intent(context, FindingCar.class);
                startActivity(it2);
                break;
        }
    }

    public void upload() {

    }

    public void getMap() {

       String path= Environment.getExternalStorageDirectory() + "/test_map"
                + ".png";
        Bitmap bit =BitmapFactory.decodeFile(path);
        if (bit!=null){
            image_map.setImageBitmap(bit);
        }

       // Picasso.with(context).load(new File(path)).into(image_map);
    }

    @Override
    public void getAMapLocation(AMapLocation mapLocation) {
        city_name.setText(mapLocation.getCity());
        address.setText(mapLocation.getProvince() + mapLocation.getCity() + mapLocation.getDistrict() + mapLocation.getStreet());
        util.stopLocation();
    }

}
