package com.zuchexing.carrental.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zuchexing.carrental.R;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class MyAttestation extends Activity {
    LinearLayout line_carter,line_rent,line_shop;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_attestation);
        initialize();
        onclick();
    }

    public void initialize(){
        line_carter=(LinearLayout)findViewById(R.id.line_carter);
        line_rent  =(LinearLayout)findViewById(R.id.line_rent);
        line_shop  =(LinearLayout)findViewById(R.id.line_shop);
    }

    public void onclick(){
        line_rent.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent(MyAttestation.this,MyPattestation.class);
            startActivity(it);
            finish();
            }
        });

        line_carter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MyAttestation.this,MyCattestation.class);
                startActivity(it);
                finish();
            }
        });


        line_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MyAttestation.this,MySattestation.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void show(String msg){
        Toast.makeText(MyAttestation.this,"提示信息："+msg,Toast.LENGTH_SHORT).show();
    }
}
