package com.zuchexing.carrental.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zuchexing.carrental.R;


/**
 * Created by Administrator on 2016/4/19 0019.
 */
public class MyRegister extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_register);


    }

    public void registered(View view){
        Intent it = new Intent(MyRegister.this,MyRegistered.class);
        startActivity(it);
    }

    public void back(View view){
        finish();
    }



}
