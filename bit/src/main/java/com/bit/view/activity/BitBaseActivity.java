package com.bit.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.bit.R;
import com.bit.utils.FragmentCompat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * author       : Jiang zinc
 * time         : 2019/3/25 下午5:18
 * email        : 56002982@qq.com
 * desc         : 所有的 Activity 的基类
 * version      : 1.0.0
 */
public abstract class BitBaseActivity extends AppCompatActivity {

    /**
     * 装载 FrameLayout 的布局容器
     */
    protected final static int COMMON_FRAME_LAYOUT = R.layout.bit_common_frame_layout;
    /**
     * 装载 JRecycleView 的布局容器
     */
    protected final static int COMMON_RECYCLE_VIEW_LAYOUT = R.layout.bit_common_recycle_view;
    /**
     * 装载 FrameLayout 的布局容器中的 FrameLayout 的 id
     */
    protected final static int ID_FRAME_LAYOUT_CONTAINER = R.id.frame_layout_container;
    /**
     * 装载 JRecycleView 的布局容器中的 JRecycleView 的 id
     */
    protected final static int ID_RECYCLE_VIEW = R.id.recycle_view;

    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 当前存活的activity， 用于关闭所有的activity
     */
    private static final LinkedList<Activity> EXIST_ACTIVITIES = new LinkedList<>();

    protected Bundle mSavedInstanceState;

    /**
     * 表示无布局文件
     */
    protected static final int NONE = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSavedInstanceState = savedInstanceState;

        EXIST_ACTIVITIES.add(this);

        // 为 NONE 时，不进行设置视图的 layout
        if (getLayout() != NONE) {
            setContentView(getLayout());
        }

        initIntent(getIntent());
        initView();

    }

    @Override
    protected void onDestroy() {
        EXIST_ACTIVITIES.remove(this);
        super.onDestroy();
    }

    /**
     * 退出所有的存活 Activity
     */
    public void exit() {
        Iterator<Activity> activityIterator = EXIST_ACTIVITIES.iterator();
        while (activityIterator.hasNext()) {
            Activity next = activityIterator.next();
            activityIterator.remove();
            next.finish();
        }
    }

    /**
     * 当前activity是否为用户所见的activity
     *
     * @return true 表示当前 Activity 是最后一个 Activity；false 则相反
     */
    private boolean isCurActivity() {
        return EXIST_ACTIVITIES.getLast() == this;
    }

    /**
     * 添加一个 Fragment
     *
     * @param containerId 容器的ID
     * @param fragment    需要显示的Fragment
     */
    public void addLayerFragment(int containerId, Fragment fragment) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(fragment);
        addLayerFragment(containerId, fragments, 0);
    }

    /**
     * 添加一个列表的 Fragment，并且显示列表的第一个 Fragment
     *
     * @param containerId 容器的ID
     * @param fragments   加入容器的 Fragment 列表
     */
    public void addLayerFragment(int containerId, List<Fragment> fragments) {
        addLayerFragment(containerId, fragments, 0);
    }

    /**
     * 添加一个列表的 Fragment，并且显示列表的指定下标的 Fragment
     *
     * @param containerId  容器的ID
     * @param fragments    加入容器的 Fragment 列表
     * @param showPosition 显示的下标
     */
    public void addLayerFragment(int containerId, List<Fragment> fragments, int showPosition) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        if (mSavedInstanceState == null) {
            FragmentCompat.Layer.init(getSupportFragmentManager(), containerId, showPosition, fragments);
        } else {
            FragmentCompat.Layer.restoreInstance(getSupportFragmentManager(), fragments);
        }
    }

    /**
     * 切换fragment
     *
     * @param from 当前显示的 Fragment
     * @param to   要切换至的 Fragment
     */
    public void toggleLayerFragment(Fragment from, Fragment to) {
        FragmentCompat.Layer.toggle(getSupportFragmentManager(), from, to);
    }

    /**
     * 获取视图layout的id
     *
     * @return 布局文件，可能为 {@link BitBaseActivity#NONE} 表示 BitBaseActivity 无需设置布局
     */
    protected abstract int getLayout();

    /**
     * 初始化intent
     */
    protected abstract void initIntent(Intent intent);

    /**
     * 初始化视图的View
     */
    protected abstract void initView();


}
