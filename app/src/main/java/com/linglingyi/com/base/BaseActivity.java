package com.linglingyi.com.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StorageAppInfoUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.viewone.dialog.TipDialog;
import com.lzy.okgo.OkGo;
import com.wuyouchuangke.com.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {
    public Dialog loadingDialog;
    public Dialog loadingDialogCanCancel;
    public Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        loadingDialog = ViewUtils.createLoadingDialog(BaseActivity.this, getString(R.string.loading_wait), false);
        loadingDialogCanCancel = ViewUtils.createLoadingDialog(BaseActivity.this, getString(R.string.loading_wait), true);
        context = this;
        //设置布局
        String theme = StorageAppInfoUtil.getInfo("theme", context);
        LogUtils.i("theme=" + theme);
        switch (theme) {
            case "08bdcd":
                setTheme(R.style.AppTheme);
                break;
            case "38a3f7":
                setTheme(R.style.BlueTheme);
                break;
            case "221814":
                setTheme(R.style.BlackTheme);
                break;
            case "f08519":
                setTheme(R.style.OrangeTheme);
                break;
            case "f1b900":
                setTheme(R.style.YellowTheme);
                break;
            default:
                setTheme(R.style.AppTheme);
                break;
        }

        setContentView(initLayout());
        ButterKnife.bind(this);
        //设置数据
        initData();
        //全局设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        ActivityManager.getInstance().add(this);
        setWindowStatusBarColor(this, R.color.transparent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (loadingDialogCanCancel != null && loadingDialogCanCancel.isShowing()) {
            loadingDialogCanCancel.dismiss();
        }
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
    public abstract void initData();

    public void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Window window = activity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(activity, colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getMerNo() {
        return StorageCustomerInfo02Util.getInfo("customerNum", context);
    }

    public String getMerId() {
        return StorageCustomerInfo02Util.getInfo("merchantId", context);
    }

    public boolean checkCustomerInfoCompleteShowToast() {
        //用于判断是否进行过实名认证
        if (!checkAuth()) {
            TipDialog tipDialog = TipDialog.getInstance("是否去实名认证", "auth");
            tipDialog.show(getSupportFragmentManager(), "auth");
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
    public boolean checkAuth() {
        String freezeStatus = StorageCustomerInfo02Util.getInfo("freezeStatus", context);
        if ("10A".equals(freezeStatus)) {
            return false;
        }
        return true;
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
            tipDialog.show(getSupportFragmentManager(), "auth");
            return false;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ViewUtils.overridePendingTransitionBack(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void setImmerseLayout(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
                /*window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int statusBarHeight = ViewUtils.getStatusBarHeight(this.getBaseContext());
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

}
