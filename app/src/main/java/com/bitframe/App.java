package com.bitframe;

import android.app.Application;

import com.bit.cache.AppBitBaseCache;

/**
 * author       : zinc
 * time         : 2019/3/28 下午5:13
 * desc         :
 * version      :
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppBitBaseCache.init(this);
    }

}
