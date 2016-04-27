package com.zuchexing.carrental.lookup;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.amap.api.location.AMapLocation;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.Store;
import com.zuchexing.carrental.customlayout.TitleLayout;
import com.zuchexing.carrental.map.IMap;
import com.zuchexing.carrental.map.MapUtil;

import java.util.ArrayList;

/**
 * Created by 情谊纵横 on 2016/4/25.
 */
public class FindingCarStore extends AppCompatActivity implements IMap{

    TitleLayout title;
    ArrayList<Store> datas;
    StoreAdapter adapter;
    String city="";
    MapUtil mapUtil;
    PullToRefreshListView plistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookip_fcarstore_main);
        title=(TitleLayout)findViewById(R.id.car_store);
        title.setTitle("店铺查找");
        title.setIsHidderCollateImage(true);
        mapUtil=new MapUtil(this,this);
        mapUtil.startLocation();
    }

    private void initData(){
        datas=new ArrayList<>();
        datas.add(new Store());
        datas.add(new Store());
        datas.add(new Store());
        initView();
//        BmobQuery<Store> query=new BmobQuery<>();
//        query.addWhereEqualTo("storeCity", city);
//        query.setLimit(15);//返回15条信息,默认10条
//        query.findObjects(FindingCarStore.this, new FindListener<Store>() {
//            @Override
//            public void onSuccess(List<Store> list) {
//                datas = (ArrayList) list;
//                if (datas.size() == 0) {
//                    Toast.makeText(FindingCarStore.this, "暂未找到该城市店铺信息!", Toast.LENGTH_SHORT).show();
//                } else {
//                    initView();
//                }
//            }
//
//            @Override
//            public void onError(int i, String s) {
//                System.out.println("查询失败：" + i + s);
//            }
//        });
    }

    private void initView() {
        plistView=(PullToRefreshListView)findViewById(R.id.find_store);
        adapter=new StoreAdapter(this,datas);
        plistView.setAdapter(adapter);
        plistView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //刷新的处理
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(1000);
                            datas.clear();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        // datas.add("新数据:" + new Date().toLocaleString());
                        initData();
                        adapter.notifyDataSetChanged();
                        plistView.onRefreshComplete();
                    }
                }.execute();
            }
        });
    }

    @Override
    public void getAMapLocation(AMapLocation mapLocation) {//Imap接口的回调
        city=mapLocation.getCity();
        initData();
        mapUtil.stopLocation();
    }
}
