package com.linglingyi.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.linglingyi.com.MyApplication;
import com.linglingyi.com.activity.ChangeAuthBankCardActivity;
import com.linglingyi.com.base.BaseFragment;
import com.linglingyi.com.event.BankChangeEvent;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.Utils;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class BankNormalCardFragment extends BaseFragment {

    @BindView(R.id.iv_bank_icon)
    ImageView ivBankIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_bank_account)
    TextView tvBankAccount;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.btn_change)
    Button btnChange;
    Unbinder unbinder;


    public static BankNormalCardFragment newInstance() {
        return new BankNormalCardFragment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int initLayout() {
        return R.layout.frag_bank_normal_card;
    }

    @Override
    public void initData(View v) {
        EventBus.getDefault().register(this);
        updateData();
    }

    private void updateData() {

        String bankAccount = StorageCustomerInfo02Util.getInfo("bankAccount",
                context);
        String bankNum2 = "", bankNum1 = "";
        if (bankAccount.length() > 4) {
            bankNum1 = bankAccount.substring(0, 4);
            bankNum2 = bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
        }
        tvBankAccount.setText(String.format("**** **** **** %s", bankNum2));
        String bankDetail = StorageCustomerInfo02Util.getInfo("bankDetail", context);
        Utils.initBankCodeColorIcon(MyApplication.bankNameList.get(bankDetail), ivBankIcon, context);
        tvBankName.setText(bankDetail + "储蓄卡");

    }


    /**
     * 绑卡成功后，自动刷新数据
     *
     * @param event
     */
    @Subscribe
    public void onEvent(BankChangeEvent event) {

        updateData();
    }


    @OnClick(R.id.btn_change)
    public void onViewClicked() {
        startActivity(new Intent(context, ChangeAuthBankCardActivity.class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
