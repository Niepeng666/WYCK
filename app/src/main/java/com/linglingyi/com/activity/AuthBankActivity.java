package com.linglingyi.com.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.AuthCloseEvent;
import com.linglingyi.com.event.AuthEvent;
import com.linglingyi.com.model.BankNameModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.RealPersonModel;
import com.linglingyi.com.utils.BitmapManage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.FileUtils;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.ImageUtils;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.PermissionUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.UriToPathUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.linglingyi.com.utils.Constant.IMAGE_PICKER;

/**
 * @author: lilingfei
 * @date: 2019/2/11
 * @describe:
 */
public class AuthBankActivity extends BaseActivity {
    private static final int REQUEST_CAMERA = 1;//拍照
    private static final int REQUEST_PHOTO = 2; // 本地
    private static final int BANK_NAME = 0x20;
    private static final int auth_id_1 = 1;
    @BindView(R.id.btn_next)
    Button btnNext;
    RealPersonModel realPersonModel;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.et_bank_account)
    EditText etBankAccount;
    @BindView(R.id.et_bank_phone)
    EditText etBankPhone;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.enter_iv)
    ImageView enterIv;
    @BindView(R.id.ly_bank)
    LinearLayout lyBank;
    private String bankUrl;
    private int id = auth_id_1;
    private boolean canInput = true;
    private boolean isInfoComplete;


    @OnClick({R.id.iv_back, R.id.tv_bank_name, R.id.btn_next, R.id.iv_bank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.iv_bank:
                if (!canInput) {
                    return;
                }
                if (!PermissionUtil.CAMERA(context)) {
                    return;
                }
                id = auth_id_1;
                Intent imgIntent = new Intent(context, ImageGridActivity.class);
                startActivityForResult(imgIntent, Constant.IMAGE_PICKER);
                break;
            case R.id.tv_bank_name:
                if (!canInput) {
                    return;
                }
                startActivityForResult(new Intent(context, CustomBankNameListActivity.class), BANK_NAME);
                break;
            case R.id.btn_next:
                String bankAccount = etBankAccount.getText().toString();
                String bankPhone = etBankPhone.getText().toString();
                String bankName = tvBankName.getText().toString();
                String bankCode = MyApplication.bankNameList.get(bankName);
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
                    ViewUtils.makeToast(context, "请选择银行卡", 1000);
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

                admit();
                break;
            default:
                break;
        }
    }

    /**
     * 提交信息
     */
    private void admit() {
        loadingDialog.show();
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("1", realPersonModel.getPhone());
        map.put("5", realPersonModel.getName());
        map.put("6", realPersonModel.getIdcard());
        map.put("7", realPersonModel.getBankAccount());
        map.put("43", realPersonModel.getBankCode());
        map.put("3", "190938");


        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                if ("00".equals(response.body().getStr39())) {
                    ViewUtils.makeToast(context, "信息已提交，请耐心等待", 500);
                    StorageCustomerInfo02Util.putInfo(context, "merchantCnName", realPersonModel.getName());
                    StorageCustomerInfo02Util.putInfo(context, "bankAccount", realPersonModel.getBankAccount());
                    StorageCustomerInfo02Util.putInfo(context, "bankAccountName", realPersonModel.getName());
                    StorageCustomerInfo02Util.putInfo(context, "idCardNumber", realPersonModel.getIdcard());
                    StorageCustomerInfo02Util.putInfo(context, "bankDetail", realPersonModel.getBankName());
                    StorageCustomerInfo02Util.putInfo(context, "phone", realPersonModel.getPhone());
                    StorageCustomerInfo02Util.putInfo(context, "bankCode", realPersonModel.getBankAccount());
//                    StorageCustomerInfo02Util.putInfo(context, "freezeStatus", "10B");
                    ViewUtils.overridePendingTransitionBack(context);
                    EventBus.getDefault().post(new AuthEvent());
                    EventBus.getDefault().post(new AuthCloseEvent());
                }
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case BANK_NAME:
                    String selectBankname = data.getStringExtra("selectBankname");
                    tvBankName.setText(selectBankname);
                    break;
                default:
                    break;
            }
        }
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                LogUtils.i("images" + images.get(0).path);
                uploadImage(BitmapManage.bitmapToString(images.get(0).path, 600, 600),"10K");

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
        LogUtils.i("imageData=" + imageData+","+new File(imageData));
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190948");
        httpParams.put("9", imageType);
        httpParams.put("42", getMerNo());
        httpParams.put("40",imageData);
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
                     bankUrl=model.getStr57();
                    GlideUtils.loadImage(context,bankUrl,ivBank);
                    StorageCustomerInfo02Util.putInfo(context,"infoImageUrl_10K",model.getStr57());
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_auth_bank;
    }

    @Override
    public void initData() {
        tvTitle.setText("身份校验");

        realPersonModel = (RealPersonModel) getIntent().getSerializableExtra("realPersonModel");
        canInput = getIntent().getBooleanExtra("canInput", true);
        isInfoComplete = getIntent().getBooleanExtra("isInfoComplete", false);
        String bankAccount = StorageCustomerInfo02Util.getInfo("bankAccount", context);
        String bankCode = StorageCustomerInfo02Util.getInfo("bankCode", context);
        String phoneNum = StorageCustomerInfo02Util.getInfo("phone", context);
        etBankPhone.setFocusable(false);

        if (isInfoComplete) {
            if (!StringUtil.isEmpty(bankAccount)) {
                etBankAccount.setText(bankAccount);
            }
            if (!StringUtil.isEmpty(MyApplication.bankCodeList.get(bankCode))) {
                tvBankName.setText(MyApplication.bankCodeList.get(bankCode));
            }
        }
        if (!StringUtil.isEmpty(phoneNum)) {
            etBankPhone.setText(phoneNum);
        }

        if (isInfoComplete) {
            bankUrl = StorageCustomerInfo02Util.getInfo("infoImageUrl_10K", context);
            if (!StringUtil.isEmpty(bankUrl)) {
                GlideUtils.loadNoChacheImage(context, bankUrl, ivBank);
            }
        }
        if (!canInput) {
            etBankAccount.setFocusable(false);
            tvBankName.setClickable(false);
            btnNext.setText("下一页");
        }
        initListener();
    }

    private void initListener() {
        etBankAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etBankAccount.getText().toString().length() >= 16) {
                    getBankName(etBankAccount.getText().toString());
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
                }
            }

        });
    }


}
