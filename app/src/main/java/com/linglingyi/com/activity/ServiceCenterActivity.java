package com.linglingyi.com.activity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjq.permissions.Permission;
import com.linglingyi.com.utils.PermissionsUtils;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.PermissionUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/17
 */
public class ServiceCenterActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.btn_online_service)
    Button btnOnlineService;
    @BindView(R.id.tv_service_phone)
    TextView tvServicePhone;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.ll_service_wechat)
    LinearLayout llServiceWechat;

    @Override
    public int initLayout() {
        return R.layout.act_service_center;
    }

    @Override
    public void initData() {
        tvTitle.setText("客服中心");
        String call = StorageCustomerInfo02Util.getInfo("serviceNumber", context);
//        if (call.contains(",") && call.split(",").length >= 2) {
//            String call1 = call.split(",")[0];
//            String call2 = call.split(",")[1];
//            tvServicePhone.setText(call1);
//        } else {
//
//        }
        tvServicePhone.setText("联系电话：" + call);
        tvWechat.setText("官方微信号 :" + StorageCustomerInfo02Util.getInfo("wechat", context));
    }

    @OnClick({R.id.iv_back, R.id.btn_online_service, R.id.ll_phone,R.id.btn_wechat_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_online_service:
                // TODO: 2019/7/25 53在线客服
                if (StorageCustomerInfo02Util.getIntInfo(context, "kf", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                startActivity(new Intent(context, ContactServiceActivity.class));
                break;
            case R.id.btn_wechat_copy:
                // TODO: 2019/6/17 微信客服
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", StorageCustomerInfo02Util.getInfo("wechat", context));
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                ViewUtils.makeToast(context, "复制成功", 1000);
                break;
            case R.id.ll_phone:
                if (!PermissionUtil.CALL_PHONE(context)) {
                    return;
                }
                if (StringUtil.isEmpty(StorageCustomerInfo02Util.getInfo("serviceNumber", context))) {
                    return;
                }
                showCallServerDialog(StorageCustomerInfo02Util.getInfo("serviceNumber", context));
                break;
//            case R.id.tv_service_phone_2:
//                if (!PermissionUtil.CALL_PHONE(context)) {
//                    return;
//                }
//                if (StringUtil.isEmpty(tvServicePhone2.getText().toString())) {
//                    return;
//                }
//                showCallServerDialog(tvServicePhone2.getText().toString());
//                break;
        }
    }

    private void showCallServerDialog(final String phone) {
        Button confirmBt, cancleBt;
        final Dialog mydialog = new Dialog(context, R.style.MyProgressDialog);
        mydialog.setContentView(R.layout.callserver_dialog);
        mydialog.setCanceledOnTouchOutside(false);
        TextView phonenum = (TextView) mydialog.findViewById(R.id.phoneNum);

        phonenum.setText(phone);
        confirmBt = (Button) mydialog.findViewById(R.id.bt_cancelPlan);
        cancleBt = (Button) mydialog.findViewById(R.id.bt_suspendCancel);
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.isFastDoubleClick2()) {
                    return;
                }
                mydialog.dismiss();
                /**判断是否具有某个权限,及申请某个权限**/
                if(PermissionsUtils.isHasPermission(context, Permission.CALL_PHONE)){//判断是否具有某个权限，比如打电话
                    String serviceNumber = phone.replace("-", "");
                    Intent phoneIntent = new Intent(
                            "android.intent.action.CALL", Uri.parse("tel:"
                            + serviceNumber));
                    startActivity(phoneIntent);
                }else {
                    PermissionsUtils.requestPermissionone(context,Permission.CALL_PHONE);//跳转授权界面授权
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
