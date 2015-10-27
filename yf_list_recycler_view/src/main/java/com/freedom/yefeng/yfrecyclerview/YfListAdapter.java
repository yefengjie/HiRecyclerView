package com.freedom.yefeng.yfrecyclerview;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yefeng on 7/24/15.
 * github:yefengfreedom
 * this is a recycler view adapter with empty view, loading view,data view,header view,footer view
 * if you want to enable each views, you should Override each onCreateViewHolder method and onBindViewHolder method
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class YfListAdapter extends RecyclerView.Adapter {

    private static final String TAG = "YfListAdapter";

    /**
     * headers
     */
    public ArrayList<Object> mHeaders;
    /**
     * footers
     */
    public ArrayList<Object> mFooters;
    /**
     * recycler view mode
     */
    public int mMode;

    /**
     * tool bar height
     */
    //because our toobar is ThemeOverlay, so we should minus toolbar height,
    //you can use AppBarLayout.getMeasuredHeight method to get toobar height.
    protected int mToolBarHeight;

    protected YfListInterface.OnItemClickListener mOnItemClickListener;
    protected YfListInterface.OnItemLongClickListener mOnItemLongClickListener;
    protected YfListInterface.OnEmptyViewClickListener mOnEmptyViewClickListener;
    protected YfListInterface.OnErrorViewClickListener mOnErrorViewClickListener;
    protected YfListInterface.OnHeaderViewClickListener mOnHeaderViewClickListener;
    protected YfListInterface.OnFooterViewClickListener mOnFooterViewClickListener;

    public YfListAdapter(List<?> data) {
        this(data, YfListMode.MODE_DATA, 0);
    }

    public YfListAdapter(List<?> data, int mode) {
        this(data, mode, 0);
    }

    public YfListAdapter(List<?> data, int mode, int toolBarHeight) {
        this(data, null, null, mode, toolBarHeight);
    }

    public YfListAdapter(List<?> data, ArrayList<Object> headers, ArrayList<Object> footers, int mode, int toolBarHeight) {
        this.mHeaders = null == headers ? new ArrayList<Object>() : headers;
        this.mFooters = null == footers ? new ArrayList<Object>() : footers;
        this.mMode = null == data ? YfListMode.MODE_EMPTY : (0 == data.size() ? YfListMode.MODE_EMPTY : mode);
        this.mToolBarHeight = toolBarHeight;
    }

    public ArrayList<Object> getHeaders() {
        return mHeaders;
    }

    public ArrayList<Object> getFooters() {
        return mFooters;
    }

    /**
     * change recycler view display mode
     *
     * @param mode mode
     */
    public void changeMode(int mode) {
        if (mode < 0 || mode > YfListMode.MODE_EMPTY) {
            mode = 0;
        }
        mMode = mode;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == YfListMode.MODE_LOADING) {
            RecyclerView.ViewHolder loadingViewHolder = onCreateLoadingViewHolder(parent);
            //because our toobar is ThemeOverlay, so we should minus toolbar height
            loadingViewHolder.itemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getHeight() - mToolBarHeight)
            );
            return loadingViewHolder;
        }
        if (viewType == YfListMode.MODE_ERROR) {
            RecyclerView.ViewHolder errorViewHolder = onCreateErrorViewHolder(parent);
            //because our toobar is ThemeOverlay, so we should minus toolbar height
            errorViewHolder.itemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getHeight() - mToolBarHeight)
            );
            errorViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (null != mOnErrorViewClickListener) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mOnErrorViewClickListener.onErrorViewClick(v);
                            }
                        }, 200);
                    }
                }
            });
            return errorViewHolder;
        }
        if (viewType == YfListMode.MODE_EMPTY) {
            RecyclerView.ViewHolder emptyViewHolder = onCreateEmptyViewHolder(parent);
            //because our toobar is ThemeOverlay, so we should minus toolbar height
            emptyViewHolder.itemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getHeight() - mToolBarHeight)
            );
            emptyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (null != mOnEmptyViewClickListener) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mOnEmptyViewClickListener.onEmptyViewClick(v);
                            }
                        }, 200);
                    }
                }
            });
            return emptyViewHolder;
        }
        if (viewType == YfListMode.MODE_HEADER_VIEW) {
            RecyclerView.ViewHolder headerViewHolder = onCreateHeaderViewHolder(parent);
            headerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (null != mOnHeaderViewClickListener) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mOnHeaderViewClickListener.onHeaderViewClick(v, v.getTag());
                            }
                        }, 200);
                    }
                }
            });
            return headerViewHolder;
        }
        if (viewType == YfListMode.MODE_FOOTER_VIEW) {
            RecyclerView.ViewHolder footerViewHolder = onCreateFooterViewHolder(parent);
            footerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (null != mOnFooterViewClickListener) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mOnFooterViewClickListener.onFooterViewClick(v, v.getTag());
                            }
                        }, 200);
                    }
                }
            });
            return footerViewHolder;
        }
        RecyclerView.ViewHolder dataViewHolder = onCreateDataViewHolder(parent);
        dataViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (null != mOnItemClickListener) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mOnItemClickListener.onItemClick(v, v.getTag());
                        }
                    }, 200);
                }
            }
        });
        dataViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                if (null != mOnItemLongClickListener) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mOnItemLongClickListener.onItemLongClick(v, v.getTag());
                        }
                    }, 200);
                    return true;
                }
                return false;
            }
        });
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mMode == YfListMode.MODE_LOADING) {
            onBindLoadingViewHolder(holder, position);
        } else if (mMode == YfListMode.MODE_ERROR) {
            onBindErrorViewHolder(holder, position);
        } else if (mMode == YfListMode.MODE_EMPTY) {
            onBindEmptyViewHolder(holder, position);
        } else {
            if (position < mHeaders.size()) {
                if (mHeaders.size() > 0) {
                    onBindHeaderViewHolder(holder, position);
                }
            } else if (position >= mHeaders.size() + getDataCount()) {
                if (mFooters.size() > 0) {
                    onBindFooterViewHolder(holder, getDataOffsetPosition(position) - getDataCount());
                }
            } else {
                onBindDataViewHolder(holder, getDataOffsetPosition(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mMode == YfListMode.MODE_DATA) {
            return getDataCount() + mHeaders.size() + mFooters.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mMode == YfListMode.MODE_LOADING) {
            return YfListMode.MODE_LOADING;
        }
        if (mMode == YfListMode.MODE_ERROR) {
            return YfListMode.MODE_ERROR;
        }
        if (mMode == YfListMode.MODE_EMPTY) {
            return YfListMode.MODE_EMPTY;
        }
        //check what type our position is, based on the assumption that the order is headers > items > footers
        if (position < mHeaders.size()) {
            return YfListMode.MODE_HEADER_VIEW;
        } else if (position >= mHeaders.size() + getDataCount()) {
            return YfListMode.MODE_FOOTER_VIEW;
        }
        return YfListMode.MODE_DATA;
    }

    public abstract RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent);

    public RecyclerView.ViewHolder onCreateLoadingViewHolder(ViewGroup parent) {
        return null;
    }

    public RecyclerView.ViewHolder onCreateErrorViewHolder(ViewGroup parent) {
        return null;
    }

    public abstract RecyclerView.ViewHolder onCreateEmptyViewHolder(ViewGroup parent);

    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent) {
        return null;
    }

    public abstract void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position);

    public void onBindLoadingViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    public void onBindErrorViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    public void onBindEmptyViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    public void setOnFooterViewClickListener(YfListInterface.OnFooterViewClickListener onFooterViewClickListener) {
        mOnFooterViewClickListener = onFooterViewClickListener;
    }

    public void setOnItemClickListener(YfListInterface.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(YfListInterface.OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnEmptyViewClickListener(YfListInterface.OnEmptyViewClickListener onEmptyViewClickListener) {
        mOnEmptyViewClickListener = onEmptyViewClickListener;
    }

    public void setOnErrorViewClickListener(YfListInterface.OnErrorViewClickListener onErrorViewClickListener) {
        mOnErrorViewClickListener = onErrorViewClickListener;
    }

    public void setOnHeaderViewClickListener(YfListInterface.OnHeaderViewClickListener onHeaderViewClickListener) {
        mOnHeaderViewClickListener = onHeaderViewClickListener;
    }

    public void setToolBarHeight(int toolBarHeight) {
        mToolBarHeight = toolBarHeight;
        this.notifyDataSetChanged();
    }


    public int getFooterCount() {
        return mFooters.size();
    }

    public int getHeaderCount() {
        return mHeaders.size();
    }

    public abstract int getDataCount();

    /**
     * Notice headers, footers and custom data set to update UI
     */
    public void notifyAllDataSetChanged()
    {
        if(autoChangeMode()){
            return;
        }

        this.notifyDataSetChanged();
    }

    /**
     * Notice all custom data set to update UI
     */
    public void notifyCustomDataSetChanged()
    {
        if(autoChangeMode()){
            return;
        }

        int dataCount = getDataCount();
        int startPosition = dataCount + mHeaders.size();
        this.notifyItemRangeInserted(startPosition, dataCount);
    }

    /**
     * add a header to the adapter
     *
     * @param header header
     */
    public void addHeader(Object header) {
        if (mMode != YfListMode.MODE_DATA) {
            Log.e(TAG, "error: you can not add header or footer while you are not in data mode");
            return;
        }
        if (!mHeaders.contains(header)) {
            mHeaders.add(header);
            //animate
            notifyItemInserted(mHeaders.size() - 1);
        }
    }

    /**
     * remove a header from the adapter
     *
     * @param header header
     */
    public void removeHeader(Object header) {
        if (mHeaders.contains(header)) {
            int position = mHeaders.indexOf(header);
            mHeaders.remove(position);
            //animate
            notifyItemRemoved(position);
        }
    }

    /**
     * remove a header from the adapter
     *
     * @param position position
     */
    public void removeHeader(int position) {
        if (mHeaders.size() > 0 && position < mHeaders.size()) {
            mHeaders.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * remove all headers
     */
    public void removeAllHeader() {
        if (mHeaders.size() > 0) {
            notifyItemRangeRemoved(0, mHeaders.size());
            mHeaders.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * add a footer to the adapter
     *
     * @param footer footer
     */
    public void addFooter(Object footer) {
        if (mMode != YfListMode.MODE_DATA) {
            Log.e(TAG, "error: you can not add header or footer while you are not in data mode");
            return;
        }
        if (!mFooters.contains(footer)) {
            mFooters.add(footer);
            //animate
            notifyItemInserted(mHeaders.size() + getDataCount() + mFooters.size() - 1);
        }
    }

    /**
     * remove a footer from the adapter
     *
     * @param footer footer view
     */
    public void removeFooter(Object footer) {
        if (mFooters.contains(footer)) {
            int position = mFooters.indexOf(footer);
            mFooters.remove(position);
            //animate
            notifyItemRemoved(mHeaders.size() + getDataCount() + position);
        }
    }

    /**
     * remove a footer from the adapter
     *
     * @param position position
     */
    public void removeFooter(int position) {
        if (mFooters.size() > 0 && position < mFooters.size()) {
            mFooters.remove(position);
            //animate
            notifyItemRemoved(mHeaders.size() + getDataCount() + position);
        }
    }

    /**
     * remove all footers
     */
    public void removeAllFooters() {
        if (mFooters.size() > 0) {
            notifyItemRangeChanged(0, mFooters.size());
            mFooters.clear();
            notifyDataSetChanged();
        }
    }

    protected int getDataOffsetPosition(int position)
    {
        return position - mHeaders.size();
    }

    private boolean autoChangeMode(){
        int dataCount = getDataCount();
        if (dataCount == 0) {
            changeMode(YfListMode.MODE_EMPTY);
            return true;
        }
        else{
            if(YfListMode.MODE_EMPTY == mMode){
                changeMode(YfListMode.MODE_DATA);
                return true;
            }
        }
        return false;
    }
}

