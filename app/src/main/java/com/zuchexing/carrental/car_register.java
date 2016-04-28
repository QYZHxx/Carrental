package com.zuchexing.carrental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zuchexing.carrental.customlayout.TitleLayout;

public class car_register extends AppCompatActivity implements View.OnClickListener{

    TitleLayout title;
    LinearLayout brandselect;
    LinearLayout address;
    TextView master_name;
    Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_register);
        title=(TitleLayout)findViewById(R.id.title);

        initview();

        brandselect.setOnClickListener(this);
        address.setOnClickListener(this);
    }
    public void initview(){
        title.setIsHidderCollateImage(true);
        title.setIsHidderServeImage(true);
        title.setTitle("提交车辆资料");
        brandselect=(LinearLayout)findViewById(R.id.carregister_brandselect);
        address=(LinearLayout)findViewById(R.id.car_register_address);
        master_name=(TextView)findViewById(R.id.carregister_name);

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.carregister_brandselect){
            it=new Intent(this,car_brand_select.class);
            startActivity(it);
        }else if (v.getId()==R.id.car_register_address){

        }

    }
}
