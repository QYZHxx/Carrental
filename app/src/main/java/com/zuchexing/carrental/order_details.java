package com.zuchexing.carrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zuchexing.carrental.bmob.Order;
import com.zuchexing.carrental.customlayout.TitleLayout;

public class order_details extends AppCompatActivity {
    Bundle bundle;
    TitleLayout title;
    TextView car_master;    //车主
    TextView car_masterphone;   //车主电话
    TextView car_cartype;   //租用车辆的类型
    TextView rent_time; //租用期限
    TextView rent_price; //租金
    TextView all_price; //总的消费
    ImageView car_pic;                  //车主的图片
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        bundle=getIntent().getExtras().getBundle("order");
        init();
    }
    public void init(){
        title=(TitleLayout)findViewById(R.id.title);
        title.setIsHidderServeImage(true);
        title.setIsHidderCollateImage(true);
        title.setTitle("租车详情");

        order=(Order)getIntent().getExtras().getSerializable("order");

        car_master=(TextView)findViewById(R.id.details_car_master);
        car_masterphone=(TextView)findViewById(R.id.details_car_masterphone);
        car_cartype=(TextView)findViewById(R.id.details_car_cartype);

        rent_price=(TextView)findViewById(R.id.details_car_rentprice);

        car_pic=(ImageView)findViewById(R.id.details_car_pic);
        //
//        car_master.setText("车主:         "+order.getOrderCar().getMyUser().getName());
//        car_masterphone.setText("车主联系电话:         " + order.getOrderCar().getMyUser().getMobilePhoneNumber());
//        car_cartype.setText("车子的品牌:         "+order.getOrderCar().getCarName());
//        rent_price.setText("车子租用的单价:         "+order.getOrderCar().getCarRentPrice());
//        rent_price.setText("租车的消费         "+order.getOrderCar().getCarRentPrice());
        car_master.setText("车主:         ");
        car_masterphone.setText("车主联系电话:         ");
        car_cartype.setText("车子的品牌:         ");
        rent_price.setText("车子租用的单价:         ");
        rent_price.setText("租车的消费         ");
        if (order.getOrderCar().getCarImage()!=null){
            String path = order.getOrderCar().getCarImage().getUrl() + "";
            Picasso.with(order_details.this).load(path).into(car_pic);
        }else{
            car_pic.setImageResource(R.drawable.a);
        }
    }


}
