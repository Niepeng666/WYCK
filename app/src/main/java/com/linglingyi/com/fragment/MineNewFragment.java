package com.linglingyi.com.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.BankManagerActivity;
import com.linglingyi.com.activity.BenefitListActivity;
import com.linglingyi.com.activity.LoginNewActivity;
import com.linglingyi.com.activity.MyClientActivity;
import com.linglingyi.com.activity.NewGuideActivity;
import com.linglingyi.com.activity.RecordListActivity;
import com.linglingyi.com.activity.SettingActivity;
import com.linglingyi.com.activity.TradeActivity;
import com.linglingyi.com.activity.WithdrawalActivity;
import com.linglingyi.com.activity.X5WebViewActivity;
import com.linglingyi.com.base.BaseFragment;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.UrlModel;
import com.linglingyi.com.model.UserInfoModel;
import com.linglingyi.com.utils.ActivitySwitcher;
import com.linglingyi.com.utils.BitmapManage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.GlideCircleTransform;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者 chenlanxin
 * @创建日期 2019/5/23 14:30
 * @功能
 **/
public class MineNewFragment extends BaseFragment {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_today_earnings)
    TextView tvTodayEarnings;
    @BindView(R.id.ly_today_earnings)
    LinearLayout lyTodayEarnings;
    @BindView(R.id.tv_yesterday_earnings)
    TextView tvYesterdayEarnings;
    @BindView(R.id.ly_yesterday_earnings)
    LinearLayout lyYesterdayEarnings;
    @BindView(R.id.tv_cumulative_earnings)
    TextView tvCumulativeEarnings;
    @BindView(R.id.ly_accumulated_earnings)
    LinearLayout lyAccumulatedEarnings;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_withdrawal)
    TextView tvWithdrawal;
    @BindView(R.id.ll_trade)
    LinearLayout llTrade;
    @BindView(R.id.ll_auth)
    LinearLayout llAuth;
    @BindView(R.id.ll_card)
    LinearLayout llCard;
    @BindView(R.id.ll_team)
    LinearLayout llTeam;
    @BindView(R.id.ll_guide)
    LinearLayout llGuide;
    @BindView(R.id.ll_security)
    LinearLayout llSecurity;
    Unbinder unbinder;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            loadBenefitData();
        }
    }

    /**
     * 获取头像，收益数据
     */
    private void loadBenefitData() {
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190112");
        httpParams.put("42", getMerNo());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    GlideUtils.loadAvatar(context, model.getStr48(), ivAvatar);
                    if (!StringUtil.isEmpty(model.getStr48())) {
                        StorageCustomerInfo02Util.putInfo(context, "headImage", model.getStr48());
                    }
                    try {
                        List<UserInfoModel> list = JSONArray.parseArray(model.getStr57(), UserInfoModel.class);
                        if (list.size() == 0) {
                            return;
                        }
                        UserInfoModel userInfoModel = list.get(0);
                        StorageCustomerInfo02Util.putInfo(context, "freezeStatus", userInfoModel.getFreezeStatus());
                        tvTodayEarnings.setText(model.getStr44());
                        tvYesterdayEarnings.setText(model.getStr45());
                        tvCumulativeEarnings.setText(model.getStr46());
                        tvBalance.setText(model.getStr43());
                        //tvBalance.setText(CommonUtils.formatNewWithScale(userInfoModel.getTotalMoney() + "", 2).toString());
                        String level = userInfoModel.getLevel();
                        String leve;
                        switch (level) {
                            case "1":
                                leve = "普通用户";
                                break;
                            case "2":
                                leve = "VIP会员";
                                break;
                            case "3":
                                leve = "高级VIP";
                                break;
                            case "4":
                                leve = "初级代理";
                                break;
                            case "5":
                                leve = "高级代理";
                                break;
                            case "6":
                                leve = "钻石";
                                break;
                            case "7":
                                leve = "区领主";
                                break;
                            case "8":
                                leve = "市领主";
                                break;
                                case "9":
                                leve = "省领主";
                                break;
                            default:
                                leve = "未知等级";
                                break;

                        }
                        tvLevel.setText(leve);
                        StorageCustomerInfo02Util.putInfo(context, "withdrawFee", model.getStr25());

                        updateAuthData(userInfoModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    /**
     * 实名认证后刷新数据
     *
     * @param userInfoModel
     */
    private void updateAuthData(UserInfoModel userInfoModel) {
        String phone = userInfoModel.getPhone();
        String name = userInfoModel.getMerchantCnName();
        StorageCustomerInfo02Util.putInfo(context, "merchantCnName", userInfoModel.getMerchantCnName());
        tvName.setText(!StringUtil.isEmpty(name) ? name : phone);

        String freeStatus = userInfoModel.getFreezeStatus();
        tvPhone.setText(ActivitySwitcher.AUTH_PASS.equals(freeStatus) ? CommonUtils.translateShortNumber(phone, 3, 4) : phone);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == Constant.IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                uploadImage(BitmapManage.bitmapToString(images.get(0).path, 600, 600));

            } else {
                ViewUtils.makeToast(context, "没有数据", 500);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isAdded() && !isHidden()) {
            loadBenefitData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 上传图片
     *
     * @param imageData
     */
    private void uploadImage(String imageData) {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190949");
        httpParams.put("42", getMerNo());
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
                    GlideUtils.loadAvatar(context, model.getStr57(), ivAvatar);
                    StorageCustomerInfo02Util.putInfo(context, "headImage", model.getStr57());
                }
            }
        });

    }

    @OnClick({R.id.iv_avatar, R.id.iv_right, R.id.tv_withdrawal, R.id.ll_trade, R.id.ll_auth, R.id.ll_card,
            R.id.ll_team, R.id.ll_security,
            R.id.ly_accumulated_earnings, R.id.ly_today_earnings,
            R.id.ly_yesterday_earnings, R.id.ll_guide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar://头像
                if (!PermissionUtil.CAMERA(context)) {
                    return;
                }
                Intent imgIntent = new Intent(context, ImageGridActivity.class);
                startActivityForResult(imgIntent, Constant.IMAGE_PICKER);
                break;
            case R.id.ly_accumulated_earnings://累计收益
                if (checkCustomerInfoCompleteShowToast()) {
                    startActivity(new Intent(context, BenefitListActivity.class).putExtra("title", "收益明细").putExtra("day", "3"));
                }
                break;
            case R.id.ly_today_earnings://今日明细
                if (checkCustomerInfoCompleteShowToast()) {
                    startActivity(new Intent(context, BenefitListActivity.class).putExtra("title", "今日收益").putExtra("day", "1"));
                }
                break;
            case R.id.ly_yesterday_earnings://昨日明细
                if (checkCustomerInfoCompleteShowToast()) {
                    startActivity(new Intent(context, BenefitListActivity.class).putExtra("title", "昨日收益").putExtra("day", "2"));
                }
                break;
            case R.id.tv_withdrawal://提现
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                if (!checkBindCard()) {
                    return;
                }

                startActivity(new Intent(context, WithdrawalActivity.class).putExtra("money", tvBalance.getText().toString())
                        .putExtra("type", 1));

                break;
            case R.id.ll_trade://交易明细
                if (checkCustomerInfoCompleteShowToast()) {
                    startActivity(new Intent(context, TradeActivity.class));
                }
                break;
            case R.id.ll_auth://实名认证
                if (checkCustomerInfoCompleteShowToast()) {
                    ViewUtils.makeToast(context, "您已审核通过", 500);
                }
                break;
            case R.id.ll_card://银行卡管理
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                if (!checkBindCard()) {
                    return;
                }
                startActivity(new Intent(context, BankManagerActivity.class));

                break;
            case R.id.ll_team://我的团队
                if (checkCustomerInfoCompleteShowToast()) {
                    startActivity(new Intent(context, MyClientActivity.class));
                }
                break;

            case R.id.ll_security://安全保障
                if (StorageCustomerInfo02Util.getIntInfo(context, "bx", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                loadUrlData("4", "保险业务");
                break;


            case R.id.iv_right://设置
                startActivity(new Intent(context, SettingActivity.class));
                break;
            case R.id.ll_guide:
                startActivity(new Intent(context, NewGuideActivity.class));
                break;
        }
    }

    private void loadUrlData(final String type, final String title) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "690034");
        httpParams.put("41", getMerId());
//        httpParams.put("43", type);//1 贷款 2 办卡
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
                    UrlModel urlModel = JSONObject.parseObject(model.getStr42(), UrlModel.class);
                    if ("2".equals(type)) {
                        goWebView(urlModel.getBK(), title);
                    } else if ("1".equals(type)) {
                        goWebView(urlModel.getDK(), title);
                    } else if ("3".equals(type)) {
                        goWebView(urlModel.getJF(), title);
                    } else if ("4".equals(type)) {
                        goWebView(urlModel.getDS(), title);
                    }
                }
            }
        });
    }

    /**
     * 跳转至h5链接
     *
     * @param
     */
    private void goWebView(String url, String title) {

        Intent webIntent = new Intent(context, X5WebViewActivity.class);
        webIntent.putExtra("title", title);
        webIntent.putExtra("url", url);
        startActivity(webIntent);
    }

    private void showExitDialog() {
        Button confirmBt, cancleBt;
        final Dialog mydialog = new Dialog(context, R.style.MyProgressDialog);
        mydialog.setContentView(R.layout.dialog_exit);
        mydialog.setCanceledOnTouchOutside(false);
        confirmBt = mydialog.findViewById(R.id.bt_cancelPlan);
        cancleBt = mydialog.findViewById(R.id.bt_suspendCancel);
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.isFastDoubleClick()) {
                    return;
                }
                //清除存储终端信息的缓存数据
                if (StorageCustomerInfo02Util.clearKey(context)) {
                    goLogin();
                    mydialog.dismiss();
                } else {
                    ViewUtils.makeToast(context, "数据清除失败", 1500);
                }
            }
        });
        cancleBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mydialog.dismiss();
            }

        });
        mydialog.show();
    }

    /**
     * 跳转至登录页
     */
    private void goLogin() {
        Intent intent = new Intent(context, LoginNewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
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
        return R.layout.fragment_my;
    }

    @Override
    public void initData(View v) {
        ivBack.setVisibility(View.GONE);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.drawable.setting);
        GlideUtils.loadAvatar(context, "", ivAvatar);
    }
}
