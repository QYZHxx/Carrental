package com.zuchexing.carrental;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.zuchexing.carrental.bmob.Car;
import com.zuchexing.carrental.bmob.MyUser;
import com.zuchexing.carrental.customlayout.TitleLayout;
import com.zuchexing.carrental.imageupload.GetPathFromUri4kitkat;
import com.zuchexing.carrental.map.IMap;
import com.zuchexing.carrental.map.MapUtil;

import java.io.File;
import java.io.FileNotFoundException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

//
public class car_register extends AppCompatActivity implements View.OnClickListener,IMap{

    TitleLayout title;      //标题
    LinearLayout brandselect;//选择车牌
    TextView  car_address;      //车辆所在地址
    TextView car_type;  //车辆显示牌
    TextView broad_num;     //车牌号
    TextView profession;    //职业显示
    TextView car_km;    //行驶距离
    TextView car_num;   //载人数
    TextView car_age;   //车龄
    TextView car_rentprice;     //租用价格
    Intent it;
    Button submit;      //上传到数据库
    Car car;
    MyUser myUser;
    MapUtil map;
    AMapLocation location;
    ImageView car_pic;
    String path;
    BmobFile bmobFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_register);
        title=(TitleLayout)findViewById(R.id.title);
        initview();
        map=new MapUtil(this,this);
        map.startLocation();


    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }

    public void initview(){
        title.setIsHidderCollateImage(true);
        title.setIsHidderServeImage(true);
        title.setTitle("提交车辆资料");
        brandselect=(LinearLayout)findViewById(R.id.car_register_brandselect);
        submit=(Button)findViewById(R.id.car_register_submit);

        car_type=(TextView)findViewById(R.id.car_register_car_type);
        car_address=(TextView)findViewById(R.id.car_register_address);
        broad_num=(TextView)findViewById(R.id.car_register_boradnum);
        car_num=(TextView)findViewById(R.id.car_register_carnum);
        car_km=(TextView)findViewById(R.id.car_register_carKm);
        car_age=(TextView)findViewById(R.id.car_register_carage);
        car_rentprice=(TextView)findViewById(R.id.car_register_carRentPrice);
        brandselect.setOnClickListener(this);
        submit.setOnClickListener(this);

        car_pic=(ImageView)findViewById(R.id.register_car_pic);

    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.car_register_brandselect) {
            it = new Intent(this, car_brand_select.class);
            startActivityForResult(it, 1);
        } else if (v.getId()==R.id.car_register_submit){
            String carnumRegex="[A-Z]{1}[A-Z_0-9]{5}";
            String identity="[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)";
            if (broad_num.getText()!=null&&broad_num.getText().toString().matches(carnumRegex)){
                if (car_type.getText()!=null){
                    if (car_km.getText()!=null&&Integer.parseInt(car_km.getText()+"")>0&&Integer.parseInt(car_km.getText()+"")<100000){
                        if (car_num!=null){
                            if (car_rentprice!=null){
                                if (car_address!=null){
                                    if (car_pic!=null){
                                            myUser= BmobUser.getCurrentUser(this,MyUser.class);
                                            car=new Car();
                                            car.setMyUser(myUser);              //用户
                                            System.out.println(myUser.getName());
                                            car.setCarNum(broad_num.getText().toString());      //车牌号
                                            car.setCarName(car_type.getText().toString());      //车辆名
                                            car.setCarAddress(car_address.getText().toString());    //交车地址
                                            car.setCarKm(Integer.valueOf(car_km.getText().toString())); //车距
                                            car.setCarCity(location.getCity().toString());      //当前城市地址
                                            car.setCarLatitude(location.getLatitude() + "");       //纬度
                                            car.setCarLongitude(location.getLongitude() + "");    //纬度
                                            car.setCarState("0x11");
                                            car.setCarRentPrice(Integer.valueOf(car_rentprice.getText().toString()));
                                            car.setCarVehices(Integer.valueOf(car_num.getText().toString()));
                                            car.setCarAge(Integer.valueOf(car_age.getText().toString()));
                                            car.setCarImage(bmobFile);
                                            car.save(this, new SaveListener() {
                                                @Override
                                                public void onSuccess() {
                                                    Toast.makeText(car_register.this, "添加成功", Toast.LENGTH_SHORT).show();
                                                }
                                                @Override
                                                public void onFailure(int i, String s) {
                                                    Toast.makeText(car_register.this,"失败",Toast.LENGTH_SHORT).show();
                                                }
                                            });

//                                            跳转页面 提示发布成功发布页面
//                                            Intent it=new Intent(); //提示发布成功   并且跳转到车库

                                            }else{
                                        Toast.makeText(this,"请上传您车辆的图片",Toast.LENGTH_SHORT).show();

                                    }
                                        }else{
                                            Toast.makeText(this,"请开启网络获取您目前的交车地点",Toast.LENGTH_SHORT).show();

                                        }
                                    }else{
                                        Toast.makeText(this,"您的租用价格还没有填写",Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(this,"您所输入的年限不合法",Toast.LENGTH_SHORT).show();
                                    car_num.setText("");
                                }
                            }else {
                                Toast.makeText(this,"您输入的距离不合法",Toast.LENGTH_SHORT).show();
                                car_km.setText("");
                            }
                        }else{
                    Toast.makeText(this,"请选择车辆型号",Toast.LENGTH_SHORT).show();
                    }
                }else{
                broad_num.setText("");
                Toast.makeText(this,"请输出正确的车牌号",Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode==1&&resultCode==RESULT_OK){
            String str=data.getStringExtra("cars");
           car_type.setText(str);
       }else if (requestCode==2&&resultCode==RESULT_OK){
            String str=data.getStringExtra("xx");
           profession.setText(str);
       }else if (requestCode==3&&resultCode==RESULT_OK){
           Uri uri=data.getData();
           ContentResolver resolver=this.getContentResolver();
           path= GetPathFromUri4kitkat.getPath(this, uri);
           try {
               Bitmap bitmap= BitmapFactory.decodeStream(resolver.openInputStream(uri));
               car_pic.setImageBitmap(bitmap);
               bmobFile=new BmobFile(new File(path));
               System.out.println("上传图片前");
               bmobFile.uploadblock(this, new UploadFileListener() {
                   @Override
                   public void onSuccess() {

                       System.out.println("上传成功");
                   }

                   @Override
                   public void onProgress(Integer value) {
                       super.onProgress(value);
                       System.out.println("正在上传");
                   }

                   @Override
                   public void onFailure(int i, String s) {
                        System.out.println("上传失败");
                   }
               });
            System.out.println("上传图片后");
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }
       }

        super.onActivityResult(requestCode,resultCode,data);

    }
    public void tupian(View view){
        Intent it=new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(it, 3);

    }

    @Override
    public void getAMapLocation(AMapLocation mapLocation) {
        car_address.setText(mapLocation.getCity() + mapLocation.getDistrict() + mapLocation.getStreet() + mapLocation.getStreetNum());
        location=mapLocation;
        map.stopLocation();
    }
}
