package com.zuchexing.carrental.lookup;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.map.IMap;
import com.zuchexing.carrental.map.MapSearch;
import com.zuchexing.carrental.map.MapUtil;

import java.io.File;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by 情谊纵横 on 2016/4/19.
 */
public class LookUpFragment extends Fragment implements View.OnClickListener,IMap {


    Context context;
    TextView city_name;
    Button btn_map_search, cx,find_store;
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
        city_name=(TextView)view.findViewById(R.id.city_name);
        util=new MapUtil(context,this);
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
        btn_map_search = (Button) view.findViewById(R.id.btn_map_search);
        find_store=(Button)view.findViewById(R.id.btn_car_store);
        find_store.setOnClickListener(this);
        cx = (Button) view.findViewById(R.id.lookup_btn_cx);
        cx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.loginByAccount(context, "18229869277", "18229869277", new LogInListener<MyUser>() {

                    @Override
                    public void done(MyUser user, BmobException e) {
                        if (user != null) {
                            Log.i("smile", "用户登陆成功");
                        }
                    }
                });
                //

                Intent it = new Intent(context, FindingCar.class);
                startActivity(it);

            }
        });
        btn_map_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, MapSearch.class);
                startActivity(it);
                System.out.println("跳转成功");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_car_store:
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
                Intent it=new Intent(context,FindingCarStore.class);
                startActivity(it);
            break;
        }
    }

    public  void upload(){
        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/UCDownloads/60899635_340.jpg";
       // final BmobFile bmobFile=new BmobFile(new File("/storage/emulated/0/UCDownloads/60899635_340.jpg"));
        System.out.println("path:"+path);
        //String root = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/1.png";
        final BmobFile bmobFile=new BmobFile(new File("/storage/sdcard0/Tencent/zebrasdk/Photoplus.jpg"));
        bmobFile.uploadblock(context, new UploadFileListener() {
            @Override
            public void onSuccess() {
                System.out.println("上传成功!");
                BmobQuery<Car> query = new BmobQuery<>();
                query.getObject(context, "cab055cfdf", new GetListener<Car>() {
                    @Override
                    public void onSuccess(Car car) {
                        System.out.println(car.getCarName());
                        car.setCarImage(bmobFile);
                        car.update(context);
                        System.out.println(car.getCarImage().getUrl()+"url");
                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
            }

            @Override
            public void onFailure(int i, String s) {
                System.out.println("上传失败!" + i + s);
            }
        });
    }
    @Override
    public void getAMapLocation(AMapLocation mapLocation) {
        city_name.setText(mapLocation.getCity());
        util.stopLocation();
    }

}
