package com.zuchexing.carrental.my;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.zuchexing.carrental.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class MyPattestation extends Activity {
    EditText edt_date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_pattestation);
        edt_date = (EditText)findViewById(R.id.edt_date);
        edt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                new DatePickerDialog(MyPattestation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        EditText show = (EditText) findViewById(R.id.edt_date);
                        show.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                    }

                }
                        , c.get(Calendar.YEAR)
                        , c.get(Calendar.MONTH)
                        , c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


}
