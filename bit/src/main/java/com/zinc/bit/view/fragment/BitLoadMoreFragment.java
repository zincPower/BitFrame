package com.zinc.bit.view.fragment;

import android.support.v7.widget.RecyclerView;

import com.zinc.jrecycleview.adapter.JRefreshAndLoadMoreAdapter;

/**
 * author       : Jiang zinc
 * time         : 2019/3/26 下午3:15
 * email        : 56002982@qq.com
 * desc         : 带上拉加载和刷新的fragment
 * version      : 1.0.0
 */
public abstract class BitLoadMoreFragment extends BitListFragment {

    protected void initAdapterForChild(RecyclerView.Adapter adapter) {

        this.mAdapter.setIsOpenLoadMore(requestLoadMore());

        if (requestLoadMore()) {
            this.mAdapter.setOnLoadMoreListener(new JRefreshAndLoadMoreAdapter.OnLoadMoreListener() {
                @Override
                public void onLoading() {
                    getLoadMoreData();
                }
            });
        }
    }

    /**
     * 是否需要刷新
     *
     * @return true: 需要；false: 不需要
     */
    @Override
    protected boolean requestRefresh() {
        return true;
    }

    /**
     * 是否需要加载更多
     *
     * @return true: 需要；false: 不需要
     */
    protected boolean requestLoadMore() {
        return true;
    }

    /**
     * 获取更多的数据
     */
    public abstract void getLoadMoreData();

}
