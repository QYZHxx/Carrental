package com.zuchexing.carrental.my;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zuchexing.carrental.R;

/**
 * Created by 情谊纵横 on 2016/4/19.
 */
public class MyFragment extends Fragment {


    private ImageView img_register;
    private ImageView img_registered;
    LinearLayout line_service;
    LinearLayout line_insyre;
    LinearLayout line_tretey;
    LinearLayout line_rule;
    LinearLayout line_about;
    Context context;
    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fgm_frag, null);

        img_register=(ImageView)view.findViewById(R.id.img_register);
        img_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MyRegister.class);
                startActivity(intent);
            }
        });

        img_registered=(ImageView)view.findViewById(R.id.img_registered);
        img_registered.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyRegistered.class);
                startActivity(intent);
            }
        });

        line_service=(LinearLayout)view.findViewById(R.id.line_service);
        line_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show("服务中心");
            }
        });

        line_insyre=(LinearLayout)view.findViewById(R.id.line_insyre);
        line_insyre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show("保险保障");
            }
        });

        line_tretey=(LinearLayout)view.findViewById(R.id.line_tretey);
        line_tretey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("用户协议");
            }
        });


        line_rule=(LinearLayout)view.findViewById(R.id.line_rule);
        line_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("平台规则");
            }
        });


        line_about=(LinearLayout)view.findViewById(R.id.line_about);
        line_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context,MyAbout.class);
                startActivity(it);
            }
        });

        return view;
    }

    public void show(String msg){
        Toast.makeText(context,"提示信息："+msg,Toast.LENGTH_SHORT).show();
    }

}
