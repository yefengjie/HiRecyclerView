package com.freedom.yefeng.yfrecyclerview.example;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.freedom.yefeng.yfrecyclerview.RecyclerViewAdapter;
import com.freedom.yefeng.yfrecyclerview.SimpleViewHolder;

import java.util.ArrayList;

/**
 * Created by yefeng on 8/5/15.
 * github:yefengfreedom
 */
public class DemoAdapter extends RecyclerViewAdapter<String> {

    public DemoAdapter(ArrayList<String> data) {
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
        return new SimpleViewHolder(view);
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
            mText = (TextView) itemView.findViewById(R.id.txt_adapter_item);
            mText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Toast.makeText(itemView.getContext(), "touch item", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
    }
}
