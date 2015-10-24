package com.freedom.yefeng.yfrecyclerview.example.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freedom.yefeng.yfrecyclerview.YfMultiTypeListAdapter;
import com.freedom.yefeng.yfrecyclerview.YfSimpleViewHolder;
import com.freedom.yefeng.yfrecyclerview.example.R;
import com.freedom.yefeng.yfrecyclerview.example.SampleGridActivity;

import java.util.ArrayList;

/**
 * Created by KingWu on 23/10/15
 * github: github.com/KingWu
 */
public class SimpleMultiTypeAdapter extends YfMultiTypeListAdapter<SampleGridActivity.DemoData> {

    public final static int BASE_TYPE = 10;
    public final static int NUMBER_OF_TYPE = 4;


    public SimpleMultiTypeAdapter(ArrayList<SampleGridActivity.DemoData> data) {
        super(data);
    }

    @Override
    public void onBindCustomDataViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((ViewHolder) viewHolder).mText.setText(mData.get(position).getData());
        viewHolder.itemView.setTag(mData.get(position).getData());
    }

    @Override
    public RecyclerView.ViewHolder onCreateCustomDataViewHolder(ViewGroup parent, int viewType) {

        int layoutRes = R.layout.grid_type_4;
        if (viewType == BASE_TYPE) {
            layoutRes = R.layout.grid_type_1;
        } else if (viewType == BASE_TYPE + 1) {
            layoutRes = R.layout.grid_type_2;
        } else if (viewType == BASE_TYPE + 2) {
            layoutRes = R.layout.grid_type_3;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateEmptyViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_empty_material, parent, false);
        return new YfSimpleViewHolder(view);
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
        return new YfSimpleViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateLoadingViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_loading_material, parent, false);
        return new YfSimpleViewHolder(view);
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
