package com.zuchexing.carrental.lookup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zuchexing.carrental.R;
import com.zuchexing.carrental.customlayout.TitleLayout;

/**
 * Created by 情谊纵横 on 2016/4/22.
 */
public class FindingCar extends AppCompatActivity {

    TitleLayout titleLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookup_findingcar_main);
        initview();
    }

    private void initview() {
        titleLayout=(TitleLayout)findViewById(R.id.zdy_finding_car);
        titleLayout.setTitle("精准找车");
       // titleLayout.setIsHidderCollateImage(true);
       // titleLayout.setIsHidderServeImage(true);
    }
}
