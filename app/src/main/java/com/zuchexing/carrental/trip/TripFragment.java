package com.zuchexing.carrental.trip;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.zuchexing.carrental.R;

/**
 * Created by 情谊纵横 on 2016/4/19.
 */
public class TripFragment extends Fragment implements View.OnClickListener {

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    Context context ;
    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trip_frag, null);
        layout1=(LinearLayout)view.findViewById(R.id.trip_zuche_lin);
        layout2=(LinearLayout)view.findViewById(R.id.trip_zuche_pay);
        layout3=(LinearLayout)view.findViewById(R.id.trip_zuche_safe);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {

        Toast.makeText(context,"00000000",Toast.LENGTH_SHORT).show();

    }
}
