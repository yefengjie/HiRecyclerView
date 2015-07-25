package com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.example;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.R;
import com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.libs.RecyclerViewAdapter;
import com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.libs.RecyclerViewInterface;
import com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.libs.RecyclerViewMode;
import com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.libs.SimpleViewHolder;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class SampleActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private SwipeRefreshLayout mSwipeLayout;
    private LinearLayoutManager mLayoutManager;
    private RecyclerViewAdapter mAdapter;
    ArrayList<String> mData = new ArrayList<String>();

    private int h = 0;
    private int f = 0;
    private int mode = RecyclerViewMode.MODE_DATA;

    private int mVisibleItemCount, mTotalItemCount, mFirstVisibleItemPosition;
    private int mTotalDataCount = 100;
    private int mCurrentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        mAdapter = new RecyclerViewAdapter(mData) {
            @Override
            public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView.findViewById(R.id.txt_adapter_item)).setText((String) mData.get(position) + " page is " + mCurrentPage);
                holder.itemView.setTag(mData.get(position));
            }

            @Override
            public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header1, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView.findViewById(R.id.tv_header)).setText(mAdapter.getHeaders().get(position).toString());
                holder.itemView.setTag(mAdapter.getHeaders().get(position).toString());
            }

            @Override
            public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_footer1, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView.findViewById(R.id.tv_footer)).setText(mAdapter.getFooters().get(position).toString());
                holder.itemView.setTag(mAdapter.getFooters().get(position).toString());
            }

            @Override
            public RecyclerView.ViewHolder onCreateEmptyViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_empty_material, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public RecyclerView.ViewHolder onCreateErrorViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_net_error_material, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public RecyclerView.ViewHolder onCreateLoadingViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_loading_material, parent, false);
                return new SimpleViewHolder(view);
            }
        };
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
                mAdapter.setData(null);
                return true;
            case R.id.action_set_data:
                mCurrentPage = 1;
                mData.clear();
                for (int i = 0; i < 20; i++) {
                    mData.add("item  " + i);
                }
                mAdapter.setData(mData);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
