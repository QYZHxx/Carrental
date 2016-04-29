package com.zuchexing.carrental.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.bmob.Store;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
/**
 * Created by Administrator on 2016/4/26 0026.
 */
public class MyGarage extends Activity {

    private AlertDialog dialog;
    ImageView img_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_garage);
        initialize();
        back();
    }

    public void initialize(){
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

    public void registered(View view){
        MyUser user = BmobUser.getCurrentUser(this,MyUser.class);
        BmobQuery<Store> query = new BmobQuery<Store>();
        query.addWhereEqualTo("myUser", user);
        query.findObjects(this, new FindListener<Store>() {
            @Override
            public void onSuccess(List<Store> list) {
                v();
            }

            @Override
            public void onError(int i, String s) {
                Intent it =new Intent(MyGarage.this,MyShop.class);
                startActivity(it);
            }
        });


//        final Button back=(Button)isloginform.findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

    }

    public void v(){
        LinearLayout isloginform = (LinearLayout) getLayoutInflater().inflate(R.layout.my_nvt_parking, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(isloginform);
        dialog = builder.create();
        dialog.show();

        final Button btn_new = (Button) isloginform.findViewById(R.id.btn_new);
        final EditText edt_name = (EditText) isloginform.findViewById(R.id.edt_name);
        final Button btn_cancel=(Button)isloginform.findViewById(R.id.btn_cancel);

        //创建店铺
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_name.getText().toString().trim().equals("")) {
                    Toast.makeText(MyGarage.this, "店铺名不能为空", Toast.LENGTH_SHORT).show();
                } else if (edt_name.getText().toString().trim().length() > 10) {
                    Toast.makeText(MyGarage.this, "店铺名称过长", Toast.LENGTH_SHORT).show();
                } else {
                    //把店铺名添加到Bmob
                    Store store = new Store();
                    store.setStoreName(edt_name.getText().toString());
                    store.setMyUser(BmobUser.getCurrentUser(MyGarage.this, MyUser.class));
                    store.save(MyGarage.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(MyGarage.this, "保存成功", Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(MyGarage.this, MyShop.class);
                            startActivity(it);
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(MyGarage.this, "保存失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        //隐藏
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });


    }
    public void show(String msg){
        Toast.makeText(MyGarage.this,"提示信息："+msg,Toast.LENGTH_SHORT).show();
    }
}
