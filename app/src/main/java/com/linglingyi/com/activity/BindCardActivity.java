package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.wuyouchuangke.com.R;

import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.AccountModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.utils.BitmapManage;
import com.linglingyi.com.utils.CheckOutMessage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.linglingyi.com.utils.Constant.IMAGE_PICKER;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class BindCardActivity extends BaseActivity {
    private static final int REWUEST_FINISH = 0x20;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.et_bank_number)
    EditText etBankNumber;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_card_number)
    EditText etCardNumber;
    @BindView(R.id.et_expiryDate)
    EditText etExpiryDate;
    @BindView(R.id.iv_showValidate)
    ImageView ivShowValidate;
    @BindView(R.id.et_statement)
    EditText etStatement;
    @BindView(R.id.iv_showValidate1)
    ImageView ivShowValidate1;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_confirmCode)
    EditText etConfirmCode;
    @BindView(R.id.bt_getConfirmCode)
    Button btGetConfirmCode;
    @BindView(R.id.layout_code)
    LinearLayout layoutCode;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    private BindCard mBindCard;
    private String category, type;

    private String name, cardNumber, bankNumber, phoneNumber, statement, expiryDate;
    private String orderNo;
    private Timer timer;
    private int time = 60;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (time == 0) {
                    timer.cancel();
                    btGetConfirmCode.setClickable(true);
                    btGetConfirmCode.setText("发送验证码");
                } else {
                    btGetConfirmCode.setClickable(false);
                    btGetConfirmCode.setText(time + "秒后可重发");
                    time--;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_bind_card;
    }

    @Override
    public void initData() {
        mBindCard = (BindCard) getIntent().getSerializableExtra("bindCard");
        category = getIntent().getStringExtra("category");
        type = getIntent().getStringExtra("type");
        tvTitle.setText("绑卡");
        String idcardNumber = StorageCustomerInfo02Util.getInfo("idCardNumber", this);
        String bankAccountName = mBindCard.getBANK_ACCOUNT_NAME();
        etCardNumber.setText(idcardNumber);
        etName.setText(bankAccountName);
        etExpiryDate.setText(mBindCard.getExpdate());
        etStatement.setText(mBindCard.getCvn());
        etPhoneNumber.setText(mBindCard.getBANK_PHONE());
        etBankNumber.setText(mBindCard.getBANK_ACCOUNT());
        if (showCodeLayout()) {
            layoutCode.setVisibility(View.VISIBLE);
        } else
            layoutCode.setVisibility(View.GONE);
        etCardNumber.setFocusable(false);

    }

    private boolean showCodeLayout() {
        return TextUtils.equals("0", category);
    }

    @OnClick({R.id.iv_back, R.id.iv_showValidate,
            R.id.iv_showValidate1, R.id.bt_submit, R.id.bt_getConfirmCode})
    public void onViewClicked(View view) {
        if (CommonUtils.isFastDoubleClick2()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.iv_showValidate:
                break;
            case R.id.iv_showValidate1:
                break;
            case R.id.bt_submit:
                name = etName.getText().toString();
                cardNumber = etCardNumber.getText().toString();
                bankNumber = etBankNumber.getText().toString();
                phoneNumber = etPhoneNumber.getText().toString();
                statement = etStatement.getText().toString();
                expiryDate = etExpiryDate.getText().toString().replace("/", "");
                String code = etConfirmCode.getText().toString().trim();
                if (CheckOutMessage.isEmpty(context, "姓名", name)) return;
                if (CheckOutMessage.isEmpty(context, "身份证号", cardNumber)) return;
                if (CheckOutMessage.isEmpty(context, "银行卡号", bankNumber)) return;
                if (CheckOutMessage.isEmpty(context, "手机号", phoneNumber)) return;
                if (CheckOutMessage.isEmpty(context, "CVV", statement)) return;
                if (CheckOutMessage.isEmpty(context, "有效期", expiryDate)) return;
                if (Utils.checkNameChinese(name)) {
                    ViewUtils.makeToast(this, "请输入中文姓名", 1000);
                    return;
                }
                // 检查网络状态
                if (CommonUtils.getConnectedType(context) == -1) {
                    ViewUtils.makeToast(context, getString(R.string.nonetwork), 1500);
                    return;
                }

                if (showCodeLayout()) {
                    if (orderNo == null) {
                        ViewUtils.makeToast(this, "请先发送验证码", 1000);
                        return;
                    }
                    if (TextUtils.isEmpty(code)) {
                        ViewUtils.makeToast(this, "验证码不能为空", 1000);
                        return;
                    }
                }
                submit(code);

                break;
            case R.id.bt_getConfirmCode:
                name = etName.getText().toString();
                cardNumber = etCardNumber.getText().toString();
                bankNumber = etBankNumber.getText().toString();
                phoneNumber = etPhoneNumber.getText().toString();
                statement = etStatement.getText().toString();
                expiryDate = etExpiryDate.getText().toString().replace("/", "");
                if (CheckOutMessage.isEmpty(context, "姓名", name)) return;
                if (CheckOutMessage.isEmpty(context, "身份证号", cardNumber)) return;
                if (CheckOutMessage.isEmpty(context, "银行卡号", bankNumber)) return;
                if (CheckOutMessage.isEmpty(context, "手机号", phoneNumber)) return;
                if (CheckOutMessage.isEmpty(context, "CVV", statement)) return;
                if (CheckOutMessage.isEmpty(context, "有效期", expiryDate)) return;
                if (Utils.checkNameChinese(name)) {
                    ViewUtils.makeToast(this, "请输入中文姓名", 1000);
                    return;
                }
                // 检查网络状态
                if (CommonUtils.getConnectedType(context) == -1) {
                    ViewUtils.makeToast(context, getString(R.string.nonetwork), 1500);
                    return;
                }
                getCode();
                break;
        }
    }

    private void submit(String code) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "490008");
        httpParams.put("5", mBindCard.getLIMIT_MONEY());
        httpParams.put("6", mBindCard.getBILL_DAY());
        httpParams.put("7", mBindCard.getREPAYMENT_DAY());
        if (StringUtil.isEmpty(mBindCard.getID())) {
            httpParams.put("37", "0");
        } else {
            httpParams.put("37", mBindCard.getID());
        }
        httpParams.put("41", showCodeLayout() ? code : " ");
        httpParams.put("42", cardNumber);
        httpParams.put("43", getMerId());
        httpParams.put("45", bankNumber);
        httpParams.put("46", phoneNumber);
        httpParams.put("47", expiryDate);
        httpParams.put("48", statement);
        httpParams.put("49", type);
        httpParams.put("50", getMerId());
        httpParams.put("31", TextUtils.isEmpty(orderNo) ? " " : orderNo);
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
                    String url = model.getStr38();
                    if ("00".equals(url)) {
                        ViewUtils.makeToast(context, "绑定成功", 1000);
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Intent intent = new Intent(context, X5WebViewActivity.class);
                        intent.putExtra("title", "绑卡");
                        intent.putExtra("url", url);
                        intent.putExtra("back", true);
                        startActivityForResult(intent, REWUEST_FINISH);
                    }
                } else if (model.getStr39().contains("通道已绑卡") || model.getStr39().contains("签约成功")) {
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });

    }

    private void getCode() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "490007");
        httpParams.put("5", mBindCard.getLIMIT_MONEY());
        httpParams.put("6", mBindCard.getBILL_DAY());
        httpParams.put("7", mBindCard.getREPAYMENT_DAY());
        if (StringUtil.isEmpty(mBindCard.getID())) {
            httpParams.put("37", "0");
        } else {
            httpParams.put("37", mBindCard.getID());
        }
        httpParams.put("41", bankNumber);
        httpParams.put("42", cardNumber);
        httpParams.put("43", name);
        httpParams.put("46", phoneNumber);
        httpParams.put("47", expiryDate);
        httpParams.put("48", statement);
        httpParams.put("49", type);
        httpParams.put("50", getMerId());
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
                    orderNo = model.getStr31();
                    ViewUtils.makeToast(context, "验证码已发送，请注意查收", 500);
                    time();

                }
            }
        });
    }

    void time() {
        timer = new Timer();
        time = 60;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timing();
            }
        };
        timer.schedule(task, 0, 1000);
    }

    void timing() {
        Message message = Message.obtain();
        message.what = 1;
        handler.sendMessage(message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REWUEST_FINISH && resultCode == RESULT_OK) {
            finish();
        }
    }

}
