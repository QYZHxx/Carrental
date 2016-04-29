package com.zuchexing.carrental.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.bmob.ICar;
import com.zuchexing.carrental.bmob.MyUser;

import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * 这是车库
 */
public class My_carbarn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_carbarn);
        MyUser user= BmobUser.getCurrentUser(this,MyUser.class);

    }
    public void init(){

    }

}
