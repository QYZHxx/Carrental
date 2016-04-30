package com.zuchexing.carrental.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zuchexing.carrental.R;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.ResetPasswordByCodeListener;

/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class Mypassword extends Activity {

    Button btn_next;
    EditText edt_phone;
    EditText edt_code;
    EditText edt_szmi;
    ImageView img_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_password);
        initialize();
        back();
    }

    public void initialize(){
        btn_next=(Button)findViewById(R.id.btn_next);
        edt_code=(EditText)findViewById(R.id.edt_code);
        edt_phone=(EditText)findViewById(R.id.edt_phone);
        edt_szmi=(EditText)findViewById(R.id.edt_szmi);
        img_back=(ImageView)findViewById(R.id.img_back);
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
        String phone=edt_phone.getText().toString();
        BmobSMS.requestSMSCode(Mypassword.this, phone, "SMS", new RequestSMSCodeListener() {
            @Override
            public void done(Integer smsId, BmobException ex) {
                // TODO Auto-generated method stub
                if (ex == null) {//验证码发送成功
                    show("发送成功！");
                } else {
                    show("发送失败！");
                }
            }
        });
    }


    public void reset(View view){
        String code=edt_code.getText().toString();
        String phone2=edt_szmi.getText().toString();
       BmobUser.resetPasswordBySMSCode(Mypassword.this, code, phone2, new ResetPasswordByCodeListener() {
           @Override
           public void done(BmobException e) {
               if (e == null) {
                   show("密码设置成功！");
               } else {
                   Toast.makeText(Mypassword.this, "密码设置失败：code=" + e.getErrorCode() + "msg=" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
    public void show(String msg){
        Toast.makeText(Mypassword.this,"提示信息："+msg,Toast.LENGTH_SHORT).show();
    }
}