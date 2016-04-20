package com.zuchexing.carrental.lookup;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zuchexing.carrental.R;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 情谊纵横 on 2016/4/20.
 */
public class AdvertFragment extends Fragment {//广告页面

    private int imageIds[];
    private String[] titles;
    private ArrayList<ImageView> images;
    private ArrayList<View> dots;
    private TextView title;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private int oldPosition = 0;//记录上一次点的位置
    private int currentItem; //当前页面
    ViewPagerTask viewPagerTask=null;
    private ScheduledExecutorService scheduledExecutorService;
    private Context context;
    View view;
    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.lookup_advert_frag,null);
        System.out.println("AdvertFragment--------");
        initView();
        return view;
    }

    public void initView(){
        //图片ID
        imageIds = new int[]{
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e
        };

        //图片标题
        titles = new String[]{
                "巩俐不低俗，我就不能低俗",
                "扑树又回来啦！再唱经典老歌引万人大合唱",
                "揭秘北京电影如何升级",
                "乐视网TV版大派送",
                "热血屌丝的反杀"
        };

        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i =0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(imageIds[i]);

            images.add(imageView);
        }

        //显示的点
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.advert_image_0));
        dots.add(view.findViewById(R.id.advert_image_1));
        dots.add(view.findViewById(R.id.advert_image_2));
        dots.add(view.findViewById(R.id.advert_image_3));
        dots.add(view.findViewById(R.id.advert_image_4));

        title = (TextView)view.findViewById(R.id.title);
        title.setText(titles[0]);

        mViewPager = (ViewPager)view.findViewById(R.id.lookup_advert_vp);

        adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);

                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return images.size();
        }

        //是否是同一张图片
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//            super.destroyItem(container, position, object);
//            view.removeViewAt(position);
            view.removeView(images.get(position));

        }

        @Override
        public Object instantiateItem(ViewGroup view, final int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
                images.get(position).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("点击："+position);
                        Toast.makeText(context,"点击："+position,Toast.LENGTH_SHORT).show();
                    }
                });
            return images.get(position);
        }
    }


    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

     //   new ViewPagerTask();
//
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        if (viewPagerTask==null) {
            //每隔2秒钟切换一张图片
            viewPagerTask=new ViewPagerTask();
            scheduledExecutorService.scheduleWithFixedDelay(viewPagerTask, 2, 2, TimeUnit.SECONDS);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //切换图片
    private class ViewPagerTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            currentItem = (currentItem +1) % imageIds.length;
            //更新界面
//            handler.sendEmptyMessage(0);
            try {
                Thread.sleep(0,2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.obtainMessage().sendToTarget();
        }

    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //设置当前页面
            mViewPager.setCurrentItem(currentItem);
        }

    };

}
