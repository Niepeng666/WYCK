package com.linglingyi.com.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.PayResult;
import com.linglingyi.com.model.WeiXinModel;
import com.linglingyi.com.utils.CheckOutMessage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.utils.wechat.PayListener;
import com.linglingyi.com.utils.wechat.WechatPay;
import com.linglingyi.com.viewone.FontIconView;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardHonorActivity extends BaseActivity {


    private static final int SDK_PAY_FLAG = 1;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_id_card)
    EditText etIdCard;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_phone_password)
    EditText etPhonePassword;
    @BindView(R.id.tv_registration_protocol)
    TextView tvRegistrationProtocol;
    @BindView(R.id.ly_check)
    LinearLayout lyCheck;
    @BindView(R.id.btn_check)
    Button btnCheck;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.checkbox)
    FontIconView checkbox;


    private String scoreCost;
    private String name, idCard, phone, phonePassword;
    private boolean isChecked = true;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    LogUtil.e("clx", payResult.toString());
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ViewUtils.makeToast(context,
                                "支付成功", 500);
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ViewUtils.makeToast(context, "支付取消", 500);
                    } else {
                        ViewUtils.makeToast(context, "支付失败", 500);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @OnClick({R.id.iv_back, R.id.tv_registration_protocol, R.id.btn_check, R.id.tv_right, R.id.checkbox})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_registration_protocol:
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("title", "用户协议");
                intent.putExtra("url", Constant.honor_url);
                startActivity(intent);
                break;
            case R.id.btn_check:
                name = etName.getText().toString();
                idCard = etIdCard.getText().toString();
                phone = etPhone.getText().toString();
                phonePassword = etPhonePassword.getText().toString();
                if (CheckOutMessage.isEmpty(context, "姓名", name)) {
                    return;
                }
                if (CheckOutMessage.isEmpty(context, "身份证号", idCard)) {
                    return;
                }
                if (CheckOutMessage.isEmpty(context, "手机号", phone)) {
                    return;
                }
                if (CheckOutMessage.isEmpty(context, "手机服务密码", phonePassword)) {
                    return;
                }
                if (!isChecked) {
                    ViewUtils.makeToast(context, "请先同意用户授权协议", 1000);
                    return;
                }
                showChoseDialogCost(context, "此次查询费用" + scoreCost + "元，将从可提现分润扣除", "确定");

                break;
            case R.id.tv_right:
                startActivity(new Intent(context, CardScoreHistoryListActivity.class));
                break;
            case R.id.checkbox:
                isChecked = !isChecked;
                int[] attrArray1 = {R.attr.theme_bg_color};
                TypedArray mTypedArray1 = context.obtainStyledAttributes(attrArray1);
                checkbox.setTextColor(isChecked ? mTypedArray1.getColor(0, 0xFF000000) : ContextCompat.getColor(context, R.color.gray));
                break;
        }
    }

    /**
     * 是否支付查询金额
     *
     * @param context
     * @param msg
     */
    public void showChoseDialogCost(final Context context, String msg, final String confirm) {
        final Dialog updateDialog = new Dialog(context, R.style.MyProgressDialog);
        updateDialog.setContentView(R.layout.chose_dialog_update);
        updateDialog.setCanceledOnTouchOutside(false);
        Button dialog_confirmBt = (Button) updateDialog.findViewById(R.id.left_bt);
        final Button cancleBt = (Button) updateDialog.findViewById(R.id.right_bt);
        cancleBt.setText("取消");
        dialog_confirmBt.setText(confirm);
        TextView dialog_title_text = ((TextView) updateDialog.findViewById(R.id.title_text));
        dialog_title_text.setText(msg);
        dialog_title_text.setTextSize(18);
        dialog_title_text.setTextColor(Color.BLACK);
        cancleBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
            }
        });
        dialog_confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
                if ("确定".equals(confirm)) {
                    requestData();
                } else {
                    costDialog();
                }
            }
        });
        updateDialog.show();
    }

    /**
     * 充值dialog
     */
    private void costDialog() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_pay, null);
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .show();

        view.findViewById(R.id.tv_alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // TODO: 2019/4/3 支付宝支付
                loadPayData("z");
//                startActivity(new Intent(context, QrCodePayActivity.class)
//                        .putExtra("paytype", "z")
//                        .putExtra("money", scoreCost)
//                        .putExtra("type", "Z"));
            }
        });
        view.findViewById(R.id.tv_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // TODO: 2019/4/3 微信支付
                loadPayData("w");
//                startActivity(new Intent(context, QrCodePayActivity.class)
//                        .putExtra("paytype", "w")
//                        .putExtra("money", scoreCost)
//                        .putExtra("type", "Z"));
            }
        });
    }

    private void loadPayData(final String type) {
        String money = CommonUtils.formatNewFen(scoreCost);
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "890001");
        httpParams.put("5", money);
        httpParams.put("8", type);
        httpParams.put("41", "CZ");
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
                    final String orderInfo = model.getStr42();
                    if ("z".equals(type)) {
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(context);
                                Map<String, String> result = alipay.payV2(orderInfo, true);
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    } else if ("w".equals(type)) {
                        WeiXinModel wechatModel = JSONObject.parseObject(orderInfo, WeiXinModel.class);
                        WechatPay.getInstance().startWeChatPay(wechatModel.getAppid(), wechatModel.getPartnerid(), wechatModel.getPrepayid(), wechatModel.getNoncestr(), wechatModel.getTimestamp(), wechatModel.getSign(), new PayListener() {
                            @Override
                            public void onPaySuccess() {
                                ViewUtils.makeToast(context,
                                        "支付成功", 500);
                            }

                            @Override
                            public void onPayError(String resultStatus) {
                                ViewUtils.makeToast(context, "支付失败" + "\n" + resultStatus, 1000);
                            }

                            @Override
                            public void onPayCancel() {
                                ViewUtils.makeToast(context, "支付取消", 1000);
                            }
                        });
                    }
                }
            }
        });
    }

    private void requestData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "690018");
        httpParams.put("42", getMerId());
        httpParams.put("23", idCard);
        httpParams.put("24", phonePassword);
        httpParams.put("21", phone);
        httpParams.put("22", name);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    ViewUtils.makeToast(context, "查询信息已提交", 500);
                    String url = model.getStr57();
                    Intent intent = new Intent(context, X5WebViewActivity.class);
                    intent.putExtra("title", "大数据征信结果");
                    intent.putExtra("url", url);
                    startActivity(intent);
                } else if ("ZZ".equals(model.getStr39()) || model.getStr39().contains("余额不足")) {
                    showChoseDialogCost(context, "您的账户余额不足，请前往充值", "充值");
                } else {
                    ViewUtils.makeToast(context, model.getStr39(), 500);
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
        return R.layout.activity_card_honor;
    }

    @Override
    public void initData() {
        tvTitle.setText("征信查询");
        tvRight.setText("历史记录");
        tvRight.setVisibility(View.VISIBLE);
        scoreCost = StorageCustomerInfo02Util.getInfo("honorCost", context);
        tvMoney.setText("" + scoreCost + "元");
    }
}
