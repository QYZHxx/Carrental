package com.zuchexing.carrental;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.bmob.Order;
import com.zuchexing.carrental.customlayout.TitleLayout;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.FindStatisticsListener;

public class order_table extends AppCompatActivity {
    ListView order_lv;
    Order_table_adaputer adaputer;
    MyUser user;
    Car car;
    TitleLayout title;
    List<Order> copylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_table);
      init();

    }
    public void init(){
        order_lv=(ListView)findViewById(R.id.order_table_lv);
        title=(TitleLayout)findViewById(R.id.title);
        title.setIsHidderServeImage(true);
        title.setIsHidderCollateImage(true);
        title.setTitle("我的订单");

        user= BmobUser.getCurrentUser(this,MyUser.class);
        car=new Car();

        BmobQuery<Order> query=new BmobQuery<>();
        query.addWhereEqualTo("orderUser",user);        //查询条件是当前用户
        query.findObjects(this, new FindListener<Order>() {
            @Override
            public void onSuccess(final List<Order> list) {
                //成功找到后  查询所有订单
                adaputer=new Order_table_adaputer(order_table.this,list);
                order_lv.setAdapter(adaputer);
            }
            @Override
            public void onError(int i, String s) {

            }
        });
    }

    public class Order_table_adaputer extends BaseAdapter{
        Context context;
        LayoutInflater inflater;
        List<Order> list;
        public Order_table_adaputer(Context context,List<Order> list){
            this.context=context;
            inflater=LayoutInflater.from(context);
            this.list=list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if (convertView==null){
                convertView=inflater.inflate(R.layout.order_table_adaputer,parent,false);
                holder=new ViewHolder();
                holder.car_master=(TextView)convertView.findViewById(R.id.order_adaputer_carmatast);
                holder.order_carprice=(TextView)convertView.findViewById(R.id.order_adaputer_carprice);
                holder.order_rentcar=(TextView)convertView.findViewById(R.id.order_adaputer_rentcar);
                holder.order_indent=(TextView)convertView.findViewById(R.id.order_adaputer_indent);
                holder.order_pic=(ImageView)convertView.findViewById(R.id.order_adaputer_pic);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent jump=new Intent(order_table.this,order_details.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("order",list.get(position));
                        car=list.get(position).getOrderCar();
                        System.out.println(car.getCarName());
                        jump.putExtras(bundle);
                        startActivity(jump);
                    }
                });
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }

//            holder.car_master.setText(list.get(position).getOrderCar().getMyUser().getName().toString()+"");
//            System.out.println(list.get(position).getOrderCar().getCarName());
            holder.order_rentcar.setText(list.get(position).getOrdergetcartime()+"——"+list.get(position).getOrderreturntime());
            holder.order_carprice.setText(list.get(position).getOrderPrice().toString());
            holder.order_indent.setText(list.get(position).getCreatedAt());
            if (list.get(position).getOrderCar().getCarImage()!=null){
                String path=car.getCarImage().getUrl()+"";
                Picasso.with(context).load(path).resize(100,80).into(holder.order_pic);
            }else{
                holder.order_pic.setImageResource(R.drawable.a);
            }

            return convertView;
        }
        public class ViewHolder{
            TextView car_master;
            TextView order_carprice;
            TextView order_rentcar;
            TextView order_indent;
            ImageView order_pic;
        }
    }
}
