package com.zuchexing.carrental.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.zuchexing.carrental.MainActivity;
import com.zuchexing.carrental.R;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
public class MyPersonal extends Activity {

    AlertDialog dialog=null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_personal);

    }

    public void quit(View view){
        Toast.makeText(MyPersonal.this, "退出成功！", Toast.LENGTH_SHORT).show();
        BmobUser.logOut(MyPersonal.this);   //清除缓存用户对象
        Intent it=new Intent(MyPersonal.this,MainActivity.class);
        startActivity(it);
    }

    public void img_head(View view){

        Intent it = new Intent(MyPersonal.this,MyInformation.class);
        startActivity(it);

    }




}
