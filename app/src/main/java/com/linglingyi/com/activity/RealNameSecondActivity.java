package com.linglingyi.com.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BankNameModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.RealPersonModel;
import com.linglingyi.com.utils.BitmapManage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.GlideUtils;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/10 16:52
 * @功能 实名认证结算卡填写
 **/
public class RealNameSecondActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_bank_1)
    ImageView ivBank1;
    @BindView(R.id.et_bank_code)
    EditText etBankCode;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.et_phone)
    EditText etPhone;

    private RealPersonModel realPersonModel;
    private boolean isInfoComplete;
    private String bankUrl;
    private String bankCode;


    @Override
    public int initLayout() {
        return R.layout.activity_real_name_second;
    }

    @Override
    public void initData() {
        tvTitle.setText("实名认证");

        realPersonModel = (RealPersonModel) getIntent().getSerializableExtra("realPersonModel");
        isInfoComplete = getIntent().getBooleanExtra("isInfoComplete", false);
        String bankAccount = StorageCustomerInfo02Util.getInfo("bankAccount", context);
        bankCode = StorageCustomerInfo02Util.getInfo("bankCode", context);
        String phoneNum = StorageCustomerInfo02Util.getInfo("phone", context);

        if (isInfoComplete) {
            if (!StringUtil.isEmpty(bankAccount)) {
                etBankCode.setText(bankAccount);
            }
            if (!StringUtil.isEmpty(MyApplication.bankCodeList.get(bankCode))) {
                tvBankName.setText(MyApplication.bankCodeList.get(bankCode));
            }
            bankUrl = StorageCustomerInfo02Util.getInfo("infoImageUrl_10K", context);
            if (!StringUtil.isEmpty(bankUrl)) {
                GlideUtils.loadNoChacheImage(context, bankUrl, ivBank1);
            }
        }
        if (!StringUtil.isEmpty(phoneNum)) {
            etPhone.setText(phoneNum);
        }

        initListener();
    }

    @OnClick({R.id.iv_back, R.id.rl_bank_1, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_bank_1:
                if (!PermissionUtil.CAMERA(context)) {
                    return;
                }
                startActivityForResult(new Intent(context, ImageGridActivity.class), 1);
                break;
            case R.id.btn_next:
                String bankAccount = etBankCode.getText().toString();
                String bankPhone = etPhone.getText().toString();
                String bankName = tvBankName.getText().toString();
                if (StringUtil.isEmpty(bankUrl)) {
                    ViewUtils.makeToast(context, "请先上传储蓄卡正面照", 1000);
                    return;
                }
                if (StringUtil.isEmpty(bankAccount)) {
                    ViewUtils.makeToast(context, "请输入储蓄卡卡号", 1000);
                    return;
                }
                if (StringUtil.isEmpty(bankPhone)) {
                    ViewUtils.makeToast(context, "请输入银行预留手机号", 1000);
                    return;
                }
                if (StringUtil.isEmpty(bankCode)) {
                    ViewUtils.makeToast(context, "卡宾不存在", 1000);
                    return;
                }
                if (!CommonUtils.checkBankCard(bankAccount)) {
                    ViewUtils.makeToast(context, "请输入正确的银行卡号", 1000);
                    return;
                }

                realPersonModel.setBankAccount(bankAccount);
                realPersonModel.setBankName(bankName);
                realPersonModel.setBankCode(bankCode);
                realPersonModel.setPhone(bankPhone);

                Intent intent = new Intent(context, RealNameThirdActivity.class);
                intent.putExtra("realPersonModel", realPersonModel);
                intent.putExtra("isInfoComplete", isInfoComplete);
                startActivity(intent);
                break;
        }
    }

    private void initListener() {
        etBankCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etBankCode.getText().toString().length() >= 16) {
                    getBankName(etBankCode.getText().toString());
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data==null)
                return;
            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (requestCode == 1) {
                uploadImage(BitmapManage.bitmapToString(images.get(0).path, 600, 600),"10K");
            }else {
                ViewUtils.makeToast(context, "没有数据", 500);
            }
        }
    }

    private void uploadImage(String signPath, String imageType) {
        // 检查网络状态
        if (CommonUtils.getConnectedType(context) == -1) {
            ViewUtils.makeToast(context,
                    getString(R.string.nonetwork), 1500);
            return;
        }
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190948");
        httpParams.put("9", imageType);
        httpParams.put("42", getMerNo());
        httpParams.put("40", signPath);
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
                    // 上传成功
                    String imageUrl = model.getStr57();
                    StorageCustomerInfo02Util.putInfo(context, "infoImageUrl_10K", imageUrl);
                    bankUrl = imageUrl;
                    GlideUtils.loadImage(context, bankUrl, ivBank1);
                }
            }
        });

    }
}
