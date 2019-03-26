package com.zinc.bit.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zinc.bit.adapter.BitFrameAdapter;
import com.zinc.bit.callback.IStateListener;
import com.zinc.jrecycleview.JRecycleView;
import com.zinc.jrecycleview.adapter.JRefreshAndLoadMoreAdapter;

/**
 * author       : Jiang zinc
 * time         : 2019/3/26 下午2:40
 * email        : 56002982@qq.com
 * desc         : 带刷新的ListFragment
 * version      : 1.0.0
 */
public abstract class BitListFragment extends BitLazyFragment implements IStateListener {

    protected JRecycleView mRecycleView;

    /**
     * 包装adapter
     */
    protected BitFrameAdapter mAdapter;

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater,
                                        @Nullable ViewGroup container,
                                        @Nullable Bundle savedInstanceState) {
        return inflater.inflate(COMMON_RECYCLE_VIEW_LAYOUT, container, false);
    }

    @Override
    protected void initView(View view) {
        this.mRecycleView = view.findViewById(ID_RECYCLE_VIEW);
        this.initRecycleView(this.mRecycleView);
        this.setAdapter(getAdapter());
    }

    protected void initRecycleView(JRecycleView recycleView) {
        recycleView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        // 是否添加分割线
        if (isShowDivider()) {
            recycleView.addItemDecoration(getItemDecoration());
        }

        // 是否添加动画
        recycleView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * @param adapter
     */
    private void setAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = new BitFrameAdapter(getContext(), adapter);

        this.mAdapter.setIsOpenRefresh(requestRefresh());
        if (requestRefresh()) {
            this.mAdapter.setOnRefreshListener(new JRefreshAndLoadMoreAdapter.OnRefreshListener() {
                @Override
                public void onRefreshing() {
                    BitListFragment.this.getFirstData();
                }
            });
        }

        this.mAdapter.setIsOpenLoadMore(false);
        this.mAdapter.setStateListener(this);

        this.initAdapterForChild(adapter);

        this.mRecycleView.setAdapter(this.mAdapter);
    }

    /**
     * 子View 可以加强 adapter 的功能
     *
     * @param adapter 用户传入的 Adapter
     */
    protected void initAdapterForChild(RecyclerView.Adapter adapter) {
    }

    /**
     * 获取用户的Adapter，因为我们这里会进行包装
     *
     * @return 用户的Adapter
     */
    protected abstract RecyclerView.Adapter getAdapter();

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        this.mAdapter.onLoading();
        this.getFirstData();
    }

    /**
     * 是否已经初始化，如果当前Fragment 未加载成功，则为false；
     * 如果已经加载成功，则返回true
     *
     * @return true: 已经初始化；false: 未初始化
     */
    @Override
    protected boolean hasInitialized() {
        if (this.mAdapter == null) {
            return false;
        }
        return !this.mAdapter.isState();
    }

    /**
     * 加载时调用
     */
    @Override
    public void onLoading() {
        super.logw("已经正在刷新......");
    }

    /**
     * 为空时调用
     */
    @Override
    public void onEmpty() {
        getFirstData();
    }

    /**
     * 重试时回调
     */
    @Override
    public void onRetry() {
        getFirstData();
    }

    /**
     * 第一次获取数据
     */
    public abstract void getFirstData();

    /**
     * 是否需要下拉刷新功能
     *
     * @return true(默认)：需要刷新功能，false：不需要刷新功能
     */
    protected boolean requestRefresh() {
        return true;
    }

    /**
     * 是否需要分割线
     *
     * @return false(默认): 不需要；true: 需要
     */
    protected boolean isShowDivider() {
        return false;
    }

    /**
     * 获取 item 的修饰项
     *
     * @return 默认返回分割线
     */
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(this.getActivity(), 1);
    }

}
