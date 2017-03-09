package com.freedom.yefeng.yfrecyclerview.example;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.freedom.yefeng.yfrecyclerview.HiRecyclerView;
import com.freedom.yefeng.yfrecyclerview.example.adapter.DemoAdapter;

import java.util.ArrayList;

/**
 * Created by yefeng on 8/5/15.
 * github:yefengfreedom
 */
public class SampleAdapterDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_adapter_demo);

        ArrayList<String> mData = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mData.add("item  " + i);
        }

        Toolbar mToolbar = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(mToolbar);

        final ActionBar ab = getSupportActionBar();
        if (null != ab) {
            ab.setHomeAsUpIndicator(R.drawable.ic_action_back);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        HiRecyclerView mList = (HiRecyclerView) findViewById(R.id.recycler);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        DemoAdapter adapter = new DemoAdapter(mData);
        mList.setAdapter(adapter);
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
