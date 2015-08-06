# YfRecyclerView
a economic recycler view with functions list below:

1.add header view

2.add footer view

3.auto load more

4.pull to refresh

5.empty display

6.loading display

7.error display

8.divider

9.add recycler view inside recycler view demo

## How To Use

### first

include libs in your build.gradle

or see it in maven :https://bintray.com/yefengfreedom/maven/YfRecyclerView/view

```Java
   compile 'com.freedom.yefeng:yfrecyclerview:0.1.6'
```



### sencond
new a RecyclerViewAdapter in your activity,and override the method whitch you want to use, just like SampleActivity do...

```Java
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
            public RecyclerView.ViewHolder onCreateEmptyViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_empty_material, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public RecyclerView.ViewHolder onCreateLoadingViewHolder(ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_loading_material, parent, false);
                return new SimpleViewHolder(view);
            }
        };
mRecycler.setAdapter(mAdapter);
```

### how to use recycler view inside recycler view

use ExpansionLinearLayoutManager to replace LinearLayoutManager

```Java
ExpansionLinearLayoutManager layoutManager = new ExpansionLinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
recyclerView.setLayoutManager(layoutManager);
```

### change display mode(show loading data,show data,show empty,show error)

show data.if data is empty, empty view will display.

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

