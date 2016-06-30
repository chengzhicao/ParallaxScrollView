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
    private int startY, startX;

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
        int distanceY, distanceX;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getRawY();
                startX = (int) ev.getRawX();
                START_LOOP = false;
                break;
            case MotionEvent.ACTION_MOVE:
                distanceY = (int) ev.getRawY() - startY;
                distanceX = (int) ev.getRawX() - startX;
                if (Math.abs(distanceY) >= DisplayUtils.dip2px(context, 5)
                        ) {//判断Y轴，按住时,只有当Y轴触摸移动的距离大于5dp才可开启轮播
                    START_LOOP = true;
                }
                if (Math.abs(distanceX) <= DisplayUtils.dip2px(context, 5)) {//判断X轴，按住时,如果当X轴触摸移动的距离大于5dp则关闭轮播
                    START_LOOP = true;
                } else {
                    START_LOOP = false;
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

    /**
     * 设置当前current值
     *
     * @param item
     */
    @Override
    public synchronized void setCurrentItem(int item) {
        super.setCurrentItem(item);
        this.currentItem = item;
    }

}
