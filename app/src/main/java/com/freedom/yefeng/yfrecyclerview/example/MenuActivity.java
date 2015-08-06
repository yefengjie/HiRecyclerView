package com.freedom.yefeng.yfrecyclerview.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.freedom.yefeng.yfrecyclerview.example.base.AppInfo;

public class MenuActivity extends AppCompatActivity {

    ImageView mIvShow;
    Toolbar mTb;
    CollapsingToolbarLayout mCtl;
    CoordinatorLayout mCl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvShow = (ImageView) findViewById(R.id.iv_show);
        mTb = (Toolbar) findViewById(R.id.tb);
        mCtl = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        mCl = (CoordinatorLayout) findViewById(R.id.cl);
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
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, SampleActivity.class));
            }
        });
        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, SampleRecyclerInsideRecyclerActivity.class));
            }
        });
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, SampleAdapterDemoActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "github:yefengfreedom", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
