package com.linglingyi.com.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.BankCardEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.CardPlanList;
import com.linglingyi.com.model.PlanAllModel;
import com.linglingyi.com.model.PlanItem;
import com.linglingyi.com.model.PlanSmallDetailModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.MyListView;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 计划详情页
 */
public class PlanDetailActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_bank_icon)
    ImageView ivBankIcon;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.ll_bank_name)
    LinearLayout llBankName;
    @BindView(R.id.tv_pay_title)
    TextView tvPayTitle;
    @BindView(R.id.tv_bank_account)
    TextView tvBankAccount;
    @BindView(R.id.tv_repayCycle)
    TextView tvRepayCycle;
    @BindView(R.id.bind_item)
    LinearLayout bindItem;
    @BindView(R.id.tv_preRepayAmount)
    TextView tvPreRepayAmount;
    @BindView(R.id.tv_payed_amount)
    TextView tvPayedAmount;
    @BindView(R.id.tv_orderStatus)
    TextView tvOrderStatus;
    @BindView(R.id.tv_planProgress)
    TextView tvPlanProgress;
    @BindView(R.id.tv_channel_name)
    TextView tvChannelName;
    @BindView(R.id.tv_payType)
    TextView tvPayType;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_pay_fee)
    TextView tvPayFee;
    @BindView(R.id.tv_times_fee)
    TextView tvTimesFee;
    @BindView(R.id.tv_decrease_money)
    TextView tvDecreaseMoney;
    @BindView(R.id.myListView)
    MyListView myListView;
    @BindView(R.id.btn_stop_start)
    Button btnStopStart;
    @BindView(R.id.delete_btn)
    Button deleteBtn;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tv_level_fee)
    TextView tvLevelFee;
    @BindView(R.id.ll_btn)
    LinearLayout llBtn;
    private PlanItem mPlanItem;
    private BindCard mBindCard;
    private List<CardPlanList> mList = new ArrayList<>();
    private MyAdapter mMyAdapter;
    private boolean showMore = false, panduan;
    private LinearLayout ll_lookMore;
    private String status;
    private boolean isRefresh, zhia;
    private String type;

    /**
     * 取消/启用计划时刷新数据
     */
    private void refreshData() {
        mList.clear();
        isRefresh = true;
        loadSmallPlan();
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.btn_stop_start, R.id.delete_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_right:
                break;
            case R.id.btn_stop_start:
                if (!"10C".equals(status) && !"10D".equals(status)) {
//                    不是取消状态
                    cancelPlanRequest();
                } else {
                    requestPlanStatus();
                }

                break;
            case R.id.delete_btn:
                showDeleteDialog();
                break;
//            case R.id.ll_descriptionPreFee:
//                ViewUtils.showTipDialog(context, "预交手续费说明", "");
//                break;
//
//            case R.id.ll_descriptionPreTimesFee:
//                ViewUtils.showTipDialog(context, "预计笔数费说明", "");
//                break;
        }
    }

    private void showDeleteDialog() {
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("是否删除计划，计划删除将无法恢复")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ("10A".equals(status) || "10B".equals(status)) {
                            ViewUtils.makeToast(context, "请先取消计划再删除！", 1000);
                            return;
                        }
                        deletePlan();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    /**
     * 删除计划
     */
    private void deletePlan() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390002");
        httpParams.put("8", mPlanItem.getPlanId());
        httpParams.put("42", getMerNo());
        httpParams.put("43", mBindCard.getBANK_ACCOUNT());
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
                    ViewUtils.makeToast(context, "删除成功", 1500);
                    EventBus.getDefault().post(new BankCardEvent());
                    finish();
                }
            }


        });
    }

    /**
     * 获取是否有其他执行计划
     */
    private void requestPlanStatus() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190212");
        httpParams.put("42", getMerNo());
        httpParams.put("43", mBindCard.getBANK_ACCOUNT());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    List<PlanAllModel> list = JSONArray.parseArray(model.getStr57(), PlanAllModel.class);
                    for (PlanAllModel allmodel :
                            list) {
                        if (allmodel.getBANK_ACCOUNT().equals(mBindCard.getBANK_ACCOUNT())) {
                            String status = allmodel.getSTATUS();
                            if ("10A".equals(status) || "10B".equals(status)) {
                                ViewUtils.makeToast(context, "该卡有其他正在执行的计划，当前计划暂不可以开启", 1000);
                                panduan = false;
                                break;
                            } else if ("10D".equals(status) || "10C".equals(status)) {
                                panduan = true;
                            }
                        }
                    }
                    if (panduan) {
                        cancelPlanRequest();
                    }
                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }
        });
    }

    /**
     * 启动/取消计划
     */
    private void cancelPlanRequest() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190214");
        httpParams.put("7", "10C".equals(status) || "10D".equals(status) ? "1" : "0");
        httpParams.put("8", mPlanItem.getPlanId());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    if ("10C".equals(status) || "10D".equals(status)) {
                        ViewUtils.makeToast(context, "启用成功", 500);
                        btnStopStart.setText("取消计划");
                        btnStopStart.setBackgroundResource(R.drawable.button_corners_blue_corner_5);
                        btnStopStart.setTextColor(Color.WHITE);
                        status = "10B";
                    } else {
                        ViewUtils.makeToast(context, "取消成功", 500);
                        btnStopStart.setText("启用计划");
                        btnStopStart.setBackgroundResource(R.drawable.shape_solid_gray_corner_5);
                        btnStopStart.setTextColor(Color.WHITE);
                        status = "10D";
                    }
                    EventBus.getDefault().post(new BankCardEvent());
                    refreshData();
                    tvOrderStatus.setText(String.format("%s", getStatus(status)));
                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }
        });
    }

    /**
     * 一键补单
     */
    private void singleSupplement() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "890009");
        httpParams.put("43", mPlanItem.getPlanId());
        loadingDialog.show();
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
                    ViewUtils.makeToast(context, "补单提交成功，请耐心等待...", 1000);
                    EventBus.getDefault().post(new BankCardEvent());
                    finish();
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
        return R.layout.activity_plan_detail;
    }

    @Override
    public void initData() {
        tvTitle.setText("计划详情");
        mPlanItem = (PlanItem) getIntent().getSerializableExtra("planItem");
        type = mPlanItem.getType();
        mBindCard = (BindCard) getIntent().getSerializableExtra("bindCard");
        ll_lookMore = (LinearLayout) myListView.findViewById(R.id.ll_lookMore);
        ll_lookMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMore = !showMore;
                isRefresh = false;
                lookMore(false);
            }
        });
        status = mPlanItem.getPlanStatus();
        String planId = mPlanItem.getPlanId();
        String bankAccount = mBindCard.getBANK_ACCOUNT();
        if (!StringUtil.isEmpty(bankAccount) && bankAccount.length() > 4) {
            String bankNum1 = bankAccount.substring(0, 4);
            String bankNum2 = bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
            tvBankAccount.setText(bankNum1 + " **** **** " + bankNum2);
        }
        tvBankName.setText(MyApplication.bankCodeList.get(mBindCard.getBANK_NAME()));
        Utils.initBankCodeColorIcon(mBindCard.getBANK_NAME(), ivBankIcon, this);

        tvRepayCycle.setText(mPlanItem.getPlanCycle());
        tvOrderStatus.setText(getStatus(status));
        tvPlanProgress.setText(mPlanItem.getPlanProgress());
        if ("10C".equals(status) || "10D".equals(status)) {
            btnStopStart.setText("启用计划");
            btnStopStart.setBackgroundResource(R.drawable.shape_solid_gray_corner_5);
            btnStopStart.setTextColor(Color.WHITE);
        } else {
            btnStopStart.setText("停止计划");
            btnStopStart.setBackgroundResource(R.drawable.button_corners_blue_corner_5);
        }

        tvPayedAmount.setText("已还金额：￥" + mPlanItem.getPaidAmountNow());
        tvPayFee.setText("手续费：" + mPlanItem.getPrePayFee());
        tvTimesFee.setText("笔数费：" + mPlanItem.getPreTimesAmount());
        tvPreRepayAmount.setText("还款金额：￥" + mPlanItem.getShouldPayNow());
        tvDecreaseMoney.setText("自用减免：￥" + mPlanItem.getDISCOUNTS_MONEY());

        String content = "<html><font color=\"#808080\">您当前为" +
                "</font><font color=\"#FF0000\">" + getLevelName() + "</font><font color=\"#808080\">, </font>" +
                "<font color=\"#808080\">自用</font></font><font color=\"#FF0000\">" + "可省" + "</font>" +
                "<font color=\"#808080\">手续费￥" + mPlanItem.getDISCOUNTS_MONEY() + "</font></html>";
        tvLevelFee.setText(fromHtml(content));
        tvChannelName.setText(mPlanItem.getACQ_NAME());
        BigDecimal fee = CommonUtils.formatNewWithScale(mPlanItem.getPrePayFee(), 2);
        BigDecimal timesFee = CommonUtils.formatNewWithScale(mPlanItem.getPreTimesAmount(), 2);
        if ("还款形式：0笔/日".equals(mPlanItem.getPayType())) {
            tvPayType.setVisibility(View.GONE);
            tvTotalPrice.setText("手续费小计：" + String.valueOf(fee.add(timesFee)));
            llBtn.setVisibility(View.GONE);
            zhia = true;
        } else {
            tvPayType.setVisibility(View.VISIBLE);
            BigDecimal workingFund = CommonUtils.formatNewWithScale(mPlanItem.getWorkingFund(), 2);
            tvTotalPrice.setText("周转金总额：" + String.valueOf(workingFund.add(fee).add(timesFee)));
            //需要预留的金额
            zhia = false;
        }
        tvPayType.setText(mPlanItem.getPayType());

        mMyAdapter = new MyAdapter();
        myListView.setAdapter(mMyAdapter);
        loadSmallPlan();
    }

    @SuppressWarnings("deprecation")
    public Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    private void lookMore(boolean isFirst) {
        if (mList.size() <= 5) {
            int measuredHeight = ll_lookMore.getMeasuredHeight();
            ll_lookMore.setPadding(0, -measuredHeight, 0, 0);
        } else {
            if (isRefresh) {
                return;
            }
            RotateAnimation rotateAnimation = null;
            TextView tv_lookMore = (TextView) myListView.findViewById(R.id.tv_lookMore);
            String desc = "查看更多";
            if (tv_lookMore.getText().toString().trim().equals(desc)) {
                tv_lookMore.setText("收起更多");
                rotateAnimation = new RotateAnimation(0, 180, 1, 0.5f, 1, 0.5f);
            } else {
                tv_lookMore.setText(desc);
                if (!isFirst) {
                    rotateAnimation = new RotateAnimation(180, 0, 1, 0.5f, 1, 0.5f);
                }
            }
            if (rotateAnimation != null) {
                rotateAnimation.setDuration(1000);
                rotateAnimation.setFillAfter(true);
            }
            myListView.findViewById(R.id.iv_lookMore).setAnimation(rotateAnimation);
        }
    }

    /**
     * 获取小计划
     */
    private void loadSmallPlan() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190213");
        httpParams.put("8", mPlanItem.getPlanId());
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
                    LogUtils.i("57=" + model.getStr57());
                    List<PlanSmallDetailModel> list = JSONArray.parseArray(model.getStr57(), PlanSmallDetailModel.class);
                    for (PlanSmallDetailModel plan :
                            list) {
                        CardPlanList cardPlanList = new CardPlanList();
                        BigDecimal money = CommonUtils.formatNewWithScale(String.valueOf(plan.getMoney()), 2);
                        BigDecimal toMoney = CommonUtils.formatNewWithScale(String.valueOf(plan.getToMoney()), 2);
                        cardPlanList.setFromMoney(money);
                        cardPlanList.setToMoney(toMoney);
                        cardPlanList.setPayFee(money.subtract(toMoney));
                        cardPlanList.setFromCard(plan.getFromIncreaseId());
                        cardPlanList.setToCard(plan.getToIncreaseId());
                        if (plan.getPlanTime() != null) {
                            cardPlanList.setPlanTime(plan.getPlanTime().getTime());
                        }
                        cardPlanList.setStatus(plan.getStatus());
                        cardPlanList.setPlanItemId(plan.getId());
                        cardPlanList.setType(plan.getType());
                        cardPlanList.setArea(plan.getCustomizeCity());
                        cardPlanList.setIndustry(plan.getCityIndustry());
                        cardPlanList.setMessage(plan.getMessage());
                        cardPlanList.setFlag(plan.isFlag());
                        mList.add(cardPlanList);
                    }
                    lookMore(true);
                    mMyAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private String getStatus(String status) {
        if ("10A".equals(status)) {
            status = "未执行";
        } else if ("10B".equals(status)) {
            status = "执行中";
        } else if ("10D".equals(status)) {
            status = "暂停";
        } else if ("10C".equals(status)) {
            status = "失败";
        } else if ("10E".equals(status)) {
            status = "完成";
        }
        return status;
    }

    private String getLevelName() {
        int level = StringUtil.stringToInt(mPlanItem.getLevel());
        String leve;
        switch (level) {
            case 1:
                leve = "普通用户";
                break;
            case 2:
                leve = "VIP会员";
                break;
            case 3:
                leve = "高级VIP";
                break;
            case 4:
                leve = "初级代理";
                break;
            case 5:
                leve = "高级代理";
                break;
            case 6:
                leve = "钻石";
                break;
            case 7:
                leve = "区领主";
                break;
            case 8:
                leve = "市领主";
                break;
            case 9:
                leve = "省领主";
                break;
            default:
                leve = "未知等级";
                break;

        }
        return leve;

    }

    static class ViewHolder {
        TextView tv_date;
        TextView tv_money;
        TextView tv_payType;
        ImageView iv_dealStatus;
        TextView tv_payStatus;
        TextView tv_area;
        TextView tv_industry;
        TextView tv_fail_reason;
        TextView tv_fix;
    }

    class MyAdapter extends BaseAdapter {
        public MyAdapter() {
        }

        @Override
        public int getCount() {
            int size = mList.size();
            if (size > 5 && !showMore) {
                size = 5;
            }
            return size;
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_plan_detail, null);
                viewHolder = new ViewHolder();
                viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
                viewHolder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
                viewHolder.tv_payType = (TextView) convertView.findViewById(R.id.tv_payType);
                viewHolder.iv_dealStatus = (ImageView) convertView.findViewById(R.id.iv_dealStatus);
                viewHolder.tv_payStatus = (TextView) convertView.findViewById(R.id.tv_payStatus);
                viewHolder.tv_area = (TextView) convertView.findViewById(R.id.tv_area);
                viewHolder.tv_industry = (TextView) convertView.findViewById(R.id.tv_industry);
                viewHolder.tv_fail_reason = (TextView) convertView.findViewById(R.id.tv_fail_reason);
                viewHolder.tv_fix = (TextView) convertView.findViewById(R.id.tv_fix);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            CardPlanList cardPlanList = mList.get(position);

            if (cardPlanList.getArea() != null && !cardPlanList.getArea().isEmpty()) {
                viewHolder.tv_area.setText("地区：" + cardPlanList.getArea());
                viewHolder.tv_area.setVisibility(View.VISIBLE);
                viewHolder.tv_industry.setText("行业：" + cardPlanList.getIndustry());
                viewHolder.tv_industry.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tv_area.setVisibility(View.GONE);
                viewHolder.tv_industry.setVisibility(View.GONE);
            }
            String consumeOrRepay = cardPlanList.getFromMoney().setScale(2).toString();
            String payStatus = "";
            if (zhia) {
                if ("pay".equals(cardPlanList.getType())) {
                    viewHolder.tv_payType.setBackgroundResource(R.drawable.shape_solid_orange_corner_5);
                    viewHolder.tv_payType.setText("消费");
                    payStatus = "失败";
                } else if ("sale".equals(cardPlanList.getType())) {
                    viewHolder.tv_payType.setBackgroundResource(R.drawable.shape_solid_dark_green_corner_5);
                    viewHolder.tv_payType.setText("手续费");
                    payStatus = "失败";
                } else {
                    viewHolder.tv_payType.setBackgroundResource(R.drawable.shape_solid_blue_corner_5);
                    viewHolder.tv_payType.setText("还款");
                    payStatus = "失败";
                }
            } else {
                if ("sale".equals(cardPlanList.getType())) {

                    viewHolder.tv_payType.setBackgroundResource(R.drawable.shape_solid_orange_corner_5);
                    viewHolder.tv_payType.setText("消费");
                    payStatus = "失败";
                } else {
                    viewHolder.tv_payType.setBackgroundResource(R.drawable.shape_solid_blue_corner_5);
                    viewHolder.tv_payType.setText("还款");
                    payStatus = "失败";
                }
            }
            LogUtils.i("status=" + cardPlanList.getStatus());
            String status = cardPlanList.getStatus();
            int dealStatusDrawableId = R.drawable.icon_wait_deal;
            viewHolder.tv_fix.setVisibility(View.GONE);
            switch (status) {
                case "10A":
                case "10D":
                    dealStatusDrawableId = R.drawable.icon_wait_deal;
                    viewHolder.tv_payStatus.setVisibility(View.INVISIBLE);
                    viewHolder.tv_fail_reason.setVisibility(View.GONE);
                    break;
                case "10B":
                    dealStatusDrawableId = R.drawable.icon_success_deal;
                    viewHolder.tv_payStatus.setVisibility(View.INVISIBLE);
                    viewHolder.tv_fail_reason.setVisibility(View.GONE);
                    break;
                case "10C":
                    dealStatusDrawableId = R.drawable.icon_plan_detail_fail;
                    viewHolder.tv_payStatus.setVisibility(View.VISIBLE);
                    viewHolder.tv_payStatus.setText(payStatus);
                    viewHolder.tv_fail_reason.setVisibility(View.VISIBLE);
                    viewHolder.tv_fail_reason.setText(cardPlanList.getMessage());
                    if ("10C".equals(type)) {
                        viewHolder.tv_fix.setVisibility(View.GONE);
                    } else {
                        if (cardPlanList.isFlag())
                            viewHolder.tv_fix.setVisibility(View.VISIBLE);
                        else
                            viewHolder.tv_fix.setVisibility(View.GONE);
                    }
                    break;
            }
            if ("订单状态：已取消".equals(tvOrderStatus.getText().toString()) || "订单状态：失败".equals(tvOrderStatus.getText().toString())) {
                if (!"10B".equals(status)) {
                    dealStatusDrawableId = R.drawable.icon_plan_detail_fail;
                    viewHolder.tv_payStatus.setText(payStatus);
                }
            }
            viewHolder.iv_dealStatus.setBackgroundResource(dealStatusDrawableId);
            viewHolder.tv_money.setText(consumeOrRepay);
            SimpleDateFormat dateFormat = null;
            if ("10C".equals(mPlanItem.getType())) {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            viewHolder.tv_date.setText(dateFormat.format(new Date(cardPlanList.getPlanTime())) + "");
            viewHolder.tv_fix.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    singleSupplement();
                }
            });
            return convertView;
        }


    }


}
