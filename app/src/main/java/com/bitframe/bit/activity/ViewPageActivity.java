package com.bitframe.bit.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.bit.view.activity.BitBaseActivity;
import com.bitframe.R;
import com.bitframe.bit.fragment.JLazyFragmentDemo.LazyTestFragment;
import com.bitframe.bit.fragment.JLoadMoreFragmentDemo.LoadMoreTestFragment;

/**
 * @author Jiang zinc
 * @date 创建时间：2018/4/22
 * @description
 */

public class ViewPageActivity extends BitBaseActivity {

    private ViewPager viewPager;

    private boolean checked;
    private String type;

    @Override
    protected int getLayout() {
        return R.layout.activity_view_page;
    }

    @Override
    protected void initIntent(Intent intent) {
        checked = intent.getBooleanExtra("checked", false);
        type = intent.getStringExtra("type");
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
                switch (type) {
                    case "lazy":
                        return LazyTestFragment.newInstance(position, checked);
                    case "tv_load_more_view_pager":
                        return LoadMoreTestFragment.newInstance(position, checked);
                    default:
                        return null;
                }
            }


        });

    }
}
