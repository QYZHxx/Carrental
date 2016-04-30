package com.zuchexing.carrental;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/28 0028.
 */
public class MyGridview_Adaputer extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    String [] strs;
    Activity activity;
    public MyGridview_Adaputer(Context context,Activity activity,String[] strs){
        this.context=context;
        this.strs=strs;
        this.activity=activity;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return strs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.bar_board_lv_adaputer,parent,false);
            holder=new ViewHolder();
            holder.textView=(TextView)convertView.findViewById(R.id.bar_board_lv);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        if (strs!=null&&holder.textView!=null){
            holder.textView.setText(strs[position]);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent();
                    it.putExtra("cars",holder.textView.getText());
                    activity.setResult(Activity.RESULT_OK, it);
                    Toast.makeText(context,""+holder.textView.getText(),Toast.LENGTH_SHORT).show();
                    activity.finish();
                }
            });
        }
        return convertView;
    }
    public class ViewHolder{
        TextView textView;
    }
}
