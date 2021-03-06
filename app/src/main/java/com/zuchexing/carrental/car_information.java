package com.zuchexing.carrental;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.my.MyRegister;

import cn.bmob.v3.BmobUser;

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
    private ImageView image1;
    Car car;
    Intent it;
    MyUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_information);
       user= BmobUser.getCurrentUser(this, MyUser.class);
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
        image1=(ImageView)findViewById(R.id.car_image1);

        car=(Car)getIntent().getExtras().getSerializable("Car");

        if (car.getCarImage()!=null) {
            String path = car.getCarImage().getUrl() + "";
            Picasso.with(car_information.this).load(path).into(image1);
        }else {
            image1.setImageResource(R.drawable.a);
        }
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
    //点击将该数据的状态改变

    @Override
    public void onClick(View v) {
        if (user!=null) {
            it = new Intent(car_information.this, order_carrent.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("cars", car);
            it.putExtras(bundle);
            startActivity(it);
        }else{
            AlertDialog dialog=null;
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //添加功能
            builder.setTitle("亲,你没有登陆哟!");
            builder.setIcon(R.drawable.heng);
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent jump=new Intent(car_information.this,MyRegister.class);
                    startActivity(jump);
                    finish();
                }
            });
            builder.setMessage("我们去登陆吧!");
            dialog=builder.create();
            dialog.show();
        }
    }
}
