package com.linglingyi.com.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.LoginNewActivity;
import com.linglingyi.com.base.BaseFragment;
import com.linglingyi.com.callback.CancelCallback;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.PayResult;
import com.linglingyi.com.model.WeiXinModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.utils.wechat.PayListener;
import com.linglingyi.com.utils.wechat.WechatPay;
import com.linglingyi.com.viewone.dialog.BindParentPhoneDialog;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * @作者 chenlanxin
 * @创建日期 2019/2/21 14:24
 * @功能 认证
 **/
public class VIPFragment extends BaseFragment {
    private static final int SDK_PAY_FLAG = 1;
    Unbinder unbinder;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_level_1)
    ImageView ivLevel1;
    @BindView(R.id.iv_level_2)
    ImageView ivLevel2;
    @BindView(R.id.iv_level_3)
    ImageView ivLevel3;
    @BindView(R.id.iv_level_4)
    ImageView ivLevel4;
    @BindView(R.id.iv_level_5)
    ImageView ivLevel5;
    @BindView(R.id.iv_level_6)
    ImageView ivLevel6;


    private String money, level2Cost, level3Cost;
    private String levelName;
    private Integer selLevel, level;
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
    private String parentPhone;

    public static VIPFragment newInstance() {
        return new VIPFragment();
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
        return R.layout.fragment_new_vip;
    }

    @Override
    public void initData(View v) {
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("会员升级");
        level = StorageCustomerInfo02Util.getIntInfo(context, "level", 1);
        level2Cost = StorageCustomerInfo02Util.getInfo("level2Cost", context);
        level3Cost = StorageCustomerInfo02Util.getInfo("level3Cost", context);
        String phone = StorageCustomerInfo02Util.getInfo("phone", context);
        String name = StorageCustomerInfo02Util.getInfo("merchantCnName", context);
        loadHeaderData(true);
        checkParentDialog();
    }

    /**
     * 获取等级，会员费率图片
     */
    private void loadHeaderData(final boolean showParentDialog) {
//        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "390006");
        httpParams.put("42", getMerNo());
        httpParams.put("43", "10F");

        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
//                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    List<String> stringList = JSONArray.parseArray(model.getStr57(), String.class);
                    if (stringList.size() > 0) {
                        showPicLoad(stringList.get(0), ivLevel1);
                    }
                    if (stringList.size() > 1) {
                        showPicLoad(stringList.get(1), ivLevel2);
                    }
                    if (stringList.size() > 2) {
                        showPicLoad(stringList.get(2), ivLevel3);
                    }

                    if (stringList.size() > 3) {
                        showPicLoad(stringList.get(3), ivLevel4);
                    }

                    if (stringList.size() > 4) {
                        showPicLoad(stringList.get(4), ivLevel5);
                    }

                    if (stringList.size() > 5) {
                        showPicLoad(stringList.get(5), ivLevel6);
                    }

                }
            }
        });
    }

    /**
     * 显示图片进度条
     *
     * @param url
     */
    private void showPicLoad(final String url, final ImageView imageView) {
        Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                GlideUtils.loadImage(context, url, imageView);
                imageView.setImageBitmap(resource);
            }
        });
    }

    /**
     * 显示绑定推荐人弹框
     */
    private void checkParentDialog() {
         parentPhone = StorageCustomerInfo02Util.getInfo("parentPhone", context);
        if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
            final BindParentPhoneDialog parentPhoneDialog = BindParentPhoneDialog.getInstance();
            parentPhoneDialog.show(getChildFragmentManager(), "parent");
            parentPhoneDialog.setCancelCallback(new CancelCallback() {
                @Override
                public void cancel() {
                    parentPhoneDialog.dismiss();
                }
            });

        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            loadHeaderData(true);
            checkParentDialog();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.iv_level_2, R.id.iv_level_3, R.id.iv_level_4, R.id.iv_level_5, R.id.iv_level_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_level_2:
                if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
                    checkParentDialog();
                    return;
                }
                selLevel = 2;
                money = level2Cost;
                if (selLevel <= level) {
                    ViewUtils.makeToast(context, "您已升级", 500);
                    return;
                }
                // TODO: 2019/10/9 金额
                showVipDialog(selLevel + "");
                break;
            case R.id.iv_level_3:
              /*  if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
                    checkParentDialog();
                    return;
                }
                selLevel = 3;
                money = level3Cost;
                if (selLevel <= level) {
                    ViewUtils.makeToast(context, "您已升级", 500);
                    return;
                }
// TODO: 2019/10/9 金额
                showVipDialog(selLevel + "");
                break;*/
            case R.id.iv_level_4:
             /*   if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
                    checkParentDialog();
                    return;
                }
                selLevel = 4;
                money = level3Cost;
                if (selLevel <= level) {
                    ViewUtils.makeToast(context, "您已升级", 500);
                    return;
                }
// TODO: 2019/10/9 金额
                showVipDialog(selLevel + "");*/





            case R.id.iv_level_5:
            case R.id.iv_level_6:
                ViewUtils.makeToast(context, "请联系客服开通", 1500);
                break;
        }
    }

    private void showVipDialog(final String level) {
        /**
         * 充值dialog
         */
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_pay, null);
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .show();

        view.findViewById(R.id.tv_alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // TODO: 2019/4/3 支付宝支付
                loadPayData("z", level);
//                startActivity(new Intent(context, QrCodePayActivity.class)
//                        .putExtra("paytype", "z")
//                        .putExtra("money", money)
//                        .putExtra("level", level)
//                        .putExtra("type", "M"));
            }
        });
        view.findViewById(R.id.tv_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // TODO: 2019/4/3 微信支付
                loadPayData("w", level);
//                startActivity(new Intent(context, QrCodePayActivity.class)
//                        .putExtra("paytype", "w")
//                        .putExtra("money", money)
//                        .putExtra("level", level)
//                        .putExtra("type", "M"));
            }
        });

    }


    /**
     * 支付
     *
     * @param type
     */
    private void loadPayData(final String type, String level) {
        // : 2019/4/30 金额
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "890001");
        httpParams.put("5", CommonUtils.formatNewFen(money));
        httpParams.put("8", type);
        httpParams.put("43", level);
        httpParams.put("41", "M");
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

}
