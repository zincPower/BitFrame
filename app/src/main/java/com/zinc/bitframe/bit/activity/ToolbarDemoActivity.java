package com.zinc.bitframe.bit.activity;

import android.content.Intent;

import com.zinc.bit.view.activity.BitToolbarActivity;
import com.zinc.bitframe.R;
import com.zinc.bitframe.bit.fragment.JLoadMoreFragmentDemo.LoadMoreTestFragment;

/**
 * @author Jiang zinc
 * @date 创建时间：2018/4/23
 * @description
 */

public class ToolbarDemoActivity extends BitToolbarActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_toolbar_demo;
    }

    @Override
    protected void initIntent(Intent intent) {

    }

    @Override
    protected void initView() {
        //设置标题
        setTitle("zincPower");

        //隐藏回退按钮
//        hideHomeBtn();

        addLayerFragment(R.id.frame_layout_container, LoadMoreTestFragment.newInstance(0, true));
    }

}
