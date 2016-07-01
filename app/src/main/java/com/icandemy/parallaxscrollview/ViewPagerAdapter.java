package com.icandemy.parallaxscrollview;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ICAN on 2016/6/30.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> imageList;
    private final int count = 10000;
    public final static int CURRENT_VALUE = 5000;

    public ViewPagerAdapter(List<View> imageList) {
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageList.get(position % imageList.size()));
        return imageList.get(position % imageList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageList.get(position % imageList.size()));
    }
}
