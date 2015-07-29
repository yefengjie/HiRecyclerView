package com.freedom.yefeng.yfrecyclerview.libs;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by yefeng on 7/24/15.
 * github:yefengfreedom
 * <p/>
 * this is a recycler view adapter with empty view, loading view,data view,header view,footer view
 * if you want to enable each views, you should Override each onCreateViewHolder method and onBindViewHolder method
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter {

    /**
     * data set
     */
    protected ArrayList<T> mData;
    /**
     * headers
     */
    protected ArrayList<T> mHeaders;
    /**
     * footers
     */
    protected ArrayList<T> mFooters;
    /**
     * recycler view mode
     */
    protected int mMode;

    /**
     * tool bar height
     */
    protected int mToolBarHeight;

    private RecyclerViewInterface.OnItemClickListener mOnItemClickListener;
    private RecyclerViewInterface.OnItemLongClickListener mOnItemLongClickListener;
    private RecyclerViewInterface.OnEmptyViewClickListener mOnEmptyViewClickListener;
    private RecyclerViewInterface.OnErrorViewClickListener mOnErrorViewClickListener;
    private RecyclerViewInterface.OnHeaderViewClickListener mOnHeaderViewClickListener;
    private RecyclerViewInterface.OnFooterViewClickListener mOnFooterViewClickListener;

    public RecyclerViewAdapter(ArrayList<T> data) {
        this(data, RecyclerViewMode.MODE_DATA, 0);
    }

    public RecyclerViewAdapter(ArrayList<T> data, int mode) {
        this(data, mode, 0);
    }

    public RecyclerViewAdapter(ArrayList<T> data, int mode, int toolBarHeight) {
        this(data, null, null, mode, toolBarHeight);
    }

    public RecyclerViewAdapter(ArrayList<T> data, ArrayList<T> headers, ArrayList<T> footers, int mode, int toolBarHeight) {
        this.mData = null == data ? new ArrayList<T>() : data;
        this.mHeaders = null == headers ? new ArrayList<T>() : headers;
        this.mFooters = null == footers ? new ArrayList<T>() : footers;
        this.mMode = mData.isEmpty() ? RecyclerViewMode.MODE_EMPTY : mode;
        this.mToolBarHeight = toolBarHeight;
    }

    public void setData(ArrayList<T> data) {
        setData(data, RecyclerViewMode.MODE_DATA);
    }

    public void setData(ArrayList<T> data, int mode) {
        this.mData = null == data ? new ArrayList<T>() : data;
        this.mMode = mData.isEmpty() ? RecyclerViewMode.MODE_EMPTY : mode;
        this.notifyDataSetChanged();
    }

    public void addData(ArrayList<T> data) {
        if (null == data || data.isEmpty()) {
            return;
        }
        int startPosition = mData.size() + mHeaders.size();
        this.mData.addAll(data);
        this.notifyItemRangeInserted(startPosition, data.size());
    }

    public ArrayList<T> getData() {
        return mData;
    }

    public ArrayList<T> getHeaders() {
        return mHeaders;
    }

    public ArrayList<T> getFooters() {
        return mFooters;
    }

    /**
     * change recycler view display mode
     *
     * @param mode mode
     */
    public void changeMode(int mode) {
        if (mode < 0 || mode > RecyclerViewMode.MODE_EMPTY) {
            mode = 0;
        }
        mMode = mode;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerViewMode.MODE_LOADING) {
            RecyclerView.ViewHolder loadingViewHolder = onCreateLoadingViewHolder(parent);
            loadingViewHolder.itemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getHeight() - mToolBarHeight)
            );
            return loadingViewHolder;
        }
        if (viewType == RecyclerViewMode.MODE_ERROR) {
            RecyclerView.ViewHolder errorViewHolder = onCreateErrorViewHolder(parent);
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
        if (viewType == RecyclerViewMode.MODE_EMPTY) {
            RecyclerView.ViewHolder emptyViewHolder = onCreateEmptyViewHolder(parent);
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
        if (viewType == RecyclerViewMode.MODE_HEADER_VIEW) {
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
        if (viewType == RecyclerViewMode.MODE_FOOTER_VIEW) {
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
        if (mMode == RecyclerViewMode.MODE_LOADING) {
            onBindLoadingViewHolder(holder, position);
        } else if (mMode == RecyclerViewMode.MODE_ERROR) {
            onBindErrorViewHolder(holder, position);
        } else if (mMode == RecyclerViewMode.MODE_EMPTY) {
            onBindEmptyViewHolder(holder, position);
        } else {
            if (position < mHeaders.size()) {
                if (mHeaders.size() > 0) {
                    onBindHeaderViewHolder(holder, position);
                }
            } else if (position >= mHeaders.size() + mData.size()) {
                if (mFooters.size() > 0) {
                    onBindFooterViewHolder(holder, position - mHeaders.size() - mData.size());
                }
            } else {
                onBindDataViewHolder(holder, position - mHeaders.size());
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mMode == RecyclerViewMode.MODE_DATA) {
            return mData.size() + mHeaders.size() + mFooters.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mMode == RecyclerViewMode.MODE_LOADING) {
            return RecyclerViewMode.MODE_LOADING;
        }
        if (mMode == RecyclerViewMode.MODE_ERROR) {
            return RecyclerViewMode.MODE_ERROR;
        }
        if (mMode == RecyclerViewMode.MODE_EMPTY) {
            return RecyclerViewMode.MODE_EMPTY;
        }
        //check what type our position is, based on the assumption that the order is headers > items > footers
        if (position < mHeaders.size()) {
            return RecyclerViewMode.MODE_HEADER_VIEW;
        } else if (position >= mHeaders.size() + mData.size()) {
            return RecyclerViewMode.MODE_FOOTER_VIEW;
        }
        return RecyclerViewMode.MODE_DATA;
    }

    public abstract RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent);

    public RecyclerView.ViewHolder onCreateLoadingViewHolder(ViewGroup parent) {
        return null;
    }

    public RecyclerView.ViewHolder onCreateErrorViewHolder(ViewGroup parent) {
        return null;
    }

    public RecyclerView.ViewHolder onCreateEmptyViewHolder(ViewGroup parent) {
        return null;
    }

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

    public void setOnFooterViewClickListener(RecyclerViewInterface.OnFooterViewClickListener onFooterViewClickListener) {
        mOnFooterViewClickListener = onFooterViewClickListener;
    }

    public void setOnItemClickListener(RecyclerViewInterface.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(RecyclerViewInterface.OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnEmptyViewClickListener(RecyclerViewInterface.OnEmptyViewClickListener onEmptyViewClickListener) {
        mOnEmptyViewClickListener = onEmptyViewClickListener;
    }

    public void setOnErrorViewClickListener(RecyclerViewInterface.OnErrorViewClickListener onErrorViewClickListener) {
        mOnErrorViewClickListener = onErrorViewClickListener;
    }

    public void setOnHeaderViewClickListener(RecyclerViewInterface.OnHeaderViewClickListener onHeaderViewClickListener) {
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

    /**
     * add a header to the adapter
     *
     * @param header header
     */
    public void addHeader(T header) {
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
    public void removeHeader(T header) {
        if (mHeaders.contains(header)) {
            //animate
            notifyItemRemoved(mHeaders.indexOf(header));
            mHeaders.remove(header);
        }
    }

    /**
     * remove a header from the adapter
     *
     * @param position position
     */
    public void removeHeader(int position) {
        if (mHeaders.size() > 0 && position < mHeaders.size()) {
            notifyItemRemoved(position);
            mHeaders.remove(position);
        }
    }

    /**
     * add a footer to the adapter
     *
     * @param footer footer
     */
    public void addFooter(T footer) {
        if (!mFooters.contains(footer)) {
            mFooters.add(footer);
            //animate
            notifyItemInserted(mHeaders.size() + mData.size() + mFooters.size() - 1);
        }
    }

    /**
     * remove a footer from the adapter
     *
     * @param footer footer view
     */
    public void removeFooter(T footer) {
        if (mFooters.contains(footer)) {
            //animate
            notifyItemRemoved(mHeaders.size() + mData.size() + mFooters.indexOf(footer));
            mFooters.remove(footer);
        }
    }

    /**
     * remove a footer from the adapter
     *
     * @param position position
     */
    public void removeFooter(int position) {
        if (mFooters.size() > 0 && position < mFooters.size()) {
            //animate
            notifyItemRemoved(mHeaders.size() + mData.size() + position);
            mFooters.remove(position);
        }
    }
}

