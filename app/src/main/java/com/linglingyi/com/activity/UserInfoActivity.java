package com.linglingyi.com.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.cl_clean_cache)
    ConstraintLayout clCleanCache;
    @BindView(R.id.tv_id_card)
    TextView tvIdCard;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_auth_status)
    TextView tvAuthStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initData() {
        tvTitle.setText("用户资料");
        String phone = StorageCustomerInfo02Util.getInfo("phone", context);
        if (phone.length() > 4) {
            String phone1 = phone.substring(0,3);
            String phone2 = phone.substring(phone.length() - 4, phone.length());
            tvPhone.setText(phone1+" **** "+phone2);
        }
        String name = StorageCustomerInfo02Util.getInfo("merchantCnName", context);
        tvName.setText(!StringUtil.isEmpty(name) ? name : phone);
        String idCard = StorageCustomerInfo02Util.getInfo("idCardNumber", context);
        if (idCard.length() > 4) {
            String id1 = idCard.substring(0,5);
            String id2 = idCard.substring(idCard.length() - 4, idCard.length());
            tvIdCard.setText(id1+" **** **** "+id2);
        }



        String authStr;
        String bankAccount = StorageCustomerInfo02Util.getInfo("bankAccount", context);
        String infoImageUrl_10E = StorageCustomerInfo02Util.getInfo("infoImageUrl_10E", context);
        String infoImageUrl_10F = StorageCustomerInfo02Util.getInfo("infoImageUrl_10F", context);
        if (TextUtils.isEmpty(bankAccount) ||
                TextUtils.isEmpty(infoImageUrl_10E) || TextUtils.isEmpty(infoImageUrl_10F)) {
            authStr = "未认证";
        } else {
            String freeStatus = StorageCustomerInfo02Util.getInfo("freezeStatus", context);
            switch (freeStatus) {
                case "10B":
                    authStr = "审核通过";
                    break;
                case "10C":
                    authStr = "审核拒绝";
                    break;
                case "10D":
                    authStr = "重新审核中";
                    break;
                default:
                    authStr = "审核中";
                    break;
            }
        }

        tvAuthStatus.setText(authStr);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
