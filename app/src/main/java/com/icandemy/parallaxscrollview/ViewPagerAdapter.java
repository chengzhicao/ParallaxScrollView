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

    public ViewPagerAdapter(List<View> imageList) {
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageList.get(position));
        return imageList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageList.get(position));
        Log.i("gwgwe", "adapter:"+position + "");
    }
}
