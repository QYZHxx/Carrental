package com.zuchexing.carrental.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.zuchexing.carrental.R;
import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;


/**
 * Created by Administrator on 2016/4/19 0019.
 */
public class MyRegistered extends Activity {

    EditText editText;
    EditText yz;
    Button acquire;
    Button submit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_registered);
        editText = (EditText)findViewById(R.id.edt_phone);
        yz=(EditText)findViewById(R.id.yz);
        acquire=(Button)findViewById(R.id.acquire);
        submit=(Button)findViewById(R.id.submit);


        BmobSMS.initialize(this,"ccb2e44ea27ebc4c5e9902d40231de9e");

    }

    public void register(View view){
        Intent it = new Intent(MyRegistered.this,MyRegister.class);
        startActivity(it);
    }

    public void back(View view){
        finish();
    }



    public void acquire(View view) {
        String number=editText.getText().toString().trim();
//        System.out.println(number);
        BmobSMS.requestSMSCode(MyRegistered.this, number, "SMS", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {//验证码发送成功
                    Toast.makeText(MyRegistered.this, "验证码发送成功" + integer.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyRegistered.this, "验证码发送失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void submit(View view) {
        String number2=editText.getText().toString().trim();
        String code =yz.getText().toString();
        BmobSMS.verifySmsCode(this, number2, code, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(MyRegistered.this, "验证成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}


