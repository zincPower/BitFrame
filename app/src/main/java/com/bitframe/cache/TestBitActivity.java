package com.bitframe.cache;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bit.cache.AppBitBaseCache;
import com.bit.cache.BitBaseCache;
import com.bit.view.activity.BitBaseActivity;
import com.bitframe.R;
import com.zinc.jrecycleview.JRecycleView;

/**
 * author       : zinc
 * time         : 2019/3/27 下午6:01
 * desc         :
 * version      :
 */
public class TestBitActivity extends BitBaseActivity implements View.OnClickListener {

    private TextView tvSave;
    private TextView tvRead;
    private TextView tvRemove;
    private TextView tvClear;

    @Override
    protected int getLayout() {
        return R.layout.activity_bit_cache_test;
    }

    @Override
    protected void initIntent(Intent intent) {
    }

    @Override
    protected void initView() {
        tvSave = findViewById(R.id.tv_save);
        tvRead = findViewById(R.id.tv_read);
        tvRemove = findViewById(R.id.tv_remove);
        tvClear = findViewById(R.id.tv_clear);

        tvSave.setOnClickListener(this);
        tvRead.setOnClickListener(this);
        tvRemove.setOnClickListener(this);
        tvClear.setOnClickListener(this);
    }

    private EditText getEtKey() {
        return (EditText) findViewById(R.id.et_key);
    }

    private EditText getEtValue() {
        return (EditText) findViewById(R.id.et_value);
    }

    private EditText getEtTime() {
        return (EditText) findViewById(R.id.et_time);
    }

    @Override
    public void onClick(View v) {

        String key = getEtKey().getText().toString();
        String value = getEtValue().getText().toString();
        String time = getEtTime().getText().toString();

        long validTime = -1;
        if (!TextUtils.isEmpty(time)) {
            validTime = Long.parseLong(time);
        }

        BitBaseCache cache = TestBitBaseCache.getInstance();

        switch (v.getId()) {
            case R.id.tv_save:
                if (validTime == -1) {
                    cache.setCache(key, value);
                } else {
                    cache.setCache(key, value, validTime);
                }
                break;

            case R.id.tv_read:
                String s = cache.getString(key);
                Log.i(TAG, key + ":" + s);
                break;

            case R.id.tv_remove:
                cache.remove(key);
                break;

            case R.id.tv_clear:
                cache.clear();
                break;
        }
    }
}
