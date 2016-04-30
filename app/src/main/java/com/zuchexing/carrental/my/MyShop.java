package com.zuchexing.carrental.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.bmob.Store;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/4/26 0026.
 */

///////我的店铺
public class MyShop extends Activity {

    TextView txt_table;
    GridView grid;
    ImageView img_back;

    int[] imgIds=new int[]{
            R.drawable.tp,R.drawable.tp,R.drawable.tp,R.drawable.tp,
            R.drawable.tp,R.drawable.tp,R.drawable.tp,R.drawable.tp
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_shop);
        table();
        initialize();

        List<Map<String,Object>> listItems=
                new ArrayList<Map<String, Object>>();
        for(int i =0;i<imgIds.length;i++){
            Map<String,Object> listItem = new HashMap<String, Object>();
            listItem.put("image",imgIds[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.my_adt_item,
                new String[]{"image"},new int[]{R.id.img_css});

        grid.setAdapter(simpleAdapter);
        grid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                show("001");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                show("002");
            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                show("" + position);
            }
        });

    }

    public void initialize(){
        img_back=(ImageView)findViewById(R.id.img_back);
        grid=(GridView)findViewById(R.id.gridView);
        txt_table=(TextView)findViewById(R.id.txt_table);
    }


    public void table(){
        MyUser user = BmobUser.getCurrentUser(this,MyUser.class);
        BmobQuery<Store> query = new BmobQuery<Store>();
        query.addWhereEqualTo("myUser", user);
        query.findObjects(this, new FindListener<Store>() {
            @Override
            public void onSuccess(List<Store> list) {
                txt_table.setText(list.get(0).getStoreName());
            }
            @Override
            public void onError(int i, String s) {
            }
        });
    }

    public void add(View view){
        Intent it = new Intent(MyShop.this,MyCar.class);
        startActivity(it);
    }

    public void show(String msg){
        Toast.makeText(MyShop.this,"提示信息："+msg,Toast.LENGTH_SHORT).show();
    }
}
