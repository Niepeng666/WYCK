package com.linglingyi.com.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.linglingyi.com.activity.CardScoreHistoryListActivity;
import com.linglingyi.com.activity.LoginNewActivity;
import com.linglingyi.com.activity.WebViewActivity;
import com.linglingyi.com.activity.X5WebViewActivity;
import com.linglingyi.com.base.BaseFragment;
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
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/29
 */
public class CreditHonorFragment extends BaseFragment {
    private static final int SDK_PAY_FLAG = 1;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_idCard)
    EditText etIdCard;
    @BindView(R.id.et_bank_number)
    EditText etBankNumber;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_phone_password)
    EditText etPhonePassword;
    @BindView(R.id.code_ll)
    LinearLayout codeLl;
    @BindView(R.id.cb_xieYi)
    CheckBox cbXieYi;
    @BindView(R.id.tv_xieYi)
    TextView tvXieYi;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_card_score)
    TextView tvCardScore;
    @BindView(R.id.tv_card_honor)
    TextView tvCardHonor;
    Unbinder unbinder;
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
                        ViewUtils.makeToast2(context,
                                "支付成功,请重新登录", 500, LoginNewActivity.class,
                                "PAY");
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
    private String phone, idCard, name, phonePassword;
    private String scoreCost;

    public static CreditHonorFragment newInstance() {
        CreditHonorFragment creditHonorFragment = new CreditHonorFragment();
        return creditHonorFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public int initLayout() {
        return R.layout.frag_credit_honor;
    }

    @Override
    public void initData(View v) {
        scoreCost = StorageCustomerInfo02Util.getInfo("honorCost", context);
        tvMoney.setText(scoreCost);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tv_xieYi, R.id.btn_submit, R.id.tv_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_history:
                startActivity(new Intent(context, CardScoreHistoryListActivity.class));
                break;
            case R.id.tv_xieYi:
                startActivity(new Intent(context, WebViewActivity.class)
                        .putExtra("title", "协议")
                        .putExtra("url", Constant.honor_url));
                break;
            case R.id.btn_submit:
                phone = etPhone.getText().toString();
                name = etName.getText().toString();
                idCard = etIdCard.getText().toString();
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
                if (CheckOutMessage.isEmpty(context, "手机号服务密码", phonePassword)) {
                    return;
                }
                if (!cbXieYi.isChecked()) {
                    ViewUtils.makeToast(context, "请先同意用户授权协议", 1000);
                    return;
                }
                showChoseDialogCost(context, "此次查询费用" + scoreCost + "元，将从可提现分润扣除", "确定");
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
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pay, null);
        final android.support.v7.app.AlertDialog dialog = new android.support.v7.app.AlertDialog.Builder(context)
                .setView(view)
                .show();

        view.findViewById(R.id.tv_alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // : 2019/4/3 支付宝支付
                loadPayData("z");
            }
        });
        view.findViewById(R.id.tv_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // : 2019/4/3 微信支付
                loadPayData("w");
            }
        });
    }


    private void loadPayData(final String type) {
        String money = CommonUtils.format(scoreCost);
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
                                ViewUtils.makeToast2(context,
                                        "支付成功,请重新登录", 500, LoginNewActivity.class,
                                        "PAY");
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
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "690018");
        httpParams.put("42", getMerId());
        httpParams.put("23", idCard);
        httpParams.put("24", Base64.encodeToString(phonePassword.getBytes(), Base64.DEFAULT));
        httpParams.put("22", name);
        httpParams.put("21", phone);
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
                    String url = model.getStr57();
                    Intent intent = new Intent(context, X5WebViewActivity.class);
                    intent.putExtra("title", "征信结果");
                    intent.putExtra("url", url);
                    startActivity(intent);
                } else if ("ZZ".equals(model.getStr39())) {
                    showChoseDialogCost(context, "您的账户余额不足，请前往充值", "充值");
                } else {
                    ViewUtils.makeToast(context, model.getStr39(), 500);
                }
            }
        });
    }
}
