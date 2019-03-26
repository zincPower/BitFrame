package com.bit.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bit.R;
import com.bit.config.BitManager;
import com.bit.utils.FragmentCompat;
import com.bit.widget.StateLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * author       : Jiang zinc
 * time         : 2019/3/25 下午5:47
 * email        : 56002982@qq.com
 * desc         : 基础的 Fragment，应用内的Fragment 都应该直接或间接的继承至 该Fragment
 * version      : 1.0.0
 */
public abstract class BitBaseFragment extends Fragment {

    /**
     * 装载 FrameLayout 的布局容器
     */
    protected final static int COMMON_FRAME_LAYOUT = R.layout.bit_common_frame_layout;
    /**
     * 装载 JRecycleView 的布局容器
     */
    protected final static int COMMON_RECYCLE_VIEW_LAYOUT = R.layout.bit_common_recycle_view;
    /**
     * 装载 FrameLayout 的布局容器中的 FrameLayout 的 ID
     */
    protected final static int ID_FRAME_LAYOUT_CONTAINER = R.id.frame_layout_container;
    /**
     * 装载 JRecycleView 的布局容器中的 JRecycleView 的 ID
     */
    protected final static int ID_RECYCLE_VIEW = R.id.recycle_view;

    protected Bundle mSaveInstanceState;

    @Override
    public void onAttach(Context context) {

        this.logi("onAttach------start");

        super.onAttach(context);

        Bundle arguments = getArguments();
        if (arguments != null) {
            this.initArgs(arguments);
        }

        this.logi("onAttach------end");
    }

    /**
     * 初始化参数
     */
    protected void initArgs(Bundle arguments) {

    }

    /**
     * {@link BitBaseFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)} 与其相同
     * 只是用于父类JFragment中包装
     */
    protected abstract View onCreateFragmentView(LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState);

    /**
     * 初始化 Fragment 的控件
     *
     * @param view 布局
     */
    protected abstract void initView(View view);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.logi("onCreate------start");

        super.onCreate(savedInstanceState);

        this.mSaveInstanceState = savedInstanceState;

        this.logi("onCreate------end");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.logi("onCreateView------start");

        View fragmentView = onCreateFragmentView(inflater, container, savedInstanceState);

        this.logi("onCreateView------end");
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.logi("onViewCreated------start");
        super.onViewCreated(view, savedInstanceState);
        this.initView(view);
        this.logi("onViewCreated------end");
    }

    /**
     * 包装fragment，添加多状态：重试、空、加载
     *
     * @param view 需要被包装的 View
     * @return 拥有多状态的 View
     */
    protected View wrapFragmentView(View view) {
        StateLayout stateLayout = new StateLayout(getActivity());
        stateLayout.setContentView(view);
        stateLayout.setRetryView(BitManager.getInstance().getRetryViewLayout());
        stateLayout.setEmptyView(BitManager.getInstance().getEmptyViewLayout());
        stateLayout.setLoadingView(BitManager.getInstance().getLoadingViewLayout());
        return stateLayout;
    }

    /**
     * 获取 Fragment 的 TAG
     *
     * @return 返回TAG
     */
    public String getFragmentTag() {
        return getClass().getSimpleName() + "{" + getUniqueId() + "}";
    }

    /**
     * 判断当前 Fragment 是否消费回退事件
     *
     * @param fragmentManager 上级fragment 的管理
     * @return
     */
    public boolean onConsumeBackEvent(FragmentManager fragmentManager) {
        boolean consume = FragmentCompat.isConsumeBackEvent(getChildFragmentManager());
        if (consume) {
            // 子fragment消费成功以后，判断当前fragment是否还有子fragment，没有的话，看情况决定是否关闭
            if (getChildFragmentManager().getBackStackEntryCount() == 0 && noChild2Finish()) {
                return fragmentManager.popBackStackImmediate();
            }
            return true;
        } else {
            // 子fragment未消费回退事件,则由当前fragment进行消费
            return fragmentManager.popBackStackImmediate();
        }
    }

    /**
     * 当 fragmentmanager 内的子fragment数为0时，判断是否关闭当前fragment
     *
     * @return
     */
    protected boolean noChild2Finish() {
        return true;
    }

    /**
     * 添加一个 Fragment
     *
     * @param containerId 容器的ID
     * @param fragment    需要显示的Fragment
     */
    protected void addLayerFragment(int containerId, Fragment fragment) {
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
    protected void addLayerFragment(int containerId, List<Fragment> fragments) {
        addLayerFragment(containerId, fragments, 0);
    }

    /**
     * 添加一个列表的 Fragment，并且显示列表的指定下标的 Fragment
     *
     * @param containerId  容器的ID
     * @param fragments    加入容器的 Fragment 列表
     * @param showPosition 显示的下标
     */
    protected void addLayerFragment(int containerId, List<Fragment> fragments, int showPosition) {
        if (this.mSaveInstanceState == null) {
            FragmentCompat.Layer.init(getChildFragmentManager(), containerId, showPosition, fragments);
        } else {
            FragmentCompat.Layer.restoreInstance(getChildFragmentManager(), fragments);
        }
    }

    /**
     * 切换fragment
     *
     * @param from 当前显示的 Fragment
     * @param to   要切换至的 Fragment
     */
    protected void toggleLayerFragment(Fragment from, Fragment to) {
        FragmentCompat.Layer.toggle(getChildFragmentManager(), from, to);
    }

    /**
     * 获取该 Fragment 唯一的 id 值
     *
     * @return Fragment 唯一的id
     */
    private String getUniqueId() {
        return Integer.toHexString(System.identityHashCode(this));
    }

    /**
     * 打印fragment的信息，主要用于调试
     *
     * @param fragmentManager
     */
    protected void printFragmentLog(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                Log.e("fragment_list", fragment != null ? fragment.toString() + ",isHidden:" + fragment.isHidden() : "null");
            }
        }
    }

    // ============================= 生命周期输出 用于调试 start=====================================
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        this.logi("onActivityCreated------start");
        super.onActivityCreated(savedInstanceState);
        this.logi("onActivityCreated------end");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        this.logi("onViewStateRestored------start");
        super.onViewStateRestored(savedInstanceState);
        this.logi("onViewStateRestored------end");
    }

    @Override
    public void onStart() {
        this.logi("onStart------start");
        super.onStart();
        this.logi("onStart------end");
    }

    @Override
    public void onResume() {
        this.logi("onResume------start");
        super.onResume();
        this.logi("onResume------end");
    }

    @Override
    public void onPause() {
        this.logi("onPause------start");
        super.onPause();
        this.logi("onPause------end");
    }

    @Override
    public void onStop() {
        this.logi("onStop------start");
        super.onStop();
        this.logi("onStop------end");
    }

    @Override
    public void onDestroyView() {
        this.logi("onDestroyView------start");
        super.onDestroyView();
        this.logi("onDestroyView------end");
    }

    @Override
    public void onDestroy() {
        this.logi("onDestroy------start");
        super.onDestroy();
        this.logi("onDestroy------end");
    }

    @Override
    public void onDetach() {
        this.logi("onDetach------start");
        super.onDetach();
        this.logi("onDetach------end");
    }
    // ============================== 生命周期输出 用于调试 end=======================================

    // ============================== 日志输出 start=======================================
    protected void logi(String msg) {
        if (BitManager.getInstance().isDebug()) {
            Log.i(getFragmentTag(), msg);
        }
    }

    protected void logw(String msg) {
        if (BitManager.getInstance().isDebug()) {
            Log.e(getFragmentTag(), msg);
        }
    }

    protected void loge(String msg) {
        if (BitManager.getInstance().isDebug()) {
            Log.e(getFragmentTag(), msg);
        }
    }
    // ============================== 日志输出 end=========================================

}
