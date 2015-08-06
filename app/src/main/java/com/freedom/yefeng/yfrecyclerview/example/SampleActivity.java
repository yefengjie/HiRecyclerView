package com.freedom.yefeng.yfrecyclerview.example;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.freedom.yefeng.yfrecyclerview.RecyclerViewInterface;
import com.freedom.yefeng.yfrecyclerview.RecyclerViewMode;
import com.freedom.yefeng.yfrecyclerview.example.adapter.SimpleAdapter;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class SampleActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private SwipeRefreshLayout mSwipeLayout;
    private LinearLayoutManager mLayoutManager;
    private SimpleAdapter mAdapter;
    ArrayList<String> mData = new ArrayList<String>();

    private int h = 0;
    private int f = 0;
    private int mode = RecyclerViewMode.MODE_DATA;

    private int mVisibleItemCount, mTotalItemCount, mFirstVisibleItemPosition;
    private int mTotalDataCount = 100;
    private int mCurrentPage = 1;
    private Drawable mDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        if (null != ab) {
            ab.setHomeAsUpIndicator(R.drawable.ic_action_back);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        for (int i = 0; i < 20; i++) {
            mData.add("item  " + i);
        }

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });

        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecycler.setLayoutManager(mLayoutManager);

        initAdapter();
        mRecycler.setAdapter(mAdapter);
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (null == mLayoutManager || null == mAdapter) {
                    return;
                }
                mVisibleItemCount = mLayoutManager.getChildCount();
                mTotalItemCount = mLayoutManager.getItemCount();
                mFirstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
                if ((mVisibleItemCount + mFirstVisibleItemPosition) >= mTotalItemCount) {
                    loadMore();
                }
            }
        });
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

    private void loadMore() {
        if (mAdapter.getData().size() < mTotalDataCount && mAdapter.getData().size() > 0) {
            mCurrentPage++;
            ArrayList<String> moreData = new ArrayList<String>();
            for (int i = 0; i < 20; i++) {
                moreData.add("item  " + (20 * (mCurrentPage - 1) + i));
            }
            mAdapter.addData(moreData);
            showToast("load more. current page is " + mCurrentPage);
        }
    }

    private void initAdapter() {
        mAdapter = new SimpleAdapter(mData);
        mAdapter.setOnItemClickListener(new RecyclerViewInterface.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object o) {
                showToast((String) o);
            }
        });
        mAdapter.setOnItemLongClickListener(new RecyclerViewInterface.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, Object o) {
                showToast(o + " long click");
            }
        });
        mAdapter.setOnHeaderViewClickListener(new RecyclerViewInterface.OnHeaderViewClickListener() {
            @Override
            public void onHeaderViewClick(View view, Object o) {
                showToast((String) o);
            }
        });
        mAdapter.setOnFooterViewClickListener(new RecyclerViewInterface.OnFooterViewClickListener() {
            @Override
            public void onFooterViewClick(View view, Object o) {
                showToast((String) o);
            }
        });
        mAdapter.setOnEmptyViewClickListener(new RecyclerViewInterface.OnEmptyViewClickListener() {
            @Override
            public void onEmptyViewClick(View view) {
                showToast("click empty view");
            }
        });

        mAdapter.setOnErrorViewClickListener(new RecyclerViewInterface.OnErrorViewClickListener() {
            @Override
            public void onErrorViewClick(View view) {
                showToast("click error view");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_add_header:
                if (h < 0) {
                    h = 0;
                }
                h++;
                mAdapter.addHeader("header " + h);
                mRecycler.scrollToPosition(0);
                return true;
            case R.id.action_remove_header:
                if (h <= 0) {
                    return true;
                }
                h--;
                mAdapter.removeHeader(h);
                mRecycler.scrollToPosition(0);
                return true;
            case R.id.action_add_footer:
                if (f < 0) {
                    f = 0;
                }
                f++;
                mAdapter.addFooter("footer " + f);
                mRecycler.scrollToPosition(mAdapter.getItemCount() - 1);
                return true;
            case R.id.action_remove_footer:
                if (f <= 0) {
                    return true;
                }
                f--;
                mAdapter.removeFooter(f);
                mRecycler.scrollToPosition(mAdapter.getItemCount() - 1);
                return true;
            case R.id.action_change_mode:
                mAdapter.changeMode(++mode);
                if (mode > RecyclerViewMode.MODE_EMPTY) {
                    mode = RecyclerViewMode.MODE_DATA;
                }
                return true;
            case R.id.action_clear_data:
                mCurrentPage = 1;
                h = 0;
                f = 0;
                mAdapter.removeAllHeader();
                mAdapter.removeAllFooters();
                mAdapter.setData(null);
                return true;
            case R.id.action_set_data:
                mCurrentPage = 1;
                mData.clear();
                for (int i = 0; i < 20; i++) {
                    mData.add("item  " + i);
                }
                h = 0;
                f = 0;
                mAdapter.removeAllHeader();
                mAdapter.removeAllFooters();
                mAdapter.setData(mData);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
