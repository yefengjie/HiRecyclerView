package com.freedom.yefeng.yfrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by yefeng on 09/03/2017.
 */

public abstract class HiInsideListAdapter<T> extends HiListAdapter<T> {
    public HiInsideListAdapter(ArrayList<T> data) {
        super(data);
    }

    public HiInsideListAdapter(ArrayList<T> data, int mode) {
        super(data, mode);
    }

    public HiInsideListAdapter(ArrayList<T> data, int mode, int toolBarHeight) {
        super(data, mode, toolBarHeight);
    }

    public HiInsideListAdapter(ArrayList<T> data, ArrayList<Object> headers, ArrayList<Object> footers, int mode, int toolBarHeight) {
        super(data, headers, footers, mode, toolBarHeight);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
        if (viewType == HiMode.MODE_DATA) {
            viewHolder.itemView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            viewHolder.itemView.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            viewHolder.itemView.getMeasuredHeight()));
        }
        return viewHolder;
    }
}
