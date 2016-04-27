package com.zuchexing.carrental.bmob;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 情谊纵横 on 2016/4/27.
 */
public class CarUtil {

    ICar iCar;
    Context context;
    List<Car> datas;

    public CarUtil(Context context,ICar iCar) {
        this.iCar=iCar;
        this.context = context;
    }

    public void findCar(String city){
        datas=new ArrayList<>();
        BmobQuery<Car> query=new BmobQuery<>();
        query.setSkip(2);
        query.addWhereEqualTo("carCity",city);
        query.setLimit(15);//返回15条信息,默认10条
        query.findObjects(context, new FindListener<Car>() {
            @Override
            public void onSuccess(List<Car> list) {
                datas=(ArrayList)list;
                if (datas.size()==0){
                    Toast.makeText(context, "暂未找到该城市周边车辆信息数据!", Toast.LENGTH_SHORT).show();
                }else {
                    iCar.getCar(datas);//接口的方法
                }
            }
            @Override
            public void onError(int i, String s) {
                System.out.println("查询失败："+i+s);
            }
        });
    }
}
