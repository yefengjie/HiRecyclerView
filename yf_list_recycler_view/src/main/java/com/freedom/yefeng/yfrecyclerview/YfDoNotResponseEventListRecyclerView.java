package com.freedom.yefeng.yfrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yefeng on 08/03/2017.
 */

public class YfDoNotResponseEventListRecyclerView extends YfListRecyclerView {
    public YfDoNotResponseEventListRecyclerView(Context context) {
        super(context);
    }

    public YfDoNotResponseEventListRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YfDoNotResponseEventListRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
}
