package com.zuchexing.carrental.customlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.car_information;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
                 Intent it=new Intent(context, car_information.class);
                    it.setAction("com.caradaputer.information");
                Bundle bundle=new Bundle();
                bundle.putString("car_name", lists.get(position).getCarName());
                bundle.putString("car_price", lists.get(position).getCarRentPrice() + "");
                bundle.putString("car_num",lists.get(position).getCarNum());
                bundle.putString("car_address", lists.get(position).getCarAddress());
                bundle.putString("car_Km",lists.get(position).getCarKm()+" km");
                bundle.putString("carage",lists.get(position).getCarAge()+" 年");
                bundle.putString("car_sitter",4+"座");
                it.putExtras(bundle);


                context.startActivity(it);

            }
        });
        return convertView;

    }
}
