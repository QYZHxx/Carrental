package com.zuchexing.carrental;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zuchexing.carrental.customlayout.TitleLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class car_brand_select extends AppCompatActivity {

    TitleLayout title;
    String[] letter={
            "A","B","C",
            "D","F",
            "G","H",
            "J","K","L",
            "M","N","O",
            "Q","R",
            "S","T","W","X",
            "Y","Z"};
    String[] a={"奥迪"};
    String[] b={"本田","奔腾","别克","保时捷","标致","比亚迪","宝马","宝骏","宾利","北汽绅宝"};
    String[] c={"长城","昌河","长安","长安商用","长丰扬子"};
    String[] d={"大众","东风","道奇","东风小康","东风风度"};
    String[] f={"丰田","福特","福田","菲亚特","富迪","菲亚特"};
    String[] g={"观致","广汽吉奥","广汽传祺"};
    String[] h={"悍马","红旗","海马","哈飞","华泰","哈弗","华颂","华普","黄海",""};
    String[] j={"Jeep","吉利","江淮","捷豹","江南","江铃"};
    String[] k={"凯迪拉克","克莱斯特","开瑞","凯yi"};
    String[] l={"路虎","莲花","雷克萨斯","陆风","林肯","劳斯莱斯","铃木","雷诺"};
    String[] m={"马自达","玛莎拉蒂","MINI","MG"};
    String[] n={"纳智捷","南汽"};
    String[] o={"讴歌","欧宝","欧朗"};
    String[] q={"奇瑞","起亚","启辰"};
    String[] r={"日产","荣威","瑞麟"};
    String[] s={"三菱","斯柯达","斯巴鲁","双龙","双环","Smart","萨博","上汽大通"};
    String[] t={"通家福","天汽美亚","特斯拉"};
    String[] w={"沃尔沃","五菱","威麟","五十铃"};
    String[] x={"雪佛兰","雪铁龙","现代","新凯","西雅特"};
    String[] y={"依维柯","一汽","永源","野马"};
    String[] z={"众泰","中华","中兴","中顺","知豆"};
    String[][] letters={a,b,c,d,f,g,h,j,k,l,m,n,o,q,r,s,t,w,x,y,z};
    ArrayList<HashMap<String,Object>> data;
    ListView  car_tab_lv;
    car_broad_adaputer adaputer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_brand_select);
        title=(TitleLayout)findViewById(R.id.title);
        initview();
        data=new ArrayList<>();
        init();
        car_tab_lv=(ListView)findViewById(R.id.car_tab_lv);
        adaputer=new car_broad_adaputer(this,data);
        car_tab_lv.setAdapter(adaputer);

        Bundle bundle=new Bundle();
        bundle.get("car");


    }
    public void initview(){
        title.setTitle("选择车辆的品牌");
        title.setIsHidderCollateImage(true);
        title.setIsHidderServeImage(true);

    }
    public void init(){
        for (int i=0;i<letters.length;i++){
            HashMap<String,Object> map=new HashMap<>();
            map.put("letter", letter[i]);
            map.put("trademark", letters[i]);
            data.add(map);
        }
    }
}
