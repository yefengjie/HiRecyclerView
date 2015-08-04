package com.freedom.yefeng.yfrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yefeng on 7/24/15.
 * github:yefengfreedom
 * we should find views or add views listener in RecyclerViewAdapter.onCreateDataViewHolder method,
 * so we need to define our own ViewHolder,and find views,add listener inside it, instead of using SimpleViewHolder.
 */
@Deprecated
public class SimpleViewHolder extends RecyclerView.ViewHolder {
    public SimpleViewHolder(View itemView) {
        super(itemView);
    }
}
