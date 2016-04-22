package com.zuchexing.carrental.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zuchexing.carrental.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;


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

    public void register(View view){
        EditText edt_mima = (EditText)findViewById(R.id.mima);
        EditText editText = (EditText)findViewById(R.id.sjh);

        BmobUser user = new BmobUser();
        user.setUsername(editText.getText().toString().trim());
        user.setPassword(edt_mima.getText().toString().trim());
        user.login(MyRegister.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MyRegister.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(MyRegister.this, MyPersonal.class);
                startActivity(it);
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MyRegister.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
