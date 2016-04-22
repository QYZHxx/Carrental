package com.zuchexing.carrental.customlayout;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zuchexing.carrental.R;

/**
 * Created by 情谊纵横 on 2016/4/22.
 */
public class TitleLayout extends LinearLayout implements View.OnClickListener {

    ImageView img_return,img_skip_serve,img_collate;
    TextView  title;
    Context context;
    Activity activity;

    public TitleLayout(Context context) {
        super(context);
    }
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        activity=(Activity)context;
        LayoutInflater.from(context).inflate(R.layout.zdy_titlet, this);
        img_return=(ImageView)findViewById(R.id.btn_title_return);
        img_skip_serve=(ImageView)findViewById(R.id.btn_skip_serve);
        img_collate=(ImageView)findViewById(R.id.btn_collate);
        title=(TextView)findViewById(R.id.custom_title);
        img_return.setOnClickListener(this);
        img_skip_serve.setOnClickListener(this);
    }

    public void setIsHidderCollateImage(boolean hidden){
        if (hidden){
            img_collate.setVisibility(View.INVISIBLE);
        }
    }
    public void setIsHidderServeImage(boolean hidden){
        if (hidden){
            img_skip_serve.setVisibility(View.INVISIBLE);
        }
    }
    public void setTitle(String str){
        title.setText(str);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_title_return:
                activity.finish();
                show("返回");
                break;
            case R.id.btn_skip_serve:
                show("服务");
                break;
            case R.id.btn_collate:
                show("排序");
                showPopwindow();
                break;
        }
    }

    private void showPopwindow() {

    }
    public void show(String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
