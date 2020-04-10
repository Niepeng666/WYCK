package com.linglingyi.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;

import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class BankFragPagerAdapter extends MineFragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private SparseArray<String> mTitleArray;

    public BankFragPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm, fragmentList);
        mFragmentList = fragmentList;
    }

    public void setTitleArray(SparseArray<String> titleArray) {
        mTitleArray = titleArray;
    }

    @Override
    public int getCount() {
        return mFragmentList != null ? mFragmentList.size() : 0;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleArray.get(position);
    }

    public Fragment getFragment(int position) {
        return mFragmentList.get(position);
    }
}
