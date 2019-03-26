package com.bit.view.fragment.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.bit.R;

/**
 * author       : Jiang zinc
 * time         : 2019/3/26 下午3:28
 * email        : 56002982@qq.com
 * desc         : loading 对话框
 * version      : 1.0.0
 */
public class BitCommonLoadingFragment extends BitBaseDialogFragment {

    public static BitCommonLoadingFragment newInstance() {
        BitCommonLoadingFragment fragment = new BitCommonLoadingFragment();

        Bundle bundle = new Bundle();

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initArgs(Bundle arguments) {

    }

    @Override
    protected void initWindows(Window window) {
        //空实现，去除父类动画效果
    }

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bit_fragment_loading, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView(View view) {

    }

}
