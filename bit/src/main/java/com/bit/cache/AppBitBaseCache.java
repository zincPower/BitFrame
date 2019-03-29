package com.bit.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * author       : zinc
 * time         : 2019/3/27 下午5:55
 * desc         :
 * version      :
 */
public class AppBitBaseCache extends BitBaseCache {

    private static volatile Map<String, AppBitBaseCache> CACHE_MAP = new HashMap<>();
    private static volatile AppBitBaseCache instance;

    private AppBitBaseCache(String name) {
        super(name);
    }

    private AppBitBaseCache() {
        super(null);
    }

    public static AppBitBaseCache getInstance(String name) {

        AppBitBaseCache cache = CACHE_MAP.get(name);

        if (cache == null) {
            synchronized (AppBitBaseCache.class) {
                cache = CACHE_MAP.get(name);
                if (cache == null) {
                    cache = new AppBitBaseCache(name);
                    CACHE_MAP.put(name, cache);
                }
            }
        }

        return cache;

    }

    public static AppBitBaseCache getInstance() {

        if (instance == null) {
            synchronized (AppBitBaseCache.class) {
                if (instance == null) {
                    instance = new AppBitBaseCache();
                }
            }
        }

        return instance;

    }

    @Override
    protected String getSpName() {
        return APP_CONTEXT.getPackageName() + "_preferences";
    }

}
