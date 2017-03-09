package com.freedom.yefeng.yfrecyclerview.example.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freedom.yefeng.yfrecyclerview.HiViewHolder;
import com.freedom.yefeng.yfrecyclerview.UnfoldLinearLayoutManager;
import com.freedom.yefeng.yfrecyclerview.HiListAdapter;
import com.freedom.yefeng.yfrecyclerview.HiRecyclerView;
import com.freedom.yefeng.yfrecyclerview.example.R;

import java.util.ArrayList;

/**
 * Created by yefeng on 8/5/15.
 * github:yefengfreedom
 */
public class RcyInRcyOutAdapter extends HiListAdapter<String> {


    public RcyInRcyOutAdapter(ArrayList<String> data) {
        super(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_inside_recycler, parent, false);
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
        showRecycler(((ViewHolder) viewHolder), mData);
        viewHolder.itemView.setTag(mData.get(i));
    }

    private void showRecycler(ViewHolder holder, ArrayList<String> data) {
        HiRecyclerView list = holder.mList;
        list.setHasFixedSize(true);
        UnfoldLinearLayoutManager layoutManager = new UnfoldLinearLayoutManager(
                holder.itemView.getContext(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        RcyInRcyInsideAdapter adapter = new RcyInRcyInsideAdapter(data);
        list.setAdapter(adapter);
        list.setDivider(R.drawable.divider);
    }

    private static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView mText;
        HiRecyclerView mList;

        public ViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.txt_adapter_item);
            mList = (HiRecyclerView) itemView.findViewById(R.id.recycler);
        }
    }
}
