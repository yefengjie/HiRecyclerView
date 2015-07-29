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
add this init method below in your application

```Java
DisplayInfo.init(getApplicationContext());
```


#### second
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


![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/6.png)
![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/1.png)
![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/3.png)
![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/4.png)
![alt tag](https://github.com/yefengfreedom/RecyclerViewWithHeaderFooterLoadingEmptyViewErrorView/blob/master/preview/5.png)

## Change Log
#### 15-8-25  add auto load more method.  add list divider

#### 15-8-28  add recyler view inside recycler view demo

