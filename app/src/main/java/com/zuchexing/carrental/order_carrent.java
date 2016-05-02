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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.bmob.Order;
import com.zuchexing.carrental.customlayout.TitleLayout;

import java.util.Calendar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class order_carrent extends AppCompatActivity {
    TitleLayout title;
    TextView mastr_name;
    TextView rent;
    Button promptlyrentcar;
    TextView getcartime;
    TextView returntime;
    TextView trim;
    TextView address;
    ImageView car_pic;

    Car car;
    MyUser user;
    int myear;
    int mmonth;
    int mday;
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        title=(TitleLayout)findViewById(R.id.title);
        initview();



    }
    public void initview(){
        car=(Car)this.getIntent().getExtras().getSerializable("cars");
        title.setIsHidderServeImage(true);
        title.setIsHidderCollateImage(true);
        title.setTitle("租赁车辆");
        address=(TextView)findViewById(R.id.order_carrent_address);
        mastr_name=(TextView)findViewById(R.id.order_carrent_name);
        rent=(TextView)findViewById(R.id.order_rent);
        getcartime=(TextView)findViewById(R.id.order_carrent_getcartime);
        returntime=(TextView)findViewById(R.id.order_carrent_returntime);
        trim=(TextView)findViewById(R.id.order_carrent_trim);
        car_pic=(ImageView)findViewById(R.id.order_car_pic);
        System.out.println("输出车辆的对象:"+car.getMyUser().getName());


        mastr_name.setText(car.getCarName() + "");
        rent.setHint(car.getCarRentPrice() + "(/日)");
        address.setText(car.getCarAddress());

        if (car.getCarImage()!=null){
            String path=car.getCarImage().getUrl()+"";
            Picasso.with(this).load(path).resize(100, 60).into(car_pic);
        }else{
            car_pic.setImageResource(R.drawable.a);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void order_comit(View view){

        if (getcartime.getText()!=null&&getcartime.getText()!=""){
            if (returntime.getText()!=null&&returntime.getText()!=""){
               order=new Order();
                order.setOrderCar(car);
                System.out.println(car.getCarName());
                order.setOrderUser(BmobUser.getCurrentUser(this, MyUser.class));
                order.setOrderPrice(Integer.parseInt(rent.getText().toString()));
                order.setOrderTime(Integer.parseInt(trim.getText() + ""));
                order.setOrdergetcartime(getcartime.getText() + "");
                order.setOrderreturntime(returntime.getText() + "");
                order.setOrderState("0x22");
                System.out.println(order.getOrderCar().getCarName());
                order.save(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(order_carrent.this, "订单成功", Toast.LENGTH_SHORT).show();
                        Intent jump = new Intent(order_carrent.this, order_table.class);
                        startActivity(jump);
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(order_carrent.this, "订单失败,请检查你的网络或者重启app", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(this,"请输入你结束租车的时间",Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(this,"请输入你开始租车的时间",Toast.LENGTH_SHORT).show();
        }
    }

    public void  getcartime(View view){
        final Calendar calendar=Calendar.getInstance();
        myear=calendar.get(Calendar.YEAR);
        mmonth=calendar.get(Calendar.MONTH);
        mday =calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    int nowday=(year*365+monthOfYear*30+dayOfMonth)-(myear*365+mmonth*30+mday)+1;
                        if (year >= myear|| monthOfYear >= mmonth || dayOfMonth >= mday) {
                            getcartime.setText(year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日");
                            myear=year;
                            mmonth=monthOfYear;
                            mday=dayOfMonth;
                        } else {
                            Toast.makeText(order_carrent.this, "你选择日期不合法", Toast.LENGTH_SHORT).show();
                            returntime.setText("");
                        }

                }
            }, myear, mmonth, mday);
            dialog.show();

    }
    public void returntimes(View view){
        System.out.println(getcartime.getText() + "设置时间");
        if (getcartime.getText()!=null&&getcartime.getText()!="") {
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    int nowday=(year*365+monthOfYear*30+dayOfMonth)-(myear*365+mmonth*30+mday);
                    System.out.println("nowday:"+nowday);
                        if (nowday>=1&&nowday<=30){
                            System.out.println("当前的时间" + year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                            returntime.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                            trim.setText(nowday + "");
                            if (car.getCarRentPrice()!=null) {
                                int sum = car.getCarRentPrice() * nowday;
                                rent.setText(sum + "");
                            }else{
                                Toast.makeText(order_carrent.this,"因为您选择的金额的数据为空,所以默认将您的价格设置为100",Toast.LENGTH_SHORT).show();
                                int sum = 100 * nowday;
                                rent.setText(sum + "");
                            }
                        }else{
                            Toast.makeText(order_carrent.this,"请选择正确的还车时间",Toast.LENGTH_SHORT).show();
                            returntime.setText("");
                        }
                    }

            }, myear, mmonth, mday);
            dialog.show();
        }else{
            Toast.makeText(this,"请先设置租车日期",Toast.LENGTH_SHORT).show();
        }
    }


}
