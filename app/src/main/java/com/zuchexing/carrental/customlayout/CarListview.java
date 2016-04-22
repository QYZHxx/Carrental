package com.zuchexing.carrental.customlayout;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zuchexing.carrental.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 情谊纵横 on 2016/4/22.
 */
public class CarListview extends LinearLayout {

    ArrayList<String> datas=new ArrayList<>();
    ArrayAdapter<String> adapter;

    Context context;
    PullToRefreshListView plistView;
    public CarListview(Context context) {
        super(context);
    }

    public CarListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.zdy_car_listview,this);
        plistView=(PullToRefreshListView)findViewById(R.id.car_pull);
        datas.add("测试数据1");
        adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,datas);
        plistView.setAdapter(adapter);
        plistView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //刷新的处理
                new AsyncTask<Void,Void,Void>(){

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return  null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        datas.add("新数据:"+new Date().toLocaleString());
                        adapter.notifyDataSetChanged();
                        plistView.onRefreshComplete();
                    }
                }.execute();
            }
        });
    }
}
