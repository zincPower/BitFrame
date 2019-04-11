package com.bitframe.cache;

import com.bit.cache.AppCache;
import com.zinc.bit_creator.CacheAutoCreator;

import java.io.IOException;

/**
 * author       : zinc
 * time         : 2019/3/29 下午5:31
 * desc         :
 * version      :
 */
public class CreateModelCacheMain {

    public static void main(String[] args) throws IOException {
        CacheAutoCreator.Builder builder = new CacheAutoCreator.Builder(AppCache.class);

        builder.setModelClazz(User.class);
        builder.setPkgPath("com.bit.cache");
        builder.setSavePath("app/src/main/java/cacheArea");

        builder.build().create();
    }

}
