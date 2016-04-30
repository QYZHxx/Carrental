package com.zuchexing.carrental.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zuchexing.carrental.R;

/**
 * Created by Administrator on 2016/4/26 0026.
 */
public class MyAbout extends Activity {

    ImageView img_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_about);
        back();
    }

    public void back(){
        img_back=(ImageView)findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                show("返回");
            }
        });
    }
    public void show(String msg){
        Toast.makeText(MyAbout.this, "提示信息：" + msg, Toast.LENGTH_SHORT).show();
    }

}
