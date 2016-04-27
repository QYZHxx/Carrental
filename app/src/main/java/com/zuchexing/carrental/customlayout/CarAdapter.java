package com.zuchexing.carrental.customlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.Car;

import java.util.List;

/**
 * Created by 情谊纵横 on 2016/4/22.
 */
public class CarAdapter extends BaseAdapter {

    List<Car> lists;
    Context context;

    public CarAdapter(Context context, List<Car> lists) {

        this.lists = lists;
        this.context = context;
        System.out.println("lists:"+lists.size()+lists.get(1).getObjectId());
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView name, price, address, collect;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Car car=lists.get(position);
        ViewHolder v = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.car_adapter, null);
            v = new ViewHolder();
            v.name=(TextView)convertView.findViewById(R.id.car_name);
            v.price=(TextView)convertView.findViewById(R.id.car_price);
            v.address=(TextView)convertView.findViewById(R.id.car_address);
            v.collect=(TextView)convertView.findViewById(R.id.car_collect);

            convertView.setTag(v);
        }else {
            v=(ViewHolder)convertView.getTag();
        }

        v.name.setText(car.getCarName());
        v.address.setText(car.getCarAddress());
        v.collect.setText("收藏100");
        v.price.setText("￥"+car.getCarRentPrice());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent it=new Intent(context,)
            }
        });
        return convertView;
    }
}
