package com.zuchexing.carrental;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuchexing.carrental.bmob.Car;

public class car_information extends AppCompatActivity implements View.OnClickListener{
    private TextView carName;
    private TextView carprice;
    private TextView master_name;
    private TextView probability;   //接单率
    private TextView time;      //响应时间
    private TextView address;       //取车地址
    private TextView carnum;    //车牌号
    private TextView carsiter;      //车载数
    private TextView carKm; //车辆行驶距离
    private TextView carage;   //车龄
    private Button ding;   //开始订购
    private LinearLayout layout;
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_information);
        init();
    }
    public void init(){
        carName=(TextView)findViewById(R.id.Car_information_carname);
        carprice=(TextView)findViewById(R.id.Car_information_price);
        master_name=(TextView)findViewById(R.id.car_information_master);
        probability=(TextView)findViewById(R.id.car_information_probability);
        time=(TextView)findViewById(R.id.car_information_time);
        address=(TextView)findViewById(R.id.car_information_Address);
        carnum=(TextView)findViewById(R.id.car_information_carnum);
        carKm=(TextView)findViewById(R.id.car_information_carKm);
        carage=(TextView)findViewById(R.id.car_information_carage);
        ding=(Button)findViewById(R.id.car_information_btn_ding);
        carsiter=(TextView)findViewById(R.id.car_information_carsiter);
        layout=(LinearLayout)findViewById(R.id.Car_information_layout);

        Car car=(Car)getIntent().getExtras().getSerializable("Car");
//
//        bundle.putString("car_name", lists.get(position).getCarName());
//        bundle.putString("car_price", lists.get(position).getCarRentPrice() + "");
//        bundle.putString("car_num",lists.get(position).getCarNum());
//        bundle.putString("car_address", lists.get(position).getCarAddress());
//        bundle.putString("car_Km",lists.get(position).getCarKm()+" km");
//        bundle.putString("carage",lists.get(position).getCarAge()+" 年");
//        bundle.putString("car_sitter",4+"座");

        carName.setText(car.getCarName()+"");
        carprice.setText("￥"+car.getCarRentPrice());
        address.setText(car.getCarAddress()+"");
        carnum.setText(car.getCarNum()+"");
        carKm.setText(car.getCarKm()+"km");
        carage.setText(car.getCarAge()+"");
        carsiter.setText(car.getCarVehices()+"");

        ding.setOnClickListener(this);

    }
    public void car_information_down(View view){
        if (layout.getVisibility()==View.GONE) {
            layout.setVisibility(View.VISIBLE);
        }else if (layout.getVisibility()==View.VISIBLE){
            layout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        it=new Intent(car_information.this,order_carrent.class);
        startActivity(it);
    }
}
