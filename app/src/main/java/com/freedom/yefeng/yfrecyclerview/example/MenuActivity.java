package com.freedom.yefeng.yfrecyclerview.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.freedom.yefeng.yfrecyclerview.RecyclerViewInterface;
import com.freedom.yefeng.yfrecyclerview.example.adapter.MenuAdapter;
import com.freedom.yefeng.yfrecyclerview.example.base.AppInfo;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    ImageView mIvShow;
    Toolbar mTb;
    CollapsingToolbarLayout mCtl;
    CoordinatorLayout mCl;
    RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvShow = (ImageView) findViewById(R.id.iv_show);
        mTb = (Toolbar) findViewById(R.id.tb);
        mCtl = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        mCl = (CoordinatorLayout) findViewById(R.id.cl);
        mRv = (RecyclerView) findViewById(R.id.rv);
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
        mCtl.setTitle("yf recycler view");
        mCtl.setExpandedTitleColor(getResources().getColor(R.color.yf_indigo_accent));
        int height = AppInfo.width / 4 * 3;
        CollapsingToolbarLayout.LayoutParams lp = new CollapsingToolbarLayout.LayoutParams(AppInfo.width, height);
        mIvShow.setLayoutParams(lp);
    }

    private void init() {
        ArrayList<String> actions = new ArrayList<String>();
        actions.add("yf list recycler view");
        actions.add("recycler inside recycler");
        actions.add("recycler view adapter demo");
        MenuAdapter adapter = new MenuAdapter(actions);
        mRv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRv.setLayoutManager(layoutManager);
        mRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerViewInterface.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object o) {
                if ("recycler inside recycler".equals(o.toString())) {
                    startActivity(new Intent(MenuActivity.this, SampleRecyclerInsideRecyclerActivity.class));
                } else if ("recycler view adapter demo".equals(o.toString())) {
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
