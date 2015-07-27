package com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.example;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.R;
import com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.libs.RecyclerViewAdapter;
import com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.libs.SimpleViewHolder;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class SampleRecyclerInsideRecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private LinearLayoutManager mLayoutManager;
    private RecyclerViewAdapter mAdapter;
    ArrayList<String> mData = new ArrayList<String>();
    ArrayList<String> data = new ArrayList<String>();
    private Drawable mDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_inside_recycler);

        for (int i = 0; i < 20; i++) {
            mData.add("item  " + i);
        }

        for (int i = 0; i < 3; i++) {
            data.add("item inside  " + i);
        }

        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecycler.setLayoutManager(mLayoutManager);

        initAdapter();
        mRecycler.setAdapter(mAdapter);
        mDivider = getResources().getDrawable(R.drawable.divider_horizontal_bright_opaque);
        mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();

                int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = parent.getChildAt(i);

                    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                    int top = child.getBottom() + params.bottomMargin;
                    int bottom = top + mDivider.getIntrinsicHeight();

                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
            }
        });
    }

    private void initAdapter() {
        mAdapter = new RecyclerViewAdapter(mData) {
            @Override
            public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_inside_recycler, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView.findViewById(R.id.txt_adapter_item)).setText((String) mData.get(position));
                RecyclerView recyclerView = (RecyclerView) holder.itemView.findViewById(R.id.recycler);
                showRecycler(recyclerView);
                holder.itemView.setTag(mData.get(position));
            }
        };
    }

    private void showRecycler(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        ExpansionLinearLayoutManager layoutManager = new ExpansionLinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data) {
            @Override
            public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView.findViewById(R.id.txt_adapter_item)).setText((String) data.get(position));
                holder.itemView.setTag(mData.get(position));
            }
        };
        recyclerView.setAdapter(adapter);
    }


}
