package com.zuchexing.carrental.customlayout;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zuchexing.carrental.R;
import com.zuchexing.carrental.application.DataApplication;
import com.zuchexing.carrental.bmob.Car;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 情谊纵横 on 2016/4/22.
 */
public class CarListview extends LinearLayout  {

    ArrayList<Car> datas;
    CarAdapter adapter;
    String city="";
    Context context;
    int count=5;
    int x=0;
//    MapUtil mapUtil;
    PullToRefreshListView plistView;

    public CarListview(Context context) {
        super(context);
    }

    public CarListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.zdy_car_listview, this);

        DataApplication app=(DataApplication)context.getApplicationContext();
        city=app.getCity();
        datas=new ArrayList<>();
        initDataX();
//        mapUtil=new MapUtil(context,this);
//        mapUtil.startLocation();
    }

    private void initDataX(){//下拉刷新
        BmobQuery<Car> query=new BmobQuery<>();
        query.addWhereEqualTo("carCity",city);
        query.setLimit(count);//返回15条信息,默认10条
        query.findObjects(context, new FindListener<Car>() {
            @Override
            public void onSuccess(List<Car> list) {
               datas=(ArrayList)list;
                if (datas.size()==0){
                    Toast.makeText(context,"暂未找到该城市数据!",Toast.LENGTH_SHORT).show();
                }else {
                    initView();
                }
            }

            @Override
            public void onError(int i, String s) {
                System.out.println("查询失败："+i+s);
            }
        });
    }

    private void initDataS(){//上拉加载
        BmobQuery<Car> query=new BmobQuery<>();
        query.addWhereEqualTo("carCity",city);
        query.setSkip(x*count);
        query.setLimit(count);//返回15条信息,默认10条
        query.findObjects(context, new FindListener<Car>() {
            @Override
            public void onSuccess(List<Car> list) {

                if (list.size()==0){
                    Toast.makeText(context,"数据全部加载完成!",Toast.LENGTH_SHORT).show();
                }else {
                    for (Car  car:list){
                        datas.add(car);
                    }
                    System.out.println("正在加载!");
                    initView();
                }
            }

            @Override
            public void onError(int i, String s) {
                System.out.println("查询失败："+i+s);
            }
        });
    }
    private void initView() {
        plistView=(PullToRefreshListView)findViewById(R.id.car_pull);
        adapter=new CarAdapter(context,datas);
        plistView.setAdapter(adapter);
        plistView.setMode(PullToRefreshBase.Mode.BOTH);
        plistView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                if (refreshView.isHeaderShown()) {
                    //刷新的处理
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {

                            try {
                                Thread.sleep(1000);
                                datas.clear();
                                x=0;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            // datas.add("新数据:" + new Date().toLocaleString());
                            initDataX();
                            adapter.notifyDataSetChanged();
                            plistView.onRefreshComplete();
                        }
                    }.execute();
                    System.out.println("下拉刷新");
                }else {
                    //刷新的处理
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {

                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            // datas.add("新数据:" + new Date().toLocaleString());
                            x++;
                            initDataS();
                            adapter.notifyDataSetChanged();
                            plistView.onRefreshComplete();
                        }
                    }.execute();

                    System.out.println("上拉加载");
                }
            }
        });
    }

//    @Override
//    public void getAMapLocation(AMapLocation mapLocation) {
//        city=mapLocation.getCity();
//        initData();
//        mapUtil.stopLocation();
//    }
}
