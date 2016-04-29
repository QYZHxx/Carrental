package com.zuchexing.carrental.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.zuchexing.carrental.MainActivity;
import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.MyUser;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;

/**
 * Created by Administrator on 2016/4/25 0025.
 */
public class MyCode extends Activity{

    EditText edt_phone;
    EditText edt_code;
    ImageView img_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_code);
        initialize();
        back();
    }

    public void initialize(){
        edt_phone=(EditText)findViewById(R.id.edt_phone);//手机号码
        edt_code=(EditText)findViewById(R.id.edt_code);//验证码
        img_back=(ImageView)findViewById(R.id.img_back);//返回
    }

    public void back(){
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                show("返回");
            }
        });
    }




    public void acquire(View view){
        String phone= edt_phone.getText().toString().trim();//得到输入的手机号码
        BmobSMS.requestSMSCode(MyCode.this, phone, "SMS", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {//验证码发送成功
                    Toast.makeText(MyCode.this, "验证码发送成功。" + integer.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyCode.this, "验证码发送失败,请检查网络！", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void reset(View view){
        String code = edt_code.getText().toString().trim();//得到验证码
        String phone= edt_phone.getText().toString().trim();//得到输入的手机号码

//        BmobSMS.verifySmsCode(MyCode.this, phone, code, new VerifySMSCodeListener() {
//            @Override
//            public void done(BmobException e) {
//                if(e==null){
//                    Toast.makeText(MyCode.this,"登录成功！",Toast.LENGTH_SHORT).show();
//                    Intent it=new Intent(MyCode.this, MainActivity.class);
//                    startActivity(it);
//
//                }else {
//                    Toast.makeText(MyCode.this,"验证码错误！",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        BmobUser.loginBySMSCode(MyCode.this, phone, code, new LogInListener<MyUser>() {

            @Override
            public void done(MyUser user, BmobException e) {
                if(user!=null){
                    Toast.makeText(MyCode.this,"登录成功！",Toast.LENGTH_SHORT).show();
                    Intent it=new Intent(MyCode.this, MainActivity.class);
                    startActivity(it);
                }else {
                    Toast.makeText(MyCode.this,"验证码输入不正确。！",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void show(String msg){
        Toast.makeText(MyCode.this,"提示信息："+msg,Toast.LENGTH_SHORT).show();
    }

}
