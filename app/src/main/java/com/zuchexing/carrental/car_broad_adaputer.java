package com.zuchexing.carrental;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/4/27 0027.
 */
public class car_broad_adaputer extends BaseAdapter {
        Context context;
        ArrayList<HashMap<String,Object>> data;
        LayoutInflater inflater;
        String[] str;
        int positions;
        Activity activity;
    TextView tv;
    MyGridview_Adaputer adaputer;

    private ArrayList<String[]> list=new ArrayList<>();

        public car_broad_adaputer(Context context,Activity activity,ArrayList<HashMap<String,Object>> data){
            this.context=context;
            this.data=data;
            this.activity=activity;
            inflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView==null){
                convertView=inflater.inflate(R.layout.car_brand_adaputer,parent,false);
                holder=new ViewHolder();
                holder.leter=(TextView)convertView.findViewById(R.id.carbrandadaputer_zimu);
                holder.brad_graidview=(GridView)convertView.findViewById(R.id.brad_graidview);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            System.out.println("数据");
            holder.leter.setText(data.get(position).get("letter") + "");
            adaputer=new MyGridview_Adaputer(context,activity,(String[])data.get(position).get("trademark"));
            holder.brad_graidview.setAdapter(adaputer);

            return convertView;
        }
        public class ViewHolder{
            TextView leter;
            GridView brad_graidview;
        }
}
