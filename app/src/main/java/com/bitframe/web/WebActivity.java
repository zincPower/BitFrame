package com.bitframe.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bit.config.BitManager;
import com.bit.view.activity.BitBaseActivity;
import com.bit.web.BitWebViewFragment;
import com.bitframe.R;

/**
 * @author Jiang zinc
 * @date 创建时间：2018/5/9
 * @description
 */

public class WebActivity extends BitBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BitManager.getInstance().setIsDebug(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.bit_common_frame_layout;
    }

    @Override
    protected void initIntent(Intent intent) {

    }

    @Override
    protected void initView() {
        addLayerFragment(R.id.frame_layout_container,
                BitWebViewFragment.newInstance("https://www.baidu.com"));
    }

}
