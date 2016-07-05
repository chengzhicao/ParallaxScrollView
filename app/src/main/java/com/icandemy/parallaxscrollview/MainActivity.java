package com.icandemy.parallaxscrollview;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ParallaxScrollView parallaxScrollView;
    private Toolbar toolbar;
    private InfiniteLoopView infiniteLoopView;
    private List<View> imageList;
    private List<ImageView> image_indication_list;
    private LinearLayout linearLayout;
    /**
     * 指示器边距
     */
    private final int INDICATION_MARGIN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.bar_toolbar);
        infiniteLoopView = (InfiniteLoopView) findViewById(R.id.viewpager_ads);
        parallaxScrollView = (ParallaxScrollView) findViewById(R.id.icandemy_scroller);
        linearLayout = (LinearLayout) findViewById(R.id.linear_indication);

        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        toolbar.getBackground().setAlpha(0);
        parallaxScrollView.setScrollViewControl(toolbar, infiniteLoopView);//设置toolbar渐变

        imageList = new ArrayList<View>();
        imageList.add(View.inflate(this, R.layout.pager_ads_first, null));
        imageList.add(View.inflate(this, R.layout.pager_ads_second, null));
        imageList.add(View.inflate(this, R.layout.pager_ads_third, null));
        imageList.add(View.inflate(this, R.layout.pager_ads_fourth, null));

        image_indication_list = new ArrayList<ImageView>();
        for (int i = 0; i < imageList.size(); i++) {//初始化指示器
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.loop_indicator);
            imageView.setPadding(INDICATION_MARGIN, INDICATION_MARGIN, INDICATION_MARGIN, INDICATION_MARGIN);
            if (i == 0) {
                imageView.setAlpha(1.0f);
            } else {
                imageView.setAlpha(0.5f);
            }
            image_indication_list.add(imageView);
            linearLayout.addView(imageView);
        }

        infiniteLoopView.setAdapter(new ViewPagerAdapter(imageList));
        infiniteLoopView.setCurrentItem(ViewPagerAdapter.CURRENT_VALUE);//设置当前轮播图
        infiniteLoopView.startLoopPlay();
        infiniteLoopView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                infiniteLoopView.setCurrentItem(position);//每当手动滑动轮播图后，更新当前current值
                setIndication(position);
                Log.d("MainActivity", "position:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 指示器设置
     *
     * @param position
     */
    private void setIndication(int position) {
        switch (position % 4) {
            case 0:
                image_indication_list.get(0).setAlpha(1.0f);
                image_indication_list.get(1).setAlpha(0.5f);
                image_indication_list.get(2).setAlpha(0.5f);
                image_indication_list.get(3).setAlpha(0.5f);
                break;
            case 1:
                image_indication_list.get(0).setAlpha(0.5f);
                image_indication_list.get(1).setAlpha(1.0f);
                image_indication_list.get(2).setAlpha(0.5f);
                image_indication_list.get(3).setAlpha(0.5f);
                break;
            case 2:
                image_indication_list.get(0).setAlpha(0.5f);
                image_indication_list.get(1).setAlpha(0.5f);
                image_indication_list.get(2).setAlpha(1.0f);
                image_indication_list.get(3).setAlpha(0.5f);
                break;
            case 3:
                image_indication_list.get(0).setAlpha(0.5f);
                image_indication_list.get(1).setAlpha(0.5f);
                image_indication_list.get(2).setAlpha(0.5f);
                image_indication_list.get(3).setAlpha(1.0f);
                break;
            default:
                break;
        }
    }
}
