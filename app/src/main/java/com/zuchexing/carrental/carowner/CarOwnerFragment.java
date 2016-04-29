package com.zuchexing.carrental.carowner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.MyUser;
import  com.zuchexing.carrental.car_register;
import com.zuchexing.carrental.my.MyRegister;

import cn.bmob.v3.BmobUser;

/**
 * Created by 情谊纵横 on 2016/4/19.
 */
public class CarOwnerFragment extends Fragment implements View.OnClickListener {

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    Button findcar;
    Context context ;
    Intent jump;
    MyUser user;
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        this.context=context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carowner_frag, null);
        layout1=(LinearLayout)view.findViewById(R.id.carowner_tv_how);
        layout2=(LinearLayout)view.findViewById(R.id.carowner_tv_interest);
        layout3=(LinearLayout)view.findViewById(R.id.carowner_tv_safe);
        findcar=(Button)view.findViewById(R.id.carowner_bt_findcar);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        findcar.setOnClickListener(this);


        user= BmobUser.getCurrentUser(context,MyUser.class);

        return view;

        //jhj
    }


    @Override
    public void onClick(View v) {
            if (user==null){
                AlertDialog dialog=null;
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                //添加功能
                builder.setTitle("亲,你没有登陆哟!");
                builder.setIcon(R.drawable.heng);
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jump=new Intent(context,MyRegister.class);
                        startActivity(jump);
                    }
                });
                builder.setMessage("我们去登陆吧!");
                dialog=builder.create();
                dialog.show();
            }else{
                jump=new Intent(context,car_register.class);
                startActivity(jump);
            }

        }

    }

