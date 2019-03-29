package com.bitframe.cache;

import com.bit.cache.BitBaseCache;

/**
 * author       : zinc
 * time         : 2019/3/28 下午5:53
 * desc         :
 * version      :
 */
public class TestBitBaseCache extends BitBaseCache {

    private static volatile TestBitBaseCache instance = null;

    private TestBitBaseCache(){
    }

    public static TestBitBaseCache getInstance(){

        if(instance == null){
            synchronized(TestBitBaseCache.class){
                if(instance == null){
                    instance = new TestBitBaseCache();
                }
            }
        }

        return instance;

    }

    @Override
    protected String getSpName() {
        return "TestCache";
    }

}
