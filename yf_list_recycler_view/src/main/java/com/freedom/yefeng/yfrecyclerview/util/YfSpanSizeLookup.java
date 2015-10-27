package com.freedom.yefeng.yfrecyclerview.util;

import android.support.v7.widget.GridLayoutManager;

import com.freedom.yefeng.yfrecyclerview.YfListAdapter;

/**
 * Created by KingWu on 23/10/15.
 */
public class YfSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    YfListAdapter adapter;
    int spanCount;

    public YfSpanSizeLookup(YfListAdapter adapter, int spanCount)
    {
        this.adapter = adapter;
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {

        int headerCount = adapter.getHeaderCount();
        if (position < headerCount) {
            return spanCount;
        } else if (position >= headerCount + adapter.getDataCount()) {
            return spanCount;
        }
        return 1;
    }
}
