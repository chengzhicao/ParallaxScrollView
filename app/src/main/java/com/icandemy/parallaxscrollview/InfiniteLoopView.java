package com.icandemy.parallaxscrollview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 实现无限轮播
 * Created by ICAN on 2016/6/30.
 */
public class InfiniteLoopView extends ViewPager {
    private int currentItem;
    private boolean START_LOOP = true;
    private Context context;
    private int startY;

    public InfiniteLoopView(Context context) {
        this(context, null);
    }

    public InfiniteLoopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
    }

    /**
     * 开始循环播放轮播图
     */
    public void startLoopPlay() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (START_LOOP) {
                        currentItem++;
                        if (currentItem == 4) {
                            currentItem = 0;
                        }
                        handler.sendEmptyMessage(100);
                    }
                }
            }
        }.start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    setCurrentItem(currentItem);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int distanceY;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getRawY();
                START_LOOP = false;
                break;
            case MotionEvent.ACTION_MOVE:
                distanceY = (int) ev.getRawY() - startY;
                if (Math.abs(distanceY) > DisplayUtils.dip2px(context, 5)) {//按住时,只有当触摸移动的距离大于5dp才可开启轮播
                    START_LOOP = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                START_LOOP = true;
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

}
