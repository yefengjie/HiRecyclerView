package com.freedom.yefeng.yfrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by yefeng on 7/24/15.
 * github:yefengfreedom
 * we should find views or add views listener in YfListAdapter.onCreateDataViewHolder method,
 * so we need to define our own ViewHolder,and find views,add listener inside it, instead of using YfSimpleViewHolder.
 */
public class YfSimpleViewHolder extends RecyclerView.ViewHolder {
    public YfSimpleViewHolder(View itemView) {
        super(itemView);
        Log.e("YfSimpleViewHolder", "we should find views or add views listener in YfListAdapter.onCreateDataViewHolder method," +
                "so we need to define our own ViewHolder,and find views,add listener inside it, instead of using YfSimpleViewHolder.");
    }
}
