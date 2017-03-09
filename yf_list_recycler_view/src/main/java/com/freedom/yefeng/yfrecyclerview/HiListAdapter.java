package com.freedom.yefeng.yfrecyclerview;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by yefeng on 7/24/15.
 * github:yefengfreedom
 * this is a recycler view adapter with empty view, loading view,data view,header view,footer view
 * if you want to enable each views, you should Override each onCreateViewHolder method and onBindViewHolder method
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class HiListAdapter<T> extends RecyclerView.Adapter {

    private static final String TAG = "HiListAdapter";

    /**
     * handler
     */
    private Handler mHandler = new Handler();

    /**
     * data set
     */
    public ArrayList<T> mData;
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

    private HiInterface.OnItemClickListener mOnItemClickListener;
    private HiInterface.OnItemLongClickListener mOnItemLongClickListener;
    private HiInterface.OnEmptyViewClickListener mOnEmptyViewClickListener;
    private HiInterface.OnErrorViewClickListener mOnErrorViewClickListener;
    private HiInterface.OnHeaderViewClickListener mOnHeaderViewClickListener;
    private HiInterface.OnFooterViewClickListener mOnFooterViewClickListener;

    public HiListAdapter(ArrayList<T> data) {
        this(data, HiMode.MODE_DATA, 0);
    }

    public HiListAdapter(ArrayList<T> data, int mode) {
        this(data, mode, 0);
    }

    public HiListAdapter(ArrayList<T> data, int mode, int toolBarHeight) {
        this(data, null, null, mode, toolBarHeight);
    }

    public HiListAdapter(ArrayList<T> data, ArrayList<Object> headers, ArrayList<Object> footers, int mode, int toolBarHeight) {
        this.mData = null == data ? new ArrayList<T>() : data;
        this.mHeaders = null == headers ? new ArrayList<Object>() : headers;
        this.mFooters = null == footers ? new ArrayList<Object>() : footers;
        this.mMode = mData.isEmpty() ? HiMode.MODE_EMPTY : mode;
        this.mToolBarHeight = toolBarHeight;
    }

    public void setData(final ArrayList<T> data) {
        if (data == null || data.isEmpty()) {
            this.mData = new ArrayList<T>();
            this.mMode = HiMode.MODE_EMPTY;
            this.notifyDataSetChanged();
        } else {
//            this.mData = new ArrayList<T>();
//            this.mMode = HiMode.MODE_DATA;
//            this.notifyDataSetChanged();
//            this.mData = data;
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    notifyItemRangeInserted(mHeaders.size(), mData.size());
//                }
//            });
            this.mData = data;
            this.mMode = HiMode.MODE_DATA;
            this.notifyDataSetChanged();
        }
    }

    public void addData(final ArrayList<T> data) {
        if (null == data || data.isEmpty()) {
            return;
        }
        final int startPosition = mData.size() + mHeaders.size();
        this.mData.addAll(data);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                notifyItemRangeInserted(startPosition, data.size());
            }
        });
    }

    public ArrayList<T> getData() {
        return mData;
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
        if (mode < 0 || mode > HiMode.MODE_EMPTY) {
            mode = 0;
        }
        mMode = mode;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HiMode.MODE_LOADING) {
            RecyclerView.ViewHolder loadingViewHolder = onCreateLoadingViewHolder(parent);
            //because our toobar is ThemeOverlay, so we should minus toolbar height
            loadingViewHolder.itemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getHeight() - mToolBarHeight)
            );
            return loadingViewHolder;
        }
        if (viewType == HiMode.MODE_ERROR) {
            RecyclerView.ViewHolder errorViewHolder = onCreateErrorViewHolder(parent);
            //because our toobar is ThemeOverlay, so we should minus toolbar height
            errorViewHolder.itemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getHeight() - mToolBarHeight)
            );
            if (null != mOnErrorViewClickListener) {
                errorViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mOnErrorViewClickListener.onErrorViewClick(v);
                            }
                        }, 200);
                    }
                });
            }
            return errorViewHolder;
        }
        if (viewType == HiMode.MODE_EMPTY) {
            RecyclerView.ViewHolder emptyViewHolder = onCreateEmptyViewHolder(parent);
            //because our toobar is ThemeOverlay, so we should minus toolbar height
            emptyViewHolder.itemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getHeight() - mToolBarHeight)
            );
            if (null != mOnEmptyViewClickListener) {
                emptyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mOnEmptyViewClickListener.onEmptyViewClick(v);
                            }
                        }, 200);
                    }
                });
            }
            return emptyViewHolder;
        }
        if (viewType == HiMode.MODE_HEADER_VIEW) {
            RecyclerView.ViewHolder headerViewHolder = onCreateHeaderViewHolder(parent);
            if (null != mOnHeaderViewClickListener) {
                headerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mOnHeaderViewClickListener.onHeaderViewClick(v, v.getTag());
                            }
                        }, 200);
                    }
                });
            }
            return headerViewHolder;
        }
        if (viewType == HiMode.MODE_FOOTER_VIEW) {
            RecyclerView.ViewHolder footerViewHolder = onCreateFooterViewHolder(parent);
            if (null != mOnFooterViewClickListener) {
                footerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mOnFooterViewClickListener.onFooterViewClick(v, v.getTag());
                            }
                        }, 200);
                    }
                });
            }
            return footerViewHolder;
        }
        RecyclerView.ViewHolder dataViewHolder = onCreateDataViewHolder(parent);
        if (null != mOnItemClickListener) {
            dataViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mOnItemClickListener.onItemClick(v, v.getTag());
                        }
                    }, 200);
                }
            });
        }
        if (null != mOnItemLongClickListener) {
            dataViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View v) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mOnItemLongClickListener.onItemLongClick(v, v.getTag());
                        }
                    }, 200);
                    return true;
                }
            });
        }
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mMode == HiMode.MODE_LOADING) {
            onBindLoadingViewHolder(holder, position);
        } else if (mMode == HiMode.MODE_ERROR) {
            onBindErrorViewHolder(holder, position);
        } else if (mMode == HiMode.MODE_EMPTY) {
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
        if (mMode == HiMode.MODE_DATA) {
            return mData.size() + mHeaders.size() + mFooters.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mMode == HiMode.MODE_LOADING) {
            return HiMode.MODE_LOADING;
        }
        if (mMode == HiMode.MODE_ERROR) {
            return HiMode.MODE_ERROR;
        }
        if (mMode == HiMode.MODE_EMPTY) {
            return HiMode.MODE_EMPTY;
        }
        //check what type our position is, based on the assumption that the order is headers > items > footers
        if (position < mHeaders.size()) {
            return HiMode.MODE_HEADER_VIEW;
        } else if (position >= mHeaders.size() + mData.size()) {
            return HiMode.MODE_FOOTER_VIEW;
        }
        return HiMode.MODE_DATA;
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

    public void setOnFooterViewClickListener(HiInterface.OnFooterViewClickListener onFooterViewClickListener) {
        mOnFooterViewClickListener = onFooterViewClickListener;
    }

    public void setOnItemClickListener(HiInterface.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(HiInterface.OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnEmptyViewClickListener(HiInterface.OnEmptyViewClickListener onEmptyViewClickListener) {
        mOnEmptyViewClickListener = onEmptyViewClickListener;
    }

    public void setOnErrorViewClickListener(HiInterface.OnErrorViewClickListener onErrorViewClickListener) {
        mOnErrorViewClickListener = onErrorViewClickListener;
    }

    public void setOnHeaderViewClickListener(HiInterface.OnHeaderViewClickListener onHeaderViewClickListener) {
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
    public void addHeader(Object header) {
        if (mMode != HiMode.MODE_DATA) {
            Log.e(TAG, "error: you can not add header or footer while you are not in data mode");
            return;
        }
        if (!mHeaders.contains(header)) {
            mHeaders.add(header);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //animate
                    notifyItemInserted(mHeaders.size() - 1);
                }
            });
        }
    }

    /**
     * remove a header from the adapter
     *
     * @param header header
     */
    public void removeHeader(Object header) {
        if (mHeaders.contains(header)) {
            final int position = mHeaders.indexOf(header);
            mHeaders.remove(position);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //animate
                    notifyItemRemoved(position);
                }
            });
        }
    }

    /**
     * remove a header from the adapter
     *
     * @param position position
     */
    public void removeHeader(final int position) {
        if (mHeaders.size() > 0 && position < mHeaders.size()) {
            mHeaders.remove(position);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    notifyItemRemoved(position);
                }
            });
        }
    }

    /**
     * remove all headers
     */
    public void removeAllHeader() {
        if (mHeaders.size() > 0) {
            final int headSize = mHeaders.size();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    notifyItemRangeRemoved(0, headSize);
                }
            });
            mHeaders.clear();
        }
    }

    /**
     * add a footer to the adapter
     *
     * @param footer footer
     */
    public void addFooter(Object footer) {
        if (mMode != HiMode.MODE_DATA) {
            Log.e(TAG, "error: you can not add header or footer while you are not in data mode");
            return;
        }
        if (!mFooters.contains(footer)) {
            mFooters.add(footer);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //animate
                    notifyItemInserted(mHeaders.size() + mData.size() + mFooters.size() - 1);
                }
            });
        }
    }

    /**
     * remove a footer from the adapter
     *
     * @param footer footer view
     */
    public void removeFooter(Object footer) {
        if (mFooters.contains(footer)) {
            final int position = mFooters.indexOf(footer);
            mFooters.remove(position);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //animate
                    notifyItemRemoved(mHeaders.size() + mData.size() + position);
                }
            });
        }
    }

    /**
     * remove a footer from the adapter
     *
     * @param position position
     */
    public void removeFooter(final int position) {
        if (mFooters.size() > 0 && position < mFooters.size()) {
            mFooters.remove(position);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //animate
                    notifyItemRemoved(mHeaders.size() + mData.size() + position);
                }
            });
        }
    }

    /**
     * remove all footers
     */
    public void removeAllFooters() {
        if (mFooters.size() > 0) {
            final int size = mFooters.size();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    notifyItemRangeChanged(0, size);
                    notifyItemRangeRemoved(mHeaders.size() + mData.size(), size);
                }
            });
            mFooters.clear();
        }
    }
}

