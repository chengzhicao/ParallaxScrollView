package com.icandemy.parallaxscrollview;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ScrollView;

/**
 * Created by ICAN on 2016/6/29.
 */
public class ParallaxScrollView extends ScrollView {
    private String tag = ParallaxScrollView.class.getSimpleName();
    private Toolbar toolbar;
    private ImageView imageView;

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

    public void setScrollViewControl(Toolbar toolbar, ImageView imageView) {
        this.toolbar = toolbar;
        this.imageView = imageView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        int headHeight = imageView.getMeasuredHeight() - toolbar.getMeasuredHeight();
        float alpha = (float) t / headHeight;
        if (alpha >= 1.0f)
            alpha = 1.0f;
        Log.d("wtetwet", alpha + "");
        toolbar.setAlpha(alpha);
        imageView.setTop(t - t / 2);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
