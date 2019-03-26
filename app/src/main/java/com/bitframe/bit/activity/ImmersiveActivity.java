package com.bitframe.bit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.bit.view.activity.BitBaseActivity;
import com.bitframe.R;
import com.bitframe.bit.fragment.immersiveFragmentDemo.FitStateUI;
import com.bitframe.bit.fragment.immersiveFragmentDemo.ImageImmersiveFragment;
import com.bitframe.bit.fragment.immersiveFragmentDemo.NormalImmersiveFragment;

/**
 * @author Jiang zinc
 * @date 创建时间：2018/12/3
 * @description
 */
public class ImmersiveActivity extends BitBaseActivity {

    private ViewPager viewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_immersive;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        FitStateUI.setImmersionStateMode(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initIntent(Intent intent) {
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(5);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Fragment getItem(int position) {
                if (position % 2 == 0) {
                    return ImageImmersiveFragment.newInstance();
                } else {
                    return NormalImmersiveFragment.newInstance();
                }
            }


        });

    }
}
