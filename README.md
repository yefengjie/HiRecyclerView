# Yf List Recycler View
preview at google play:https://play.google.com/store/apps/details?id=com.freedom.yefeng.yfrecyclerview.example
a powerful list recycler view to replace list view, with function belows: 

1.header and footer view

2.auto load more

3.pull to refresh

4.list divider

5.change display mode (data,empty,error)

6.add recycler view inside recycler view demo

## How To Use

### first

include libs in your build.gradle

or see it in maven :https://bintray.com/yefengfreedom/maven/yflistrecyclerview/view

```Java
    compile 'com.android.support:recyclerview-v7:22.2.0'
    compile 'com.yefeng:yf_list_recycler_view:1.1.0'
```



### second
new a RecyclerViewAdapter, and override the method whitch you want to use, just like SampleActivity do...

```Java
public class DemoAdapter extends YfListAdapter<String> {

    public DemoAdapter(ArrayList<String> data) {
        super(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateEmptyViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_empty_material, parent, false);
        return new YfSimpleViewHolder(view);
    }

    @Override
    public void onBindDataViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).mText.setText(mData.get(i));
        viewHolder.itemView.setTag(mData.get(i));
    }

    private static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView mText;

        public ViewHolder(final View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.txt_adapter_item);
        }
    }
}
mRecycler.setAdapter(new DemoAdapter(list_data));
```

### how to add divider

```Java
    mList.setDivider(R.drawable.divider);
```

### how to enable load more

```Java
    mList.enableAutoLoadMore(new YfLoadMoreListener() {
                @Override
                public void loadMore() {

                }
            });
```

### how to use recycler view inside recycler view

use ExpansionLinearLayoutManager to replace LinearLayoutManager

```Java
ExpansionLinearLayoutManager layoutManager = new ExpansionLinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
recyclerView.setLayoutManager(layoutManager);
```

### change display mode(show loading data,show data,show empty,show error)

show data. if data is empty, empty view will display.

```Java
mAdapter.setData(mData);
```

show loading

```Java
mAdapter.changeMode(RecyclerViewMode.MODE_LOADING);
```

show error

```Java
mAdapter.changeMode(RecyclerViewMode.MODE_ERROR)
```

### add header or footer

override onCreateHeaderViewHolder and onBindHeaderViewHolder or onCreateFooterViewHolder and onBindFooterViewHolder method

```Java
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
```

call adapter's addHeader or addFooter method
 
!!! make sure your adapter is in RecyclerViewMode.MODE_DATA

```Java
 mAdapter.addHeader("header 1");
 mAdapter.removeHeader(header);
 mAdapter.addFooter("footer 1");
 mAdapter.removeFooter(footer);
```


![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/0.png)
![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/6.png)
![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/1.png)
![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/2.png)
![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/2.5.png)
![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/3.png)
![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/4.png)
![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/5.png)

## Change Log
#### 15-7-25  add auto load more method.  add list divider

#### 15-7-28  add recycler view inside recycler view demo

#### 15-7-30  upload to maven center

#### 15-8-5   add recycler view adapter demo

#### 15-8-7   move divider and load more method to library

