package com.freedom.yefeng.yfrecyclerview;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.freedom.yefeng.yfrecyclerview.util.YfListItemType;

import java.util.ArrayList;

/**
 * Created by KingWu on 23/10/15
 * github: github.com/KingWu
 *
 * Remark : Reverse Item Type
 *      MODE_DATA = 0
 *      MODE_LOADING = 1
 *      MODE_ERROR = 2
 *      MODE_EMPTY = 3
 *      MODE_HEADER_VIEW = 4
 *      MODE_FOOTER_VIEW = 5
 */
public abstract class YfMultiTypeListAdapter<T extends YfListItemType> extends YfListAdapter<T> {

    public YfMultiTypeListAdapter(ArrayList<T> data) {
        super(data);
    }

    public YfMultiTypeListAdapter(ArrayList<T> data, int mode) {
        super(data, mode);
    }

    public YfMultiTypeListAdapter(ArrayList<T> data, int mode, int toolBarHeight) {
        super(data, mode, toolBarHeight);
    }

    public YfMultiTypeListAdapter(ArrayList<T> data, ArrayList<Object> headers, ArrayList<Object> footers, int mode, int toolBarHeight) {
        super(data, headers, footers, mode, toolBarHeight);
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = super.getItemViewType(position);

        Log.d("", "position : " + position);
        Log.d("", "itemType : " + itemType);


        if(itemType != YfListMode.MODE_DATA){
            return itemType;
        }
        return super.getData().get(getDataOffsetPosition(position)).getItemType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        int headerCount = mHeaders.size();
        if (position >= headerCount && position < headerCount + mData.size()) {
            onBindCustomDataViewHolder(holder, getDataOffsetPosition(position));
        }
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

        RecyclerView.ViewHolder dataViewHolder = null;
        if(viewType == YfListMode.MODE_DATA){
            dataViewHolder = onCreateDataViewHolder(parent);
        }
        else{
            dataViewHolder = onCreateCustomDataViewHolder(parent, viewType);
        }

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

    public abstract void onBindCustomDataViewHolder(RecyclerView.ViewHolder holder, int position);

    public abstract RecyclerView.ViewHolder onCreateCustomDataViewHolder(ViewGroup parent, int viewType);

    // -----------------------------
    // No Use
    // -----------------------------
    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}

