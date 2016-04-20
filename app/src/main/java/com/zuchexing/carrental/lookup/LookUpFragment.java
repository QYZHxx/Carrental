package com.zuchexing.carrental.lookup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    Button btn_map_search;
    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lookup_frag, null);
        btn_map_search=(Button)view.findViewById(R.id.btn_map_search);
        btn_map_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent it=new Intent(context, MapSearch.class);
                    startActivity(it);
                    System.out.println("跳转成功");
            }
        });
        return view;
    }
}
