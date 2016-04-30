package com.zuchexing.carrental.my;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.bmob.MyUser;
import java.util.Calendar;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class MyCar extends Activity {
    EditText car_register,edt_car_carKm,edt_car,edt_car_carm,car_register_carage,car_type;
    LinearLayout line_age;
    Button btn_car_submit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_car);
        initialize();

    }

    public void initialize() {
        car_register =(EditText)findViewById(R.id.car_register); //车牌号
        car_type=(EditText)findViewById(R.id.car_type);//车名
        edt_car_carKm=(EditText)findViewById(R.id.edt_car_carKm);//行驶距离
        edt_car      =(EditText)findViewById(R.id.edt_car);      //车载人数
        edt_car_carm =(EditText)findViewById(R.id.edt_car_carm); //出租价格
        car_register_carage=(EditText)findViewById(R.id.car_register_carage);//购车时间
        line_age=(LinearLayout)findViewById(R.id.line_age);
        btn_car_submit=(Button)findViewById(R.id.btn_car_submit);
        line_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(MyCar.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        EditText show = (EditText) findViewById(R.id.car_register_carage);
                        show.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                    }

                }
                        , c.get(Calendar.YEAR)
                        , c.get(Calendar.MONTH)
                        , c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    public void submit(View view){
        Car car = new Car();
        car.setCarNum(car_register.getText().toString());
        car.setCarName(car_type.getText().toString());
        car.setCarKm(Integer.valueOf(edt_car_carKm.getText().toString()));
        System.out.println("设置车辆的车程:"+edt_car_carm.getText().toString());
//        car.setCarVehices(edt_car.getText().toString());
        car.setCarRentPrice(Integer.valueOf(edt_car_carm.getText().toString()));
        car.setCarDate(car_register_carage.getText().toString());
        car.setMyUser(BmobUser.getCurrentUser(MyCar.this, MyUser.class));
        car.save(MyCar.this, new SaveListener() {
            @Override
            public void onSuccess() {
                show("添加车辆信息成功！");
            }

            @Override
            public void onFailure(int i, String s) {
                show("添加失败！");
            }
        });
    }


    public void show(String msg){
        Toast.makeText(MyCar.this, "提示信息：" + msg, Toast.LENGTH_SHORT).show();
    }

}
