package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BankNameModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.BitmapManage;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.PermissionUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.linglingyi.com.utils.Constant.IMAGE_PICKER;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/7/9
 */
public class DebitBankChangeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_bank_num)
    EditText etBankNum;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.bt_submit)
    Button btSubmit;

    private String bankCode;

    @Override
    public int initLayout() {
        return R.layout.act_debit_bank_change;
    }

    @Override
    public void initData() {
        tvTitle.setText("换绑储蓄卡");
        ivRight.setImageResource(R.drawable.photo_icon);
        ivRight.setVisibility(View.VISIBLE);
        String bankAccountName = StorageCustomerInfo02Util.getInfo("merchantCnName", context);
        tvName.setText(bankAccountName);
        String phoneNum = StorageCustomerInfo02Util.getInfo("phone", context);
        etPhone.setText(phoneNum);
        etPhone.setFocusable(false);
        initListener();
    }

    private void initListener() {
        etBankNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etBankNum.getText().toString().length() >= 16) {
                    getBankName(etBankNum.getText().toString());
                }
            }
        });
    }

    /**
     * 获取银行卡名称
     *
     * @param bankNum 卡号
     */
    private void getBankName(String bankNum) {

        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("15", bankNum);
        map.put("3", "690013");
        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    BankNameModel bankNameModel = JSONObject.parseObject(model.getStr16(), BankNameModel.class);
                    tvBankName.setText(bankNameModel.getShortCnName());
                    bankCode = bankNameModel.getBankCode();
                }
            }

        });
    }

    @OnClick({R.id.iv_back, R.id.iv_right, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.iv_right:
                if (!PermissionUtil.CAMERA(context)) {
                    return;
                }
                Intent imgIntent = new Intent(context, ImageGridActivity.class);
                startActivityForResult(imgIntent, Constant.IMAGE_PICKER);
                break;
            case R.id.bt_submit:
                String bankName = tvBankName.getText().toString().trim();
                String bankNum = etBankNum.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                if (bankName.equals("请选择银行")) {
                    ViewUtils.makeToast(context, "请选择银行名称", 1000);
                    return;
                }
                if (StringUtil.isEmpty(bankNum)) {
                    ViewUtils.makeToast(context, "请输入银行卡号", 1000);
                    return;
                }
                if (StringUtil.isEmpty(phone)) {
                    ViewUtils.makeToast(context, "请输入预留手机号", 1000);
                    return;
                }
                sendSubmit(bankNum, phone);
                break;
        }
    }

    /**
     * 提交信息
     */
    private void sendSubmit(String bankNum, String phone) {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "390001");
        httpParams.put("6", phone);
        httpParams.put("5", bankNum);
        httpParams.put("42", getMerNo());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    StorageCustomerInfo02Util.putInfo(context, "bankAccount", model.getStr5());
                    StorageCustomerInfo02Util.putInfo(context, "phone", model.getStr6());
                    StorageCustomerInfo02Util.putInfo(context, "bankCode", model.getStr40());
                    finish();
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                uploadImage(BitmapManage.bitmapToString(images.get(0).path, 600, 600), "10K");
            } else {
                ViewUtils.makeToast(context, "没有数据", 500);
            }
        }

    }

    /**
     * 上传图片
     *
     * @param imageData
     * @param imageType
     */
    private void uploadImage(String imageData, final String imageType) {
        LogUtils.i("imageData=" + imageData);
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190948");
        httpParams.put("9", imageType);
        httpParams.put("42", getMerNo());
        httpParams.put("43", getMerId());
        httpParams.put("40", imageData);
        OkClient.getInstance().post(Constant.UPLOADIMAGE, httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if ("00".equals(model.getStr39())) {

                    etBankNum.setText(model.getStr42());
                    getBankName(model.getStr42());
                    StorageCustomerInfo02Util.putInfo(context, "infoImageUrl_10K", model.getStr57());
                }
            }
        });

    }
}
