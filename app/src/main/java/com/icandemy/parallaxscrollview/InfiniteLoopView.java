package com.icandemy.parallaxscrollview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 实现无限轮播
 * Created by ICAN on 2016/6/30.
 */
public class InfiniteLoopView extends ViewPager {
    private int currentItem;
    private boolean START_LOOP = true;
    private Context context;
    private int startY;
    private final int SLIDING_DISTANCE = 5;

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
                requestDisallowInterceptTouchEvent(true);
                START_LOOP = false;
                startY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                distanceY = (int) ev.getRawY() - startY;
                int px_scroll = DisplayUtils.dip2px(context, SLIDING_DISTANCE);
                if (Math.abs(distanceY) >= px_scroll
                        ) {//判断Y轴，按住时,只有当Y轴触摸移动的距离大于5dp才可开启轮播
                    START_LOOP = true;
                    requestDisallowInterceptTouchEvent(false);
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