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
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016/4/28 0028.
 */
public class MyAlter extends Activity {
    EditText old_password;
    EditText new_password;
    Button btn_alter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_alter);

        old_password=(EditText)findViewById(R.id.old_password);
        new_password=(EditText)findViewById(R.id.new_password);
        btn_alter   =(Button)findViewById(R.id.btn_alter);



    }

    public void alter(View view){
        String old= old_password.getText().toString();
        String fresh  = new_password.getText().toString();
        BmobUser.updateCurrentUserPassword(MyAlter.this, old, fresh, new UpdateListener() {
            @Override
            public void onSuccess() {
                Intent it=new Intent(MyAlter.this,MyRegister.class);
                startActivity(it);
                show("修改成功！");

            }

            @Override
            public void onFailure(int code, String msg) {
                show("旧密码错误！");
            }
        });
    }


//    //读取数据库用户名
//    txt_name=(TextView)view.findViewById(R.id.txt_name);
//    MyUser user = BmobUser.getCurrentUser(context, MyUser.class);
//    txt_name.setText(user.getUsername());

    public void show(String msg){
        Toast.makeText(MyAlter.this, "提示信息：" + msg, Toast.LENGTH_SHORT).show();
    }
}
