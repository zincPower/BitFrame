package com.zinc.bit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zinc.bit.callback.IStateListener;
import com.zinc.bit.config.BitManager;
import com.zinc.jrecycleview.adapter.JRefreshAndLoadMoreAdapter;

/**
 * author       : Jiang zinc
 * time         : 2019/3/26 下午3:04
 * email        : 56002982@qq.com
 * desc         : 上拉加载更多 和 下拉刷新的适配器
 * version      : 1.0.0
 */
public class BitFrameAdapter extends JRefreshAndLoadMoreAdapter {

    /**
     * 重试类型
     */
    private final static int RETRY_TYPE = 0xABC201;
    /**
     * 加载类型
     */
    private final static int LOADING_TYPE = 0xABC202;
    /**
     * 空类型
     */
    private final static int EMPTY_TYPE = 0xABC203;
    /**
     * 成功类型
     */
    private final static int SUCCESS_TYPE = 0xABC204;

    /**
     * 当前类型
     */
    private int mCurrentType = LOADING_TYPE;

    private LayoutInflater mLayoutInflater;

    /**
     * 状态监听器
     */
    private IStateListener mStateListener;

    public BitFrameAdapter(Context context, RecyclerView.Adapter adapter) {
        super(context, adapter);
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case RETRY_TYPE:
                return new RetryViewHolder(mLayoutInflater
                        .inflate(BitManager.getInstance().getRetryViewLayout(),
                                parent,
                                false));
            case LOADING_TYPE:
                return new LoadingViewHolder(mLayoutInflater
                        .inflate(BitManager.getInstance().getLoadingViewLayout(),
                                parent,
                                false));
            case EMPTY_TYPE:
                return new EmptyViewHolder(mLayoutInflater
                        .inflate(BitManager.getInstance().getEmptyViewLayout(),
                                parent,
                                false));
        }

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (this.mStateListener == null) {
            super.onBindViewHolder(holder, position);
            return;
        }

        if (holder instanceof RetryViewHolder) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2019/3/26 这里可以优化
                    BitFrameAdapter.this.mCurrentType = LOADING_TYPE;
                    BitFrameAdapter.super.notifyDataSetChanged();
                    BitFrameAdapter.this.mStateListener.onRetry();
                }
            });

        } else if (holder instanceof LoadingViewHolder) {

            this.mStateListener.onLoading();

        } else if (holder instanceof EmptyViewHolder) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2019/3/26 这里可以优化
                    BitFrameAdapter.this.mCurrentType = LOADING_TYPE;
                    BitFrameAdapter.super.notifyDataSetChanged();
                    BitFrameAdapter.this.mStateListener.onEmpty();
                }
            });

        } else {

            super.onBindViewHolder(holder, position);

        }
    }

    @Override
    public int getItemCount() {
        if (isState()) {
            return 1;
        }
        return super.getItemCount();
    }

    public void setStateListener(IStateListener mStateListener) {
        this.mStateListener = mStateListener;
    }

    @Override
    public int getItemViewType(int position) {

        if (this.mCurrentType == SUCCESS_TYPE) {
            return super.getItemViewType(position);
        } else {
            return this.mCurrentType;
        }

    }

    //是否处在loading、retry、empty状态
    public boolean isState() {
        return this.mCurrentType != SUCCESS_TYPE;
    }

    /**
     * 设置为加载状态
     */
    public void onLoading() {
        this.mCurrentType = LOADING_TYPE;
    }

    public void onSuccess() {
        super.setRefreshComplete();
        this.mCurrentType = SUCCESS_TYPE;
        this.notifyDataSetChanged();
    }

    public void onError() {
        this.mCurrentType = RETRY_TYPE;
        super.notifyDataSetChanged();
    }

    public void onEmpty() {
        this.mCurrentType = EMPTY_TYPE;
        super.notifyDataSetChanged();
    }

    /**
     * 重试视图
     */
    class RetryViewHolder extends RecyclerView.ViewHolder {

        RetryViewHolder(View itemView) {
            super(itemView);
        }

    }

    /**
     * 加载视图
     */
    class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 空视图
     */
    class EmptyViewHolder extends RecyclerView.ViewHolder {

        EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
