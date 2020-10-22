package com.rcdz.medianewsapp.model.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/13 14:51
 */
public class MainViewPageAdapter extends FragmentStatePagerAdapter {
//    public MainViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
//        super(fm, behavior);
//    }

    private List<Fragment> fragments;

    public MainViewPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
