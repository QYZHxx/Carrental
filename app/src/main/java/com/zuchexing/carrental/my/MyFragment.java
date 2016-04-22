package com.zuchexing.carrental.my;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.zuchexing.carrental.R;

/**
 * Created by 情谊纵横 on 2016/4/19.
 */
public class MyFragment extends Fragment {

    private ImageView img_register;
    private ImageView img_registered;
    Context context;
    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_frag, null);

        img_register=(ImageView)view.findViewById(R.id.img_register);
        img_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MyRegister.class);
                startActivity(intent);
            }
        });

        img_registered=(ImageView)view.findViewById(R.id.img_registered);
        img_registered.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MyRegistered.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
