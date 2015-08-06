package com.freedom.yefeng.yfrecyclerview.example;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.freedom.yefeng.yfrecyclerview.example.adapter.RcyInRcyOutAdapter;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class SampleRecyclerInsideRecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private LinearLayoutManager mLayoutManager;
    private RcyInRcyOutAdapter mAdapter;
    private Toolbar mToolbar;
    ArrayList<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_inside_recycler);
        for (int i = 0; i < 5; i++) {
            mData.add("item  " + i);
        }

        mToolbar = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(mToolbar);

        final ActionBar ab = getSupportActionBar();
        if (null != ab) {
            ab.setHomeAsUpIndicator(R.drawable.ic_action_back);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecycler.setLayoutManager(mLayoutManager);

        mAdapter = new RcyInRcyOutAdapter(mData);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
