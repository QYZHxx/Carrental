package com.zuchexing.carrental.lookup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.Store;

import java.util.List;

/**
 * Created by 情谊纵横 on 2016/4/26.
 */
public class StoreAdapter extends BaseAdapter {

    Context context;
    List<Store> lists;

    public StoreAdapter(Context context, List<Store> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView name,introduce,collect,callNum;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v=null;
        if (convertView==null){
            v=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.store_adapter,null);
            v.name=(TextView)convertView.findViewById(R.id.store_name);
            v.introduce=(TextView)convertView.findViewById(R.id.store_introduce);
            v.collect=(TextView)convertView.findViewById(R.id.store_collect);
            v.callNum=(TextView)convertView.findViewById(R.id.store_callNum);

            convertView.setTag(v);
        }else {
            v=(ViewHolder)convertView.getTag();
        }

        v.name.setText("天天租车店");
        v.callNum.setText("拨打:100次");
        v.introduce.setText("本店什么车都有，只要你来租，我们就敢便宜租给你！！");
        v.collect.setText("收藏200");
        return convertView;
    }
}
