package com.linglingyi.com.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.viewone.dialog.TipDialog;
import com.lzy.okgo.OkGo;
//import com.squareup.leakcanary.RefWatcher;
import com.wuyouchuangke.com.R;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    public Activity context;
    public Dialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        loadingDialog = ViewUtils.createLoadingDialog(context, getString(R.string.loading_wait), true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(), null);
        ButterKnife.bind(this, view);
        initData(view);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        RefWatcher refWatcher = MyApplication.getRefWatcher(context);
//        refWatcher.watch(this);
        OkGo.getInstance().cancelTag(this);
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int initLayout();

    /**
     * 设置数据
     */
    public abstract void initData(View v);

    /**
     * 判断是否已经实名认证
     *
     * @return
     */
    public boolean checkCustomerInfoCompleteShowToast() {
        //用于判断是否进行过实名认证

        if (!checkAuth()) {
            TipDialog tipDialog = TipDialog.getInstance("是否去实名认证", "auth");
            tipDialog.show(getChildFragmentManager(), "auth");
            return false;
        }

        if (!"10B".equals(StorageCustomerInfo02Util.getInfo("freezeStatus", context))) {
            ViewUtils.makeToast(context, "实名审核中", 500);
            return false;
        }
        return true;
    }

    /**
     * 判断是否完成实名认证
     *
     * @return
     */
    public boolean checkCustomerInfoComplete() {
        if (!"10B".equals(StorageCustomerInfo02Util.getInfo("freezeStatus", context))) {
            return false;
        }
        return true;
    }



    /**
     * 判断是否进行实名认证
     *
     * @return
     */
    public boolean checkAuth() {
        String freezeStatus = StorageCustomerInfo02Util.getInfo("freezeStatus", context);
        if ("10A".equals(freezeStatus)) {
            return false;
        }
        return true;
    }

    public String getMerNo() {
        return StorageCustomerInfo02Util.getInfo("customerNum", context);
    }

    public String getMerId() {
        return StorageCustomerInfo02Util.getInfo("merchantId", context);
    }

    /**
     * 判断是否绑定储蓄卡
     *
     * @return
     */
    public boolean checkBindCard() {
        String bankAccount = StorageCustomerInfo02Util.getInfo("bankAccount", context);
        if (StringUtil.isEmpty(bankAccount)) {
            TipDialog tipDialog = TipDialog.getInstance("是否去绑定储蓄卡", "bind");
            tipDialog.show(getChildFragmentManager(), "auth");
            return false;
        }
        return true;
    }

}
