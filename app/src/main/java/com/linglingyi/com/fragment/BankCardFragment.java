package com.linglingyi.com.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.BankFragPagerAdapter;
import com.linglingyi.com.base.BaseFragment;
import com.linglingyi.com.utils.CommonUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/14
 */
public class BankCardFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tl_tab)
    TabLayout tlTab;
    @BindView(R.id.vp_child_card)
    ViewPager vpChildCard;
    Unbinder unbinder;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private BankFragPagerAdapter mPagerAdapter;
    private SparseArray<String> mSparseArray = new SparseArray<>();

    public static BankCardFragment newInstance() {
        return new BankCardFragment();
    }

    @Override
    public int initLayout() {
        return R.layout.frag_bank_card;
    }

    @Override
    public void initData(View v) {
        tvTitle.setText("智能还款");
        ivBack.setVisibility(View.GONE);

        mSparseArray.put(0, "信用卡");
        mSparseArray.put(1, "储蓄卡");

        BankCreditListFrament creditFragment = BankCreditListFrament.newInstance();
        mFragmentList.add(creditFragment);
        BankNormalCardFragment cardFragment = BankNormalCardFragment.newInstance();
        mFragmentList.add(cardFragment);
        mPagerAdapter = new BankFragPagerAdapter(getChildFragmentManager(), mFragmentList);
        mPagerAdapter.setTitleArray(mSparseArray);
        vpChildCard.setAdapter(mPagerAdapter);
        tlTab.setupWithViewPager(vpChildCard);
        setTabLine(40, 40);
    }

    public void setTabLine(int left, int right) {
        try {
            Class<?> tablayout = tlTab.getClass();
            Field tabStrip = tablayout.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout ll_tab = (LinearLayout) tabStrip.get(tlTab);
            for (int i = 0; i < ll_tab.getChildCount(); i++) {
                View child = ll_tab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                //修改两个tab的间距
                params.setMarginStart(CommonUtils.dp2px(context, left));
                params.setMarginEnd(CommonUtils.dp2px(context, right));
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
//            if (mFragmentList.get(0).isAdded()) {
//                ((BankCreditListFrament) mFragmentList.get(0)).refreshData();
//            }
        }
    }

}
