package com.freedom.yefeng.yfrecyclerview.libs;

import android.view.View;

/**
 * Created by yefeng on 7/24/15.
 * github:yefengfreedom
 */
public class RecyclerViewInterface {

    /**
     * click recycler view item
     *
     * @param <T> t
     */
    public interface OnItemClickListener<T> {
        void onItemClick(View view, T t);
    }

    /**
     * long click recycler view item
     *
     * @param <T> t
     */
    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, T t);
    }

    /**
     * click empty view
     */
    public interface OnEmptyViewClickListener {
        void onEmptyViewClick(View view);
    }

    /**
     * click error view
     */
    public interface OnErrorViewClickListener {
        void onErrorViewClick(View view);
    }

    /**
     * click header view
     */
    public interface OnHeaderViewClickListener<T> {
        void onHeaderViewClick(View view, T t);
    }

    /**
     * click footer view
     */
    public interface OnFooterViewClickListener<T> {
        void onFooterViewClick(View view, T t);
    }

}
