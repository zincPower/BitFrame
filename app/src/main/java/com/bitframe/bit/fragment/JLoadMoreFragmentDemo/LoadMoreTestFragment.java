package com.bitframe.bit.fragment.JLoadMoreFragmentDemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import com.bit.view.fragment.BitLoadMoreFragment;
import com.bitframe.bit.adapter.MyListAdapter;
import com.bitframe.bit.fragment.MyHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang zinc
 * @date 创建时间：2018/4/22
 * @description
 */

public class LoadMoreTestFragment extends BitLoadMoreFragment {

    private List data = new ArrayList();
    private int count = 1;
    private int mLength = 20;

    private int position;
    private boolean checked;

    private Handler mHandler = new MyHandler(this);

    public static LoadMoreTestFragment newInstance(int position, boolean isLazy) {
        LoadMoreTestFragment loadMoreTestFragment = new LoadMoreTestFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putBoolean("checked", isLazy);

        loadMoreTestFragment.initArgs(bundle);
        return loadMoreTestFragment;
    }

    @Override
    protected void initArgs(Bundle arguments) {
        position = arguments.getInt("position");
        checked = arguments.getBoolean("checked");
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new MyListAdapter(getContext(), data);
    }

    @Override
    public void getFirstData() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getInitData();
                mAdapter.onSuccess();
            }
        }, 2000);
    }

    @Override
    protected boolean isLazyLoad() {
        return checked;
    }

    @Override
    protected boolean requestLoadMore() {
        //是否需要加载更多
        return true;
    }

    @Override
    public void getLoadMoreData() {
        for (int i = 0; i < 20; ++i) {
            data.add(position + " zinc Power" + (count + i));
        }

        count = count + 20;

        mAdapter.setLoadComplete();
        mAdapter.notifyDataSetChanged();
    }

    public void getInitData() {
        this.data.clear();
        count = 1;
        for (; count <= mLength; ++count) {
            data.add(position + " zinc Power" + count);
        }
    }

}
