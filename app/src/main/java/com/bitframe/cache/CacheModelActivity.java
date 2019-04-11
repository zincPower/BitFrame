package com.bitframe.cache;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.bit.view.activity.BitBaseActivity;
import com.bitframe.R;

import java.util.Random;

/**
 * author       : zinc
 * time         : 2019/3/27 下午6:01
 * desc         :
 * version      :
 */
public class CacheModelActivity extends BitBaseActivity {

    Random random = new Random();

    @Override
    protected int getLayout() {
        return R.layout.activity_cache_model_test;
    }

    @Override
    protected void initIntent(Intent intent) {

    }

    @Override
    protected void initView() {

    }

    public void onSave(View view) {
        UserCache.save(new User("zinc", random.nextInt(10_000)));
    }

    public void onRead(View view) {

        Log.i(TAG, "onRead: " + UserCache.getDefault().toString());

    }
}
