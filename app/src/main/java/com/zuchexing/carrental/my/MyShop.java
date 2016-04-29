package com.zuchexing.carrental.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.bmob.Store;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

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
    private AlertDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_shop);
        table();
        initialize();
//        back();

        List<Map<String,Object>> listItems=
                new ArrayList<Map<String, Object>>();
        for(int i =0;i<imgIds.length;i++){
            Map<String,Object> listItem = new HashMap<String, Object>();
            listItem.put("image",imgIds[i]);
            listItems.add(listItem);
        }
//        cs = (ImageView)findViewById(R.id.img_cs);
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
        LinearLayout isloginform = (LinearLayout) getLayoutInflater().inflate(R.layout.my_nvt_car, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(isloginform);
        dialog = builder.create();
        dialog.show();

        final EditText car_register =(EditText)isloginform.findViewById(R.id.car_register);//车牌号
        final TextView car_register_car_type=(TextView)isloginform.findViewById(R.id.car_register_car_type);//车名
        final EditText edt_car_carKm=(EditText)isloginform.findViewById(R.id.edt_car_carKm);//行驶距离
        final EditText edt_car      =(EditText)isloginform.findViewById(R.id.edt_car);//车载人数
        final EditText edt_car_carm =(EditText)isloginform.findViewById(R.id.edt_car_carm);//出租日期
        Button   btn_car_submit=(Button)isloginform.findViewById(R.id.btn_car_submit);//提交
        btn_car_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Car car = new Car();
                car.setCarNum(car_register.getText().toString());
                car.setCarName(car_register_car_type.getText().toString());
                car.setCarKm(Integer.valueOf(edt_car_carKm.getText().toString()));
                car.setCarVehices(Integer.valueOf(edt_car.getText().toString()));
                car.setCarDate(edt_car_carm.getText().toString());
                car.setMyUser(BmobUser.getCurrentUser(MyShop.this, MyUser.class));
                car.save(MyShop.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        show("添加车辆成功！");
                        dialog.hide();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        show("添加失败！");
                    }
                });


            }
        });

        final LinearLayout line_age=(LinearLayout)isloginform.findViewById(R.id.line_age);
        line_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                new DatePickerDialog(MyShop.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        EditText show = (EditText) findViewById(R.id.car_register_carage);
                        show.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                    }

                }
                        , c.get(Calendar.YEAR)
                        , c.get(Calendar.MONTH)
                        , c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void show(String msg){
        Toast.makeText(MyShop.this,"提示信息："+msg,Toast.LENGTH_SHORT).show();
    }
}
