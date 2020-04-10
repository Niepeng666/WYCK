package com.linglingyi.com.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.LordRightsModel;
import com.linglingyi.com.model.PayResult;
import com.linglingyi.com.model.WeiXinModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.utils.wechat.PayListener;
import com.linglingyi.com.utils.wechat.WechatPay;
import com.linglingyi.com.viewone.FontIconView;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/18 11:12
 * @功能
 **/
public class ApplyLordActivity extends BaseActivity {
    private static final int SDK_PAY_FLAG = 3;
    private static final int SELECTAREA = 2;
    private static final int PAY_CARD = 1;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_lord_level)
    TextView tvLordLevel;
    @BindView(R.id.ly_select_level)
    LinearLayout lySelectLevel;
    @BindView(R.id.tv_lord_city)
    TextView tvLordCity;
    @BindView(R.id.ly_select_city)
    LinearLayout lySelectCity;
    @BindView(R.id.tv_lord_area)
    TextView tvLordArea;
    @BindView(R.id.ly_select_area)
    LinearLayout lySelectArea;
    @BindView(R.id.tv_offer_now)
    TextView tvOfferNow;
    @BindView(R.id.iv_bank_icon)
    FontIconView ivBankIcon;
    @BindView(R.id.ly_bank_pay)
    LinearLayout lyBankPay;
    @BindView(R.id.iv_balance_icon)
    FontIconView ivBalanceIcon;
    @BindView(R.id.ly_balance_pay)
    LinearLayout lyBalancePay;

    @BindView(R.id.tv_off_money)
    TextView tvOffMoney;

    @BindView(R.id.btn_apply)
    Button btnApply;
    @BindView(R.id.iv_reduce)
    FontIconView ivReduce;
    @BindView(R.id.iv_add)
    FontIconView ivAdd;

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

    private String[] numbers = {"市领主", "区领主"};
    private int payType = 0;//1:余额支付；0:银行卡支付
    private int currentPrice = 0;//当前报价
    private int minMoney = 0;//报价基数
    private int nowMoney = 0;//报价金额
    private int count = 0;
    private int selectLord = 0;
    private List<Area> list = null;
    private Map<String, Area> map = null;
    /**
     * 1 城市 2地区
     */
    private String type;
    private String cityId, distinctId;
    private String money;
    private LordRightsModel mLordRightsModel;

    @OnClick({R.id.iv_back, R.id.ly_select_level, R.id.ly_select_city, R.id.ly_select_area, R.id.ly_bank_pay, R.id.ly_balance_pay, R.id.iv_reduce, R.id.iv_add, R.id.btn_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ly_select_level:
                if (mLordRightsModel != null) {
                    return;
                }
                showBottomDialog();
                break;
            case R.id.ly_select_city:
                if (mLordRightsModel != null) {
                    return;
                }
                if (selectLord == 0) {
                    ViewUtils.makeToast(context, "请先选择等级", 1000);
                    return;
                }
                type = "1";
                startActivityForResult(new Intent().setClass(context, ProvinceCityActivity.class).putExtra("chooseDistinct", false), SELECTAREA);


                break;
            case R.id.ly_select_area:
                if (mLordRightsModel != null) {
                    return;
                }
                if (selectLord == 0) {
                    ViewUtils.makeToast(context, "请先选择等级", 1000);
                    return;
                }
                if (StringUtil.isEmpty(cityId)) {
                    ViewUtils.makeToast(context, "选择地区", 1000);
                    return;
                }
                if (tvLordArea.getText().toString().contains("不可选")) {
                    return;
                }
                type = "2";
                startActivityForResult(new Intent().setClass(context, ProvinceCityActivity.class).putExtra("cityId", cityId), SELECTAREA);

                break;
            case R.id.ly_bank_pay://银行卡支付
                payType = 0;
                int[] attrArray = {R.attr.theme_bg_color};
                TypedArray mTypedArray = context.obtainStyledAttributes(attrArray);
                ivBalanceIcon.setTextColor(ContextCompat.getColor(context, R.color.gray));
                ivBankIcon.setTextColor(mTypedArray.getColor(0, 0xFF000000));
                break;
            case R.id.ly_balance_pay://余额支付
                payType = 1;
                int[] attrArray1 = {R.attr.theme_bg_color};
                TypedArray mTypedArray1 = context.obtainStyledAttributes(attrArray1);
                ivBalanceIcon.setTextColor(mTypedArray1.getColor(0, 0xFF000000));
                ivBankIcon.setTextColor(ContextCompat.getColor(context, R.color.gray));
                break;
            case R.id.iv_reduce:
                if (mLordRightsModel == null) {
                    if (selectLord == 0) {
                        ViewUtils.makeToast(context, "请先选择等级", 1000);
                        return;
                    }
                    if (StringUtil.isEmpty(cityId)) {
                        ViewUtils.makeToast(context, "请先选择城市", 1000);
                        return;
                    }
                    if (selectLord == 2 && StringUtil.isEmpty(distinctId)) {
                        ViewUtils.makeToast(context, "请选择地区", 1000);
                        return;
                    }
                }
                count--;
                if (count < 0) {
                    count = 0;
                    return;
                }
                changeMoney();
                break;
            case R.id.iv_add:
                if (mLordRightsModel == null) {

                    if (selectLord == 0) {
                        ViewUtils.makeToast(context, "请先选择等级", 1000);
                        return;
                    }
                    if (StringUtil.isEmpty(cityId)) {
                        ViewUtils.makeToast(context, "请先选择城市", 1000);
                        return;
                    }
                    if (selectLord == 2 && StringUtil.isEmpty(distinctId)) {
                        ViewUtils.makeToast(context, "请选择地区", 1000);
                        return;
                    }
                }
                count++;
                changeMoney();
                break;
            case R.id.btn_apply:
                money = tvOffMoney.getText().toString().trim();
                if (mLordRightsModel != null) {

                } else {
                    if (selectLord == 0) {
                        ViewUtils.makeToast(context, "请先选择等级", 1000);
                        return;
                    }
                    if (StringUtil.isEmpty(cityId)) {
                        ViewUtils.makeToast(context, "请先选择城市", 1000);
                        return;
                    }
                    if (selectLord == 2 && StringUtil.isEmpty(distinctId)) {
                        ViewUtils.makeToast(context, "请选择地区", 1000);
                        return;
                    }
                }
                if (Integer.parseInt(money) <= currentPrice) {
                    ViewUtils.makeToast(context, "报价金额要大于" + currentPrice, 1000);
                    return;
                }
                if (payType == 0) {
                    Intent intent = new Intent(context, VipPayBankCardListActivity.class);
                    intent.putExtra("is2Pay", true);
                    intent.putExtra("isVip", false);
                    intent.putExtra("isApplyLord", true);
                    if (mLordRightsModel != null) {
                        intent.putExtra("areaId", mLordRightsModel.getId());
                    } else {
                        intent.putExtra("areaId", "1".equals(type) ? cityId : distinctId);
                    }
                    intent.putExtra("money", money);

                    startActivityForResult(intent, PAY_CARD);
                } else {
                    applyLord();
                }
                break;
        }
    }

    /**
     * 申请领主
     */
    private void applyLord() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "393003");
        httpParams.put("35", money + "");
        httpParams.put("43", "1".equals(type) ? cityId : distinctId);
        httpParams.put("42", getMerNo());
//        httpParams.put("44", );
        httpParams.put("45", payType == 0 ? "10B" : "10A");
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
                    ViewUtils.makeToast(context, "申请已提交", 500);
                    finish();
                } else {
                    ViewUtils.makeToast(context, model.getStr39(), 500);
                }
            }
        });

    }

    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.MyProgressDialog);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_select_lord_level, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final NumberPicker numberPicker = dialog.findViewById(R.id.np);
        setNumberPickerDividerColor(numberPicker);

        //设置需要显示的内容数组
        numberPicker.setDisplayedValues(numbers);
        //设置最大最小值
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(numbers.length);
        //设置默认的位置
        numberPicker.setValue(1);
        //这里设置为不循环显示，默认值为true
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        dialog.findViewById(R.id.tv_determine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String level = numbers[numberPicker.getValue() - 1];
                tvLordLevel.setText(level);
                selectLord = numberPicker.getValue();

                if (selectLord == 1) {
                    tvLordArea.setText("不可选");
                } else {
                    tvLordArea.setText("");
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值 透明
                    pf.set(picker, new ColorDrawable(this.getResources().getColor(R.color.gray_line)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        // 分割线高度
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDividerHeight")) {
                pf.setAccessible(true);
                try {
                    int result = getResources().getDimensionPixelOffset(R.dimen.dp_0_5);
                    pf.set(picker, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void changeMoney() {
        LogUtils.i("nowMoney=" + nowMoney + "count=" + count);
        int money = nowMoney / 10 * count + nowMoney;
        if (money >= nowMoney) {
            tvOffMoney.setText(money + "");
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
                costDialog();
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
        String moneyFen = CommonUtils.formatNewFen(money);
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "890001");
        httpParams.put("5", moneyFen);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECTAREA) {
            map = (HashMap<String, Area>) data.getSerializableExtra("data");
            if ("1".equals(type)) {
                tvLordCity.setText(map.get("province").getDivisionName() + "-" + map.get("city").getDivisionName());
                cityId = map.get("city").getId();
                currentPrice = map.get("city").getPrice();

            } else {
                distinctId = map.get("distinct").getId();
                tvLordArea.setText(map.get("distinct").getDivisionName());
                currentPrice = map.get("distinct").getPrice();
            }
            minMoney = currentPrice;
            nowMoney = currentPrice;
            tvOfferNow.setText(currentPrice + "");
            tvOffMoney.setText(currentPrice + "");

        } else if (resultCode == RESULT_OK && requestCode == PAY_CARD) {
            finish();
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
        return R.layout.activity_apply_lord;
    }

    @Override
    public void initData() {
        tvTitle.setText("申请领主");

        mLordRightsModel = (LordRightsModel) getIntent().getSerializableExtra("LordRightsModel");
        if (mLordRightsModel != null) {
// TODO: 2019/10/30 cityId需要后台返回 
            currentPrice = StringUtil.stringToInt(mLordRightsModel.getPrice());
            tvOfferNow.setText(mLordRightsModel.getPrice());
            tvLordCity.setText(mLordRightsModel.getArea());
            tvLordArea.setText(StringUtil.isEmpty(mLordRightsModel.getCity()) ? "不可选" : mLordRightsModel.getCity());
            tvLordLevel.setText(StringUtil.isEmpty(mLordRightsModel.getCity()) ? "市领主" : "区领主");
            selectLord = StringUtil.isEmpty(mLordRightsModel.getCity()) ? 1 : 2;
            type = StringUtil.isEmpty(mLordRightsModel.getCity()) ? "1" : "2";
            if (StringUtil.isEmpty(mLordRightsModel.getCity())) {
                cityId = mLordRightsModel.getId();
            } else {
                distinctId = mLordRightsModel.getId();
            }
        } else {
            tvOfferNow.setText("待定");
        }
        if (currentPrice == 0) {
            tvOffMoney.setText("待定");
        } else {
            if (minMoney < currentPrice) {
                nowMoney = currentPrice;
            } else {
                nowMoney = minMoney;
            }
            tvOffMoney.setText(nowMoney + "");
        }
    }
}
