package com.zuchexing.carrental;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/4/27 0027.
 */
public class bar_brad_graidview extends GridView{

    public bar_brad_graidview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public bar_brad_graidview(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
