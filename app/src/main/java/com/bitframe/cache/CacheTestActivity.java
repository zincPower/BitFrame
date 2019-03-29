package com.bitframe.cache;

import android.content.Intent;
import android.view.View;

import com.bit.view.activity.BitBaseActivity;
import com.bitframe.R;

/**
 * author       : zinc
 * time         : 2019/3/27 下午6:01
 * desc         :
 * version      :
 */
public class CacheTestActivity extends BitBaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_cache_test;
    }

    @Override
    protected void initIntent(Intent intent) {

    }

    @Override
    protected void initView() {

    }

    public void onAppCache(View view) {
        startActivity(new Intent(this, TestAppCacheActivity.class));
    }

    public void onBitCache(View view) {
        startActivity(new Intent(this, TestBitActivity.class));
    }
}
