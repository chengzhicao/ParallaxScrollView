package com.icandemy.parallaxscrollview;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ParallaxScrollView parallaxScrollView;
    private Toolbar toolbar;
    private InfiniteLoopView infiniteLoopView;
    private List<View> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.bar_toolbar);
        toolbar.getBackground().setAlpha(0);
        infiniteLoopView = (InfiniteLoopView) findViewById(R.id.viewpager_ads);
        parallaxScrollView = (ParallaxScrollView) findViewById(R.id.icandemy_scroller);

        parallaxScrollView.setScrollViewControl(toolbar, infiniteLoopView);//设置toolbar渐变

        imageList = new ArrayList<View>();
        imageList.add(View.inflate(this, R.layout.pager_ads_first, null));
        imageList.add(View.inflate(this, R.layout.pager_ads_second, null));
        imageList.add(View.inflate(this, R.layout.pager_ads_third, null));
        imageList.add(View.inflate(this, R.layout.pager_ads_fourth, null));

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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
