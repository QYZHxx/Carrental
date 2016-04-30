package com.zuchexing.carrental;

import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.bmob.Order;
import com.zuchexing.carrental.customlayout.TitleLayout;

import java.util.Calendar;

public class order_carrent extends AppCompatActivity {
    TitleLayout title;
    TextView mastr_name;
    TextView rent;
    Button promptlyrentcar;
    TextView getcartime;
    TextView returntime;
    Car car;
    int myear;
    int mmonth;
    int mday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        title=(TitleLayout)findViewById(R.id.title);
        initview();



    }
    public void initview(){
        car=(Car)this.getIntent().getExtras().getSerializable("cars");
        System.out.println("当前车辆的"+car.getCarName());
        title.setIsHidderServeImage(true);
        title.setIsHidderCollateImage(true);
        title.setTitle("租赁车辆");

        mastr_name=(TextView)findViewById(R.id.order_carrent_name);
        rent=(TextView)findViewById(R.id.order_rent);
        getcartime=(TextView)findViewById(R.id.order_carrent_getcartime);
        returntime=(TextView)findViewById(R.id.order_carrent_returntime);

        mastr_name.setText(car.getCarName()+"");
        rent.setText(car.getCarRentPrice() + "");
    }

    public void order_comit(View view){
        if (getcartime.getText()!=null){
            if (returntime.getText()!=null){
                Order order=new Order();
                order.setOrderCar(car);
                order.setOrderUser(car.getMyUser());
                order.setOrderPrice(car.getCarRentPrice());
                order.setOrderState("0x22");
            }else{
                Toast.makeText(this,"请输入你结束租车的时间",Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(this,"请输入你开始租车的时间",Toast.LENGTH_SHORT).show();
        }
    }


    public void returntimes(View view){

        final Calendar calendar=Calendar.getInstance();
        myear=calendar.get(Calendar.YEAR);
        mmonth=calendar.get(Calendar.MONTH);
        mday =calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (view.getId()==R.id.order_carrent_returntime) {
                    if (year >= myear && monthOfYear >= mmonth && dayOfMonth >= mday) {
                        returntime.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                    } else {
                        Toast.makeText(order_carrent.this, "你选择日期不合法", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, myear, mmonth, mday);
        dialog.show();
    }
    public void  getcartime(View view){
        if (returntime.getText()!=null){
            DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    if (view.getId()==R.id.order_carrent_returntime) {
                        if (year >= myear && monthOfYear >= mmonth && dayOfMonth >= mday) {
                            returntime.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                        } else {
                            Toast.makeText(order_carrent.this, "你选择日期不合法", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }, myear, mmonth, mday);
            dialog.show();
        }else{
            Toast.makeText(this,"请先设置租车日期",Toast.LENGTH_SHORT).show();
        }
    }

}
