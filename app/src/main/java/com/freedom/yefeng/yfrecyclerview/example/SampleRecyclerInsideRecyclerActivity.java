package com.freedom.yefeng.yfrecyclerview.example;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.freedom.yefeng.yfrecyclerview.HiRecyclerView;
import com.freedom.yefeng.yfrecyclerview.example.adapter.RcyInRcyOutAdapter;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class SampleRecyclerInsideRecyclerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_inside_recycler);

        ArrayList<String> mData = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            mData.add("item  " + i);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        if (null != ab) {
            ab.setHomeAsUpIndicator(R.drawable.ic_action_back);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        HiRecyclerView list = (HiRecyclerView) findViewById(R.id.recycler);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        RcyInRcyOutAdapter adapter = new RcyInRcyOutAdapter(mData);
        list.setAdapter(adapter);
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
