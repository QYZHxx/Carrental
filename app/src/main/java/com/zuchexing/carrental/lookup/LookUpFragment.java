package com.zuchexing.carrental.lookup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.map.MapSearch;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 情谊纵横 on 2016/4/19.
 */
public class LookUpFragment extends Fragment implements View.OnClickListener {


    Context context;
    Button btn_map_search, cx,find_store;
    AdvertFragment advertFragment;
    FragmentManager fManager;
    View view;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lookup_frag, null);
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
                        if(user!=null){
                            Log.i("smile", "用户登陆成功");
                        }
                    }
                });
                Intent it = new Intent(context, FindingCar.class);
                startActivity(it);

            }
        });
        btn_map_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser myUser= BmobUser.getCurrentUser(context,MyUser.class);
                myUser.setNickname("测试1");
                myUser.update(context, new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        System.out.println("更新成功");
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        System.out.println("更新失败"+i+s);
                    }
                });

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
//                String[] d=new String[]{"28.134602","28.132602","28.135602","28.136602","28.130602"};
//                String[] j=new String[]{"112.999948","112.997948","112.996948","112.992948","112.991948"};
//                for (int i=0;i<5;i++){
//                    Car car=new Car();
//                    car.setCarName("宝马x"+(i+1));
//                    car.setMyUser(BmobUser.getCurrentUser(context, MyUser.class));
//                    car.setCarCity("长沙市");
//                    car.setCarLatitude(d[i]);
//                    car.setCarLongitude(j[i]);
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
                Intent it=new Intent(context,FindingCarStore.class);
                startActivity(it);
            break;
        }
    }
}
