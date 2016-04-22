package com.zuchexing.carrental.lookup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.map.MapSearch;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 情谊纵横 on 2016/4/19.
 */
public class LookUpFragment extends Fragment {


    Context context;
    Button btn_map_search, cx;
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
        cx = (Button) view.findViewById(R.id.lookup_btn_cx);
        cx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                BmobQuery<Car> query = new BmobQuery<Car>();
//                query.getObject(context, "5321a8e1d0", new GetListener<Car>() {
//                    @Override
//                    public void onSuccess(Car car) {
//                        MyUser user = BmobUser.getCurrentUser(context, MyUser.class);
//                        BmobRelation relation = new BmobRelation();
//                        relation.add(car);
//                        user.setCarCollecs(relation);
//                        user.update(context, new UpdateListener() {
//                            @Override
//                            public void onSuccess() {
//
//                                Log.i("life", "多对多关联添加成功");
//                            }
//
//                            @Override
//                            public void onFailure(int i, String s) {
//                                Log.i("life", "多对多关联添加失败" + i + s);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailure(int i, String s) {
//
//                    }
//                });
                MyUser user = BmobUser.getCurrentUser(context, MyUser.class);
                BmobQuery<Car> query=new BmobQuery<Car>();
                query.addWhereRelatedTo("carCollecs",new BmobPointer(user));
                query.findObjects(context, new FindListener<Car>() {
                    @Override
                    public void onSuccess(List<Car> list) {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(list.get(i).getCarName());
                        }
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });

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
}
