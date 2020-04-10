package com.linglingyi.com.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.DataCleanManager;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StorageAppInfoUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.modifypwd)
    ImageView modifypwd;
    @BindView(R.id.iv_service)
    ImageView ivService;
    @BindView(R.id.rl_service)
    RelativeLayout rlService;
    @BindView(R.id.iv_cache)
    ImageView ivCache;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.rl_clear_cache)
    RelativeLayout rlClearCache;
    @BindView(R.id.iv_version)
    ImageView ivVersion;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.btn_exit)
    Button btnExit;
    @BindView(R.id.rl_pass_login)
    RelativeLayout rlPassLogin;
    @BindView(R.id.auth)
    ImageView auth;
    @BindView(R.id.switch_defalut)
    Switch switchDefalut;
    @BindView(R.id.rl_auth_change)
    RelativeLayout rlAuthChange;
    @BindView(R.id.theme)
    ImageView theme;
    @BindView(R.id.rl_change_theme)
    RelativeLayout rlChangeTheme;
    private boolean aliAuth = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData() {
        tvTitle.setText("设  置");
        tvVersion.setText("V " + CommonUtils.getAppVersionName(this));
        tvCache.setText(DataCleanManager.getTotalCacheSize(context));
        LogUtils.i("normal" + StorageAppInfoUtil.getBooleanInfo("aliAuth", context));
        switchDefalut.setChecked(StorageAppInfoUtil.getBooleanInfo("aliAuth", context));
        initListener();

    }

    private void initListener() {
        //是否为默认地址
        if (!"10B".equals(StorageCustomerInfo02Util.getInfo("freezeStatus", context))) {
            switchDefalut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    LogUtils.i("isChecked" + isChecked);

                    StorageAppInfoUtil.putInfo(context, "aliAuth", isChecked);
                    aliAuth = isChecked;
                }
            });

        } else {
//            ViewUtils.makeToast(context, "您已实名", 500);
            switchDefalut.setClickable(false);
        }
    }

    @OnClick({R.id.iv_back, R.id.rl_clear_cache, R.id.btn_exit,
            R.id.rl_pass_login, R.id.rl_service, R.id.rl_change_theme})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.rl_clear_cache:
                DataCleanManager.clearAllCache(context);
                ViewUtils.makeToast(context, "缓存清除成功", 500);
                tvCache.setText("0KB");
                break;
            case R.id.btn_exit:
                showExitDialog(context);
                break;
            case R.id.rl_service:
                // TODO: 2019/7/2 拨打电话
//                if (!PermissionUtil.CALL_PHONE(context)) {
//                    return;
//                }
//                new ServiceCallDialog().show(getSupportFragmentManager(), "call");
                startActivity(new Intent(context, ServiceCenterActivity.class));
                break;
            case R.id.rl_pass_login:
                // TODO: 2019/7/2 修改密码
                startActivity(new Intent(context, PasswordChangeActivity.class).putExtra("isLogin", true));

                break;

            case R.id.rl_change_theme:
                startActivity(new Intent(context, ThemeChangeActivity.class));
                break;
        }
    }


    private void showExitDialog(final Activity activity) {
        Button confirmBt, cancleBt;
        final Dialog mydialog = new Dialog(activity, R.style.MyProgressDialog);
        mydialog.setContentView(R.layout.dialog_exit);
        mydialog.setCanceledOnTouchOutside(false);
        confirmBt = (Button) mydialog.findViewById(R.id.bt_cancelPlan);
        cancleBt = (Button) mydialog.findViewById(R.id.bt_suspendCancel);
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.isFastDoubleClick()) {
                    return;
                }
                //清除存储终端信息的缓存数据
                if (StorageCustomerInfo02Util.clearKey(context)) {
                    goLogin();
                } else {
                    ViewUtils.makeToast(context, "数据清除失败", 500);
                }
            }
        });
        cancleBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mydialog.dismiss();
            }

        });
        mydialog.show();
    }

    /**
     * 跳转至登录页
     */
    private void goLogin() {
        Intent intent = new Intent(context, LoginNewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
