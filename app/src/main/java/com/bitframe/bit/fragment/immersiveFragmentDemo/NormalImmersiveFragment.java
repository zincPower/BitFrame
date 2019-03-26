package com.bitframe.bit.fragment.immersiveFragmentDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bit.view.fragment.BitBaseFragment;
import com.bitframe.R;

/**
 * @author Jiang zinc
 * @date 创建时间：2018/12/3
 * @description
 */
public class NormalImmersiveFragment extends BitBaseFragment {

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_normal_immersive, container, false);
    }

    @Override
    protected void initView(View view) {

    }

    public static Fragment newInstance() {
        return new NormalImmersiveFragment();
    }
}
