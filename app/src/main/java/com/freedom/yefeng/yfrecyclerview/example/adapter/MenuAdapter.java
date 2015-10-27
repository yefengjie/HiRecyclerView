package com.freedom.yefeng.yfrecyclerview.example.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freedom.yefeng.yfrecyclerview.YfListAdapter;
import com.freedom.yefeng.yfrecyclerview.YfSimpleViewHolder;
import com.freedom.yefeng.yfrecyclerview.example.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yefeng on 8/6/15.
 * github:yefengfreedom
 */
public class MenuAdapter extends YfListAdapter {

    List<String> mData;

    public MenuAdapter(ArrayList<String> data) {
        super(data);
        this.mData = data;
    }

    @Override
    public int getDataCount() {
        return null != this.mData ? this.mData.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateEmptyViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_empty_material, parent, false);
        return new YfSimpleViewHolder(view);
    }

    @Override
    public void onBindDataViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).mText.setText(mData.get(i));
        viewHolder.itemView.setTag(mData.get(i));
    }

    private static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView mText;

        public ViewHolder(final View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.btn);
        }
    }
}
