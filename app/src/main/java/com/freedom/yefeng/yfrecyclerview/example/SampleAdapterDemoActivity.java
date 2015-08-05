package com.freedom.yefeng.yfrecyclerview.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.freedom.yefeng.yfrecyclerview.RecyclerViewInterface;

import java.util.ArrayList;

/**
 * Created by yefeng on 8/5/15.
 * github:yefengfreedom
 */
public class SampleAdapterDemoActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private LinearLayoutManager mLayoutManager;
    private DemoAdapter mAdapter;
    private ArrayList<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_adapter_demo);

        for (int i = 0; i < 20; i++) {
            mData.add("item  " + i);
        }

        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecycler.setLayoutManager(mLayoutManager);

        mAdapter = new DemoAdapter(mData);
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerViewInterface.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object o) {
                Toast.makeText(getApplicationContext(), (String) o, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
