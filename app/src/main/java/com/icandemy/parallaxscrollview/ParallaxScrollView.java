package com.icandemy.parallaxscrollview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ScrollView;

/**
 * Created by ICAN on 2016/6/29.
 */
public class ParallaxScrollView extends ScrollView {
    private String tag = ParallaxScrollView.class.getSimpleName();
    private Toolbar toolbar;
    private ViewPager viewPager;

    public ParallaxScrollView(Context context) {
        this(context, null);
    }

    public ParallaxScrollView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        init();
    }

    public ParallaxScrollView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    private void init() {

    }

    public void setScrollViewControl(Toolbar toolbar, ViewPager viewPager) {
        this.toolbar = toolbar;
        this.viewPager = viewPager;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        int headHeight = viewPager.getMeasuredHeight() - toolbar.getMeasuredHeight();
        int alpha = (int) ((float) t / headHeight * 255);
        if (alpha >= 255) {
            alpha = 255;
        } else if (alpha < 0) {
            alpha = 0;
        }
        toolbar.getBackground().setAlpha(alpha);
        viewPager.setTop(t - t / 2);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i("iwoeiru","onTouchEvent");
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("iwoeiru","onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }
}
