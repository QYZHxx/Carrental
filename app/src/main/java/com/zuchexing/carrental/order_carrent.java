package com.zuchexing.carrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zuchexing.carrental.customlayout.TitleLayout;

public class order_carrent extends AppCompatActivity {
    TitleLayout title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        title=(TitleLayout)findViewById(R.id.title);
        initview();
    }
    public void initview(){
        title.setIsHidderServeImage(true);
        title.setIsHidderCollateImage(true);
        title.setTitle("租赁车辆");

    }
}
