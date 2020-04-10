package com.linglingyi.com.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.PayResult;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.ViewUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/29
 */
public class VipActivity extends BaseActivity {
    private static final int SDK_PAY_FLAG = 1;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_vip_pic)
    ImageView ivVipPic;
    @BindView(R.id.zhifubao_choose)
    ImageView zhifubaoChoose;
    @BindView(R.id.ll_alipay)
    LinearLayout llAlipay;
    @BindView(R.id.wechat_choose)
    ImageView wechatChoose;
    @BindView(R.id.ll_weixin)
    LinearLayout llWeixin;
    @BindView(R.id.card_choose)
    ImageView cardChoose;
    @BindView(R.id.ll_card)
    LinearLayout llCard;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_level_name)
    TextView tvLevelName;
    private String money;
    private String levelName;
    private String type = "z";
    private Integer level;

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

    @OnClick({R.id.iv_back, R.id.btn_submit, R.id.ll_alipay, R.id.ll_weixin, R.id.ll_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_submit:
                // : 2019/7/25 提交支付
                if (level > 1) {
                    ViewUtils.makeToast(context, "您已经是" + levelName, 500);
                    return;
                }
                if ("b".equals(type)) {
                    Intent intent = new Intent(context, VipPayBankCardListActivity.class);
                    intent.putExtra("is2Pay", true);
                    intent.putExtra("isVip", true);
                    intent.putExtra("money", money);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(context, QrCodePayActivity.class)
                            .putExtra("paytype", type)
                            .putExtra("money", money)
                            .putExtra("title",type.equals("z")?"支付宝扫码支付":"微信扫码支付")
                            .putExtra("type", "M"));
                }
                break;
            case R.id.ll_alipay:
                type = "z";
                zhifubaoChoose.setImageResource(R.drawable.check_circle_sel);
                wechatChoose.setImageResource(R.drawable.check_circle);
                cardChoose.setImageResource(R.drawable.check_circle);
                break;
            case R.id.ll_weixin:
                type = "w";
                wechatChoose.setImageResource(R.drawable.check_circle_sel);
                zhifubaoChoose.setImageResource(R.drawable.check_circle);
                cardChoose.setImageResource(R.drawable.check_circle);
                break;
            case R.id.ll_card:
                type = "b";
                zhifubaoChoose.setImageResource(R.drawable.check_circle);
                wechatChoose.setImageResource(R.drawable.check_circle);
                cardChoose.setImageResource(R.drawable.check_circle_sel);
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_vip;
    }

    @Override
    public void initData() {
        tvTitle.setText("会员升级");
        money = getIntent().getStringExtra("money");
        level = StorageCustomerInfo02Util.getIntInfo(context, "level", 1);
        switch (level) {
            case 1:
                levelName = "普通会员";
                break;
            case 2:
                levelName = "VIP";
                break;
            case 3:
                levelName = "高级VIP";
                break;
            case 4:
                levelName = "初级代理";
                break;
            case 5:
                levelName = "高级代理";
                break;
            case 6:
                levelName = "钻石";
                break;
            case 7:
                levelName = "区领主";
                break;
                case 8:
                levelName = "市领主";
                break;
                case 9:
                levelName = "省领主";
                break;
            default:
                levelName = "未知等级";
                break;

        }
        tvLevelName.setText("当前等级：" + levelName);
        GlideUtils.loadImage(context, StorageCustomerInfo02Util.getInfo("vipUpPic", context), ivVipPic);
    }
}
