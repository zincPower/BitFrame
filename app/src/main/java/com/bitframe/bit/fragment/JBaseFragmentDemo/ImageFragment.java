package com.bitframe.bit.fragment.JBaseFragmentDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bit.view.fragment.BitBaseFragment;
import com.bitframe.R;

/**
 * @author Jiang zinc
 * @date 创建时间：2018/4/22
 * @description
 */

public class ImageFragment extends BitBaseFragment {

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    protected void initView(View view) {

    }

}
