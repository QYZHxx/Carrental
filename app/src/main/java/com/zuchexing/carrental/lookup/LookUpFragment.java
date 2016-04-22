package com.zuchexing.carrental.lookup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zuchexing.carrental.R;
import com.zuchexing.carrental.map.MapSearch;

/**
 * Created by 情谊纵横 on 2016/4/19.
 */
public class LookUpFragment extends Fragment {


    Context context;
    Button btn_map_search, cx;
    AdvertFragment advertFragment;
    FragmentManager fManager;
    View view;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lookup_frag, null);
        initView();
        return view;
    }

    public void initView() {
        fManager = getFragmentManager();
        FragmentTransaction transaction = fManager.beginTransaction();
        advertFragment = new AdvertFragment();
        transaction.add(R.id.advert_frag, advertFragment);
        transaction.show(advertFragment);
        transaction.commit();
        btn_map_search = (Button) view.findViewById(R.id.btn_map_search);
        cx = (Button) view.findViewById(R.id.lookup_btn_cx);
        cx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, FindingCar.class);
                startActivity(it);

            }
        });
        btn_map_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, MapSearch.class);
                startActivity(it);
                System.out.println("跳转成功");
            }
        });
    }
}
