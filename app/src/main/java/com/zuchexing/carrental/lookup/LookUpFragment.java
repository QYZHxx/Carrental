package com.zuchexing.carrental.lookup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuchexing.carrental.R;

/**
 * Created by 情谊纵横 on 2016/4/19.
 */
public class LookUpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lookup_frag, null);
        return view;
    }
}
