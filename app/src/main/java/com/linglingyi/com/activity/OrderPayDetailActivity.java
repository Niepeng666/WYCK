package com.linglingyi.com.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.OrderPayEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.OrderModel;
import com.linglingyi.com.model.PayResult;
import com.linglingyi.com.model.WeiXinModel;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.IntentConstant;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.TimeUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.utils.wechat.PayListener;
import com.linglingyi.com.utils.wechat.WechatPay;
import com.linglingyi.com.viewone.CustomCountDownTimerView;
import com.linglingyi.com.viewone.FontIconView;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/23
 */
public class OrderPayDetailActivity extends BaseActivity {
    private static final int SDK_PAY_FLAG = 1;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.custom_tv_count_down)
    CustomCountDownTimerView customTvCountDown;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.ll_weixin)
    LinearLayout llWeixin;
    @BindView(R.id.ll_alipay)
    LinearLayout llAlipay;
    @BindView(R.id.ll_change)
    LinearLayout llChange;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_item_title)
    TextView tvItemTitle;
    @BindView(R.id.wechat_choose)
    FontIconView wechatChoose;
    @BindView(R.id.zhifubao_choose)
    FontIconView zhifubaoChoose;
    @BindView(R.id.card_choose)
    FontIconView cardChoose;
    @BindView(R.id.ll_count_down)
    LinearLayout llCountDown;
    @BindView(R.id.ll_order_close)
    LinearLayout llOrderClose;

    private OrderModel orderModel;
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
                        ViewUtils.makeToast(context, "支付成功", 1000);

                        paySuccess();
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
    private String type = "wxpay";
    private String price;

    private void paySuccess() {
        EventBus.getDefault().post(new OrderPayEvent());
        goOrderDetail(orderModel.getId());
        finish();
    }

    @OnClick({R.id.iv_back, R.id.ll_weixin, R.id.ll_alipay, R.id.ll_change, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_weixin:
                type = "wxpay";
                int[] attrArray = {R.attr.theme_bg_color};
                TypedArray mTypedArray = context.obtainStyledAttributes(attrArray);
                zhifubaoChoose.setTextColor(ContextCompat.getColor(context, R.color.gray));
                cardChoose.setTextColor(ContextCompat.getColor(context, R.color.gray));
                wechatChoose.setTextColor(mTypedArray.getColor(0, 0xFF000000));
                break;
            case R.id.ll_alipay:
                type = "alipay";
                int[] attrArray1 = {R.attr.theme_bg_color};
                TypedArray mTypedArray1 = context.obtainStyledAttributes(attrArray1);
                wechatChoose.setTextColor(ContextCompat.getColor(context, R.color.gray));
                cardChoose.setTextColor(ContextCompat.getColor(context, R.color.gray));
                zhifubaoChoose.setTextColor(mTypedArray1.getColor(0, 0xFF000000));
                break;
            case R.id.ll_change:
                type = "ye";
                int[] attrArray2 = {R.attr.theme_bg_color};
                TypedArray mTypedArray2 = context.obtainStyledAttributes(attrArray2);
                zhifubaoChoose.setTextColor(ContextCompat.getColor(context, R.color.gray));
                wechatChoose.setTextColor(ContextCompat.getColor(context, R.color.gray));
                cardChoose.setTextColor(mTypedArray2.getColor(0, 0xFF000000));
                break;
            case R.id.btn_submit:
                if ("ye".equals(type)) {
// TODO: 2019/9/4 余额支付
                    goPay();
                } else {
                    loadPayData();
                }
                break;
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            default:
                break;
        }
    }

    /**
     * 支付
     */
    private void goPay() {
        loadingDialog.show();
//        String moneyFen = CommonUtils.formatNewFen(price);
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "790104");
        httpParams.put("21", orderModel.getId());
        httpParams.put("22", getMerId());
        httpParams.put("23", type);
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
                    ViewUtils.makeToast(context,
                            "支付成功", 500);
                    paySuccess();
                }
            }
        });
    }

    private void goOrderDetail(String orderId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("orderId", orderId);
        startActivity(intent);
    }


    /**
     * 支付
     *
     * @param
     */
    private void loadPayData() {
        // : 2019/4/30 金额
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "790104");
        httpParams.put("21", orderModel.getId());
        httpParams.put("22", getMerId());
        httpParams.put("23", type);
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
                    final String orderInfo = model.getStr41();
                    if (isAlipay()) {
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
                    }
                    if (isWeixin()) {
                        WeiXinModel wechatModel = JSONObject.parseObject(orderInfo, WeiXinModel.class);
                        WechatPay.getInstance().startWeChatPay(wechatModel.getAppid(), wechatModel.getPartnerid(), wechatModel.getPrepayid(), wechatModel.getNoncestr(), wechatModel.getTimestamp(), wechatModel.getSign(), new PayListener() {
                            @Override
                            public void onPaySuccess() {
                                ViewUtils.makeToast(context,
                                        "支付成功", 500);
                                paySuccess();
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

    private boolean isAlipay() {
        return "alipay".equals(type);
    }

    private boolean isWeixin() {
        return "wxpay".equals(type);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_order_pay;
    }

    @Override
    public void initData() {
        tvTitle.setText("付款详情");
        price = getIntent().getStringExtra("price");
        orderModel = (OrderModel) getIntent().getSerializableExtra(IntentConstant.ORDER);
        fillData();
        initListener();
    }

    private void fillData() {
        tvItemTitle.setText(orderModel.getGoodsName() + orderModel.getGoodsSpecification() + "x" + orderModel.getGoodsCount());
        tvPrice.setText("￥" + orderModel.getGoodsPrice());//todo 生成订单后没有传pay
        if (orderModel.getId().length() >= 16) {
            tvOrderNo.setText("订单编号：" + orderModel.getId().substring(0, 16));
        } else {
            tvOrderNo.setText("订单编号：" + orderModel.getId());
        }
        checkCountDown(orderModel.getCreateTime().getTime());
    }

    /**
     * 判断订单未付款，订单超过一个小时未付款，自动关闭订单
     *
     * @param createTime
     */
    private void checkCountDown(long createTime) {
        LogUtils.i("TimeUtils.string2Millis(createTime)=" + createTime);
        long pastTime = TimeUtils.getTimeSpan(System.currentTimeMillis(), createTime, TimeUtils.MSEC);
        long msc = TimeUtils.getTimeSpan(orderModel.getUpdateTime().getTime(), orderModel.getCreateTime().getTime(), TimeUtils.MSEC);
        if (pastTime >= msc) {
            //订单关闭
            shutDownCountDown();
        } else {
            long remainTime = msc - pastTime;
            customTvCountDown.start(remainTime);
        }
    }

    private void shutDownCountDown() {
        llOrderClose.setVisibility(View.VISIBLE);
        llCountDown.setVisibility(View.GONE);
    }

    private void initListener() {
        customTvCountDown.setTimerFinishListener(new CustomCountDownTimerView.TimerFinishListener() {
            @Override
            public void onTimerFinish() {
                shutDownCountDown();
                // TODO: 2019/10/23 订单关闭
                customTvCountDown.destroy();

            }
        });

    }
}
