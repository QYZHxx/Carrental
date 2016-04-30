package com.zuchexing.carrental.my;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.zuchexing.carrental.MainActivity;
import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.bmob.Store;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 情谊纵横 on 2016/4/20
 */
public class MyPersonal extends Fragment {

    private ImageView img_head;
    private Button btn_quit;

    TextView txt_name;
    LinearLayout line_garage;
    LinearLayout txt_about;
    LinearLayout line_alter;
    LinearLayout line_attestation;


    Context context;
    private AlertDialog dialog;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fgm_personal, null);

        //实名认证
        line_attestation=(LinearLayout)view.findViewById(R.id.line_attestation);
        line_attestation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(context,MyAttestation.class);
                startActivity(it);
            }

        });

        line_alter=(LinearLayout)view.findViewById(R.id.line_alter);
        line_alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context,MyAlter.class);
                startActivity(it);
            }
        });

        img_head=(ImageView)view.findViewById(R.id.img_head);
        img_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, MyInformation.class);
                startActivity(it);
            }
        });

        btn_quit=(Button)view.findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Toast.makeText(context, "退出成功！", Toast.LENGTH_SHORT).show();
        BmobUser.logOut(context);   //清除缓存用户对象
        Intent it = new Intent(context, MainActivity.class);
        startActivity(it);

            }
        });

        //读取数据库用户名
        txt_name=(TextView)view.findViewById(R.id.txt_name);
        MyUser user = BmobUser.getCurrentUser(context,MyUser.class);
        txt_name.setText(user.getUsername());

        //店铺
        line_garage=(LinearLayout)view.findViewById(R.id.line_garage);
        line_garage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser user = BmobUser.getCurrentUser(context,MyUser.class);
                BmobQuery<Store> query = new BmobQuery<Store>();
                query.addWhereEqualTo("myUser", user);
                query.findObjects(context, new FindListener<Store>() {
                    @Override
                    public void onSuccess(List<Store> list) {

                        if(list.size()!=0){
                            Intent it1 =new Intent(context,MyShop.class);
                            startActivity(it1);
                            show("店铺");
                        }else {
                            Intent it = new Intent(context,MyGarage.class);
                            startActivity(it);
                            show("添加店铺");
                        }
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
            }
        });

        txt_about=(LinearLayout)view.findViewById(R.id.txt_about);
        txt_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(context,MyAbout.class);
                startActivity(it);
            }
        });

        return view;
    }

    public void show(String msg){
        Toast.makeText(context,"提示信息："+msg,Toast.LENGTH_SHORT).show();
    }

}