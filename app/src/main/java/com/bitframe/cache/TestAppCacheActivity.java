package com.bitframe.cache;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bit.cache.AppCache;
import com.bit.view.activity.BitBaseActivity;
import com.bitframe.R;

/**
 * author       : zinc
 * time         : 2019/3/28 下午6:16
 * desc         :
 * version      :
 */
public class TestAppCacheActivity extends BitBaseActivity implements View.OnClickListener {

    private TextView tvSave;
    private TextView tvRead;
    private TextView tvRemove;
    private TextView tvClear;

    @Override
    protected int getLayout() {
        return R.layout.activity_app_cache_test;
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

    private EditText getEtFileName() {
        return (EditText) findViewById(R.id.et_file_name);
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

        String fileName = getEtFileName().getText().toString();
        String key = getEtKey().getText().toString();
        String value = getEtValue().getText().toString();
        String time = getEtTime().getText().toString();

        long validTime = -1;
        if (!TextUtils.isEmpty(time)) {
            validTime = Long.parseLong(time);
        }

        AppCache cache;
        if (TextUtils.isEmpty(fileName)) {
            cache = AppCache.getInstance();
        } else {
            cache = AppCache.getInstance(fileName);
        }

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
