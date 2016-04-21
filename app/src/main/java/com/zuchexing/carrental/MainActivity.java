package com.zuchexing.carrental;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zuchexing.carrental.carowner.CarOwnerFragment;
import com.zuchexing.carrental.lookup.LookUpFragment;
import com.zuchexing.carrental.my.MyFragment;
import com.zuchexing.carrental.trip.TripFragment;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {//主页面

    private TextView tv_carowner, tv_lookup, tv_my, tv_trip;
    private Fragment fragment1, fragment2, fragment3, fragment4;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        initView();
    }

    public void initView() {
        Bmob.initialize(this, "ccb2e44ea27ebc4c5e9902d40231de9e");
        tv_carowner = (TextView) findViewById(R.id.main_tv_carowner);
        tv_lookup = (TextView) findViewById(R.id.main_tv_lookup);
        tv_my = (TextView) findViewById(R.id.main_tv_my);
        tv_trip = (TextView) findViewById(R.id.main_tv_trip);

        tv_carowner.setOnClickListener(this);
        tv_lookup.setOnClickListener(this);
        tv_my.setOnClickListener(this);
        tv_trip.setOnClickListener(this);

        tv_lookup.callOnClick();//模拟一次点击相当于点击了第一项
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = manager.beginTransaction();
        hideAllFragmet(transaction);
        resetTextView();
        switch (v.getId()) {
            case R.id.main_tv_lookup:
                tv_lookup.setSelected(true);
                if (fragment1 == null) {
                    fragment1 = new LookUpFragment();
                    transaction.add(R.id.main_content, fragment1);
                } else
                    transaction.show(fragment1);
                break;
            case R.id.main_tv_trip:
                tv_trip.setSelected(true);
                if (fragment2 == null) {
                    fragment2 = new TripFragment();
                    transaction.add(R.id.main_content, fragment2);
                } else
                    transaction.show(fragment2);
                break;
            case R.id.main_tv_carowner:
                tv_carowner.setSelected(true);
                if (fragment3 == null) {
                    fragment3 = new CarOwnerFragment();
                    transaction.add(R.id.main_content, fragment3);
                } else
                    transaction.show(fragment3);
                break;
            case R.id.main_tv_my:
                tv_my.setSelected(true);
                if (fragment4 == null) {
                    fragment4 = new MyFragment();
                    transaction.add(R.id.main_content, fragment4);
                } else
                    transaction.show(fragment4);
                break;
        }
        transaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragmet(FragmentTransaction transaction) {
        if (fragment1 != null) transaction.hide(fragment1);
        if (fragment2 != null) transaction.hide(fragment2);
        if (fragment3 != null) transaction.hide(fragment3);
        if (fragment4 != null) transaction.hide(fragment4);
    }

    //重置多有选中的状态
    private void resetTextView() {
        tv_carowner.setSelected(false);
        tv_lookup.setSelected(false);
        tv_my.setSelected(false);
        tv_trip.setSelected(false);
    }
}
