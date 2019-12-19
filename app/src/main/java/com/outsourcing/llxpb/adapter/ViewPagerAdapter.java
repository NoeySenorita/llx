package com.outsourcing.llxpb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/3/12 0012.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private String[] titles;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        this(fm, fragments);
        this.titles = titles;
    }

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);

    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {//选择性实现
        if (titles != null && titles.length == mFragments.size()) {
            return titles[position];
        }
        return mFragments.get(position).getClass().getSimpleName();
    }

    public void clearFragments() {
        if (mFragments != null) {
            mFragments.clear();
            mFragments = null;
        }
    }

}
