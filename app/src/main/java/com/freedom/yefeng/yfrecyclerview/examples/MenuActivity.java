package com.freedom.yefeng.yfrecyclerview.examples;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.freedom.yefeng.yfrecyclerview.HiInterface;
import com.freedom.yefeng.yfrecyclerview.HiRecyclerView;
import com.freedom.yefeng.yfrecyclerview.examples.adapter.MenuAdapter;
import com.freedom.yefeng.yfrecyclerview.examples.base.AppInfo;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    ImageView mIvShow;
    Toolbar mTb;
    CollapsingToolbarLayout mCtl;
    CoordinatorLayout mCl;
    HiRecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvShow = (ImageView) findViewById(R.id.iv_show);
        mTb = (Toolbar) findViewById(R.id.tb);
        mCtl = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        mCl = (CoordinatorLayout) findViewById(R.id.cl);
        mList = (HiRecyclerView) findViewById(R.id.rv);
        initToolbar();
        init();
    }

    private void initToolbar() {
        setSupportActionBar(mTb);
        final ActionBar ab = getSupportActionBar();
        if (null != ab) {
            ab.setHomeAsUpIndicator(R.drawable.ic_nav_story);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        mTb.setTitleTextColor(getResources().getColor(R.color.yf_white));
        mCtl.setTitle("Yf List Recycler View");
        mCtl.setExpandedTitleColor(getResources().getColor(R.color.yf_indigo_accent));
        int height = AppInfo.width / 4 * 3;
        CollapsingToolbarLayout.LayoutParams lp = new CollapsingToolbarLayout.LayoutParams(AppInfo.width, height);
        mIvShow.setLayoutParams(lp);
    }

    private void init() {
        ArrayList<String> actions = new ArrayList<String>();
        actions.add("Yf List Recycler View");
        actions.add("Recycler List Inside Recycler List");
        actions.add("Yf List Recycler Adapter Demo");
        MenuAdapter adapter = new MenuAdapter(actions);
        mList.setHasFixedSize(true);
        mList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mList.setAdapter(adapter);
        adapter.setOnItemClickListener(new HiInterface.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object o) {
                if ("Recycler List Inside Recycler List".equals(o.toString())) {
                    startActivity(new Intent(MenuActivity.this, SampleRecyclerInsideRecyclerActivity.class));
                } else if ("Yf List Recycler Adapter Demo".equals(o.toString())) {
                    startActivity(new Intent(MenuActivity.this, SampleAdapterDemoActivity.class));
                } else {
                    startActivity(new Intent(MenuActivity.this, SampleActivity.class));
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Uri uri = Uri.parse("https://github.com/yefengfreedom");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
