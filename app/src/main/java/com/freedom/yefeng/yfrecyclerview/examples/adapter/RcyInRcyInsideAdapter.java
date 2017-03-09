package com.freedom.yefeng.yfrecyclerview.examples.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freedom.yefeng.yfrecyclerview.HiInsideListAdapter;
import com.freedom.yefeng.yfrecyclerview.HiViewHolder;
import com.freedom.yefeng.yfrecyclerview.examples.R;

import java.util.ArrayList;

/**
 * Created by yefeng on 8/5/15.
 * github:yefengfreedom
 */
public class RcyInRcyInsideAdapter extends HiInsideListAdapter<String> {

    public RcyInRcyInsideAdapter(ArrayList<String> data) {
        super(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateEmptyViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_empty_material, parent, false);
        return new HiViewHolder(view);
    }

    @Override
    public void onBindDataViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).mText.setText("inside " + mData.get(i));
        viewHolder.itemView.setTag(mData.get(i));
    }

    private static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.txt_adapter_item);
        }
    }
}
