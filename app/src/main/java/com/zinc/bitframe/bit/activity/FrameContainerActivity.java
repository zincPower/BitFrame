package com.zinc.bitframe.bit.activity;

import android.content.Intent;

import com.zinc.bit.view.activity.BitBaseActivity;
import com.zinc.bitframe.bit.fragment.JBaseFragmentDemo.OrdinaryTestFragment;
import com.zinc.bitframe.bit.fragment.JListFragmentDemo.ListTestFragment;
import com.zinc.bitframe.bit.fragment.JLoadMoreFragmentDemo.LoadMoreTestFragment;

/**
 * @author Jiang zinc
 * @date 创建时间：2018/4/22
 * @description
 */

public class FrameContainerActivity extends BitBaseActivity {

    private String type;

    @Override
    protected int getLayout() {
        return COMMON_FRAME_LAYOUT;
    }

    @Override
    protected void initView() {
        switch (type){
            case "ordinary":
                addLayerFragment(ID_FRAME_LAYOUT_CONTAINER, new OrdinaryTestFragment());
                break;
            case "lazy":

                break;
            case "list":
                addLayerFragment(ID_FRAME_LAYOUT_CONTAINER, new ListTestFragment());
                break;
            case "loadMore":
                addLayerFragment(ID_FRAME_LAYOUT_CONTAINER, LoadMoreTestFragment.newInstance(0, true));
                break;
        }
    }

    @Override
    protected void initIntent(Intent intent) {
        type = intent.getStringExtra("type");
    }

}
