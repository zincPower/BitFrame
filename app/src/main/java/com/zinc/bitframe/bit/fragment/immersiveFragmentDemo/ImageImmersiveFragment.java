package com.zinc.bitframe.bit.fragment.immersiveFragmentDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zinc.bit.view.fragment.BitBaseFragment;
import com.zinc.bitframe.R;

/**
 * @author Jiang zinc
 * @date 创建时间：2018/12/3
 * @description
 */
public class ImageImmersiveFragment extends BitBaseFragment {

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_immersive, container, false);
    }

    @Override
    protected void initView(View view) {

    }

    public static Fragment newInstance() {
        return new ImageImmersiveFragment();
    }
}
