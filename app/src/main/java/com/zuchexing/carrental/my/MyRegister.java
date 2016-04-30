package com.zuchexing.carrental.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.autonavi.amap.mapcore.Md5Utility;
import com.zuchexing.carrental.MainActivity;
import com.zuchexing.carrental.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by 情谊纵横 on 2016/4/19
 */
public class MyRegister extends Activity {

    private AlertDialog dialog;
    private SharedPreferences sp;
    private EditText sjh;
    private EditText mima;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_register);

        sp=getSharedPreferences("config",MODE_PRIVATE);//初始化sp
        sjh=(EditText)findViewById(R.id.sjh);
        mima=(EditText)findViewById(R.id.mima);

        //获取sp里面存储的数据
        String savedsjh=sp.getString("sjh","");
        String savedpassword=sp.getString("password","");
        sjh.setText(savedsjh);
        mima.setText(savedpassword);

    }
    public void registered(View view){
        Intent it = new Intent(MyRegister.this,MyRegistered.class);
        startActivity(it);
    }
    public void register2(View view){
        LinearLayout isloginform=(LinearLayout)getLayoutInflater().inflate(R.layout.my_nvt_password, null);
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(isloginform);
        dialog=builder.create();
        dialog.show();
        Button btn_back =(Button)isloginform.findViewById(R.id.btn_back);
        Button btm_forget_password = (Button)isloginform.findViewById(R.id.btm_forget_password);
        Button btm_msm_register    = (Button)isloginform.findViewById(R.id.btn_sms_register);
        btm_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MyRegister.this,Mypassword.class);
                startActivity(it);
            }
        });
        btm_msm_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MyRegister.this, MyCode.class);
                startActivity(it);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });

    }
    public void back(View view){
        finish();
        show("返回");
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
                Intent it = new Intent(MyRegister.this, MainActivity.class);
                startActivity(it);
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MyRegister.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void show(String msg){
        Toast.makeText(MyRegister.this,"提示信息："+msg,Toast.LENGTH_SHORT).show();
    }

}
