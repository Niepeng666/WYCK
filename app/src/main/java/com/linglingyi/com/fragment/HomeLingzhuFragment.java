package com.linglingyi.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.LingzhuActivity;
import com.linglingyi.com.base.BaseFragment;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author dyx
 * @date on 2019/11/2
 * @describe
 */
public class HomeLingzhuFragment extends BaseFragment {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    Unbinder unbinder;

    public static HomeLingzhuFragment newInstance() {
        return new HomeLingzhuFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public int initLayout() {
        return R.layout.frg_home_lingzhu;
    }

    @Override
    public void initData(View v) {
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("领主");


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_agree)
    void jumpLingzhu() {
        if (StorageCustomerInfo02Util.getIntInfo(context, "lz", -1) == 0) {
            ViewUtils.makeToast(context, "暂未开放", 500);
            return;
        }
        context.startActivity(new Intent(context, LingzhuActivity.class));
    }
}
