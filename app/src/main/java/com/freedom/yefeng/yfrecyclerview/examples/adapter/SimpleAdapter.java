package com.freedom.yefeng.yfrecyclerview.examples.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freedom.yefeng.yfrecyclerview.HiAdapter;
import com.freedom.yefeng.yfrecyclerview.HiViewHolder;
import com.freedom.yefeng.yfrecyclerview.examples.R;

import java.util.ArrayList;

/**
 * Created by yefeng on 8/5/15.
 * github:yefengfreedom
 */
public class SimpleAdapter extends HiAdapter<String> {

    public SimpleAdapter(ArrayList<String> data) {
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
        ((ViewHolder) viewHolder).mText.setText(mData.get(i));
        viewHolder.itemView.setTag(mData.get(i));
    }


    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header1, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        String header = (String) mHeaders.get(position);
        ((HeaderViewHolder) holder).mText.setText(header);
        holder.itemView.setTag(header);
    }

    @Override
    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_footer1, parent, false);
        return new FooterViewHolder(view);
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {
        String footer = (String) mFooters.get(position);
        ((FooterViewHolder) holder).mText.setText(footer);
        holder.itemView.setTag(footer);
    }


    @Override
    public RecyclerView.ViewHolder onCreateErrorViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_net_error_material, parent, false);
        return new HiViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateLoadingViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_loading_material, parent, false);
        return new HiViewHolder(view);
    }

    private static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.txt_adapter_item);
        }
    }

    private static final class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView mText;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.tv_header);
        }
    }

    private static final class FooterViewHolder extends RecyclerView.ViewHolder {

        TextView mText;

        public FooterViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.tv_footer);
        }
    }
}
