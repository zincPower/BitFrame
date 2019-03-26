package com.zinc.bit.web;

import java.lang.ref.WeakReference;

/**
 * author       : Jiang zinc
 * time         : 2019/3/26 下午3:36
 * email        : 56002982@qq.com
 * desc         : 
 * version      : 1.0.0
 */

public class IWebViewJavaScriptInterface {

    protected WeakReference<BitWebViewFragment> mFragment;

    public BitWebViewFragment getWebViewFragment() {
        return this.mFragment.get();
    }

    public void setWebViewFragment(BitWebViewFragment fragment) {
        this.mFragment = new WeakReference<BitWebViewFragment>(fragment);
    }

}
