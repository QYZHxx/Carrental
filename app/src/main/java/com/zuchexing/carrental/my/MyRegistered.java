package com.zuchexing.carrental.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.zuchexing.carrental.MainActivity;
import com.zuchexing.carrental.R;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;

/**
 * Created by 情谊纵横 on 2016/4/19
 */
public class MyRegistered extends Activity {

    EditText editText;
    EditText yz;
    Button acquire;
    TextView txt_treaty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_registered);
        editText = (EditText)findViewById(R.id.edt_phone);
        yz=(EditText)findViewById(R.id.yz);
        acquire=(Button)findViewById(R.id.acquire);
        txt_treaty=(TextView)findViewById(R.id.txt_treaty);

    }


    public void txt_treaty(View view){
        show("用户协议 ");
    }


    public void register(View view){
        Intent it = new Intent(MyRegistered.this,MyRegister.class);
        startActivity(it);
        show("登录");
    }


    public void back(View view){
        finish();
        show("返回");
    }

    public void acquire(View view) {

        String number = editText.getText().toString().trim();
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

        String code =yz.getText().toString();
        String number2 = editText.getText().toString().trim();
        final EditText edt_phone = (EditText)findViewById(R.id.edt_phone);
        final EditText edt_szmi = (EditText)findViewById(R.id.edt_szmi);


        BmobSMS.verifySmsCode(MyRegistered.this, number2, code, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException ex) {
                // TODO Auto-generated method stub
                if (ex == null) {//短信验证码已验证成功
                    Toast.makeText(MyRegistered.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(MyRegistered.this, MainActivity.class);
                    startActivity(it);

                    BmobUser user = new BmobUser();
                    user.setUsername(edt_phone.getText().toString());
                    user.setPassword(edt_szmi.getText().toString().trim());
                    user.setMobilePhoneNumber(edt_phone.getText().toString().trim());

                    user.signUp(MyRegistered.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(MyRegistered.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(MyRegistered.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(MyRegistered.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void show(String msg){
        Toast.makeText(MyRegistered.this,"提示信息："+msg,Toast.LENGTH_SHORT).show();
    }
}


