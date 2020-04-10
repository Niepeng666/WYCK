package com.linglingyi.com.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.callback.CalendarSelectCallback;
import com.linglingyi.com.event.PlanCloseEvent;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.ChannelBean;
import com.linglingyi.com.model.PreviewPlanModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.KeyBoardUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.NewSimpleCalendarDialogFragment;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MakeDesignActivity extends BaseActivity {


    PreviewPlanModel previewPlanModel = new PreviewPlanModel();
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.iv_bank_icon)
    ImageView ivBankIcon;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.ll_bank_name)
    LinearLayout llBankName;
    @BindView(R.id.tv_bank_account)
    TextView tvBankAccount;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_limit)
    TextView tvLimit;
    @BindView(R.id.tv_billDay)
    TextView tvBillDay;
    @BindView(R.id.tv_payDay)
    TextView tvPayDay;
    @BindView(R.id.bind_item)
    LinearLayout bindItem;
    @BindView(R.id.et_inputPayAmount)
    EditText etInputPayAmount;
    @BindView(R.id.tv_moneyLimit)
    TextView tvMoneyLimit;
    @BindView(R.id.tv_payStartDay)
    TextView tvPayStartDay;
    @BindView(R.id.ll_payStartDay)
    LinearLayout llPayStartDay;
    @BindView(R.id.tv_payEndDay)
    TextView tvPayEndDay;
    @BindView(R.id.ll_payEndDay)
    LinearLayout llPayEndDay;
    @BindView(R.id.tv_payCycleLimitDesc)
    TextView tvPayCycleLimitDesc;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.caidan)
    RelativeLayout caidan;
    @BindView(R.id.tishiyu)
    TextView tishiyu;
    @BindView(R.id.rb_save_psw)
    CheckBox rbSavePsw;
    @BindView(R.id.tv_choiceArea)
    TextView tvChoiceArea;
    @BindView(R.id.ll_open_address)
    LinearLayout llOpenAddress;
    @BindView(R.id.bt_calculateWorkingFund)
    Button btCalculateWorkingFund;
    @BindView(R.id.tv_workingFundDesc)
    TextView tvWorkingFundDesc;
    @BindView(R.id.tv_workingFund)
    TextView tvWorkingFund;
    @BindView(R.id.zhouzhuanlay)
    RelativeLayout zhouzhuanlay;
    @BindView(R.id.tv_payFeeDesc)
    TextView tvPayFeeDesc;
    @BindView(R.id.tv_payFee)
    TextView tvPayFee;
    @BindView(R.id.tv_payTimesFeeDesc)
    TextView tvPayTimesFeeDesc;
    @BindView(R.id.tv_payTimesFee)
    TextView tvPayTimesFee;
    @BindView(R.id.tv_feeLossAmountDesc)
    TextView tvFeeLossAmountDesc;
    @BindView(R.id.tv_feeLossAmount)
    TextView tvFeeLossAmount;
    @BindView(R.id.viewa)
    View viewa;
    @BindView(R.id.tv_fees)
    TextView tvFees;
    @BindView(R.id.tv_pendingPayAmount)
    TextView tvPendingPayAmount;
    @BindView(R.id.zonge)
    RelativeLayout zonge;
    @BindView(R.id.ll_calculateWorkingFund)
    LinearLayout llCalculateWorkingFund;
    @BindView(R.id.bt_previewPlan)
    Button btPreviewPlan;
    /**
     * 是否计算成功
     */
    private boolean isCalculate = false;
    /**
     * 自选的省市和商户
     */
    private HashMap<String, Area> area;
    private String industy_Json;
    /**
     * 旧开始时间
     */
    private String oldStartTime;
    /**
     * 旧结束时间
     */
    private String oldEndTime;
    /**
     * 旧金额
     */
    private String oldAmt;

    private int oldPayAmountPerDay = 1;

    /**
     * 每日还款笔数
     */
    private List<String> list = new ArrayList<>();
    //    private Calendar calendar;
    private ArrayAdapter<String> adapter;
    /**
     * 每日几笔一还
     */
    private int payAmountPerDay = 1;
    /**
     * 全额还 true 余额还false
     */
    private boolean zhia;
    /**
     * 信用卡信息
     */
    private BindCard mBindCard;
    /**
     * 通道信息
     */
    private ChannelBean.Channel mChannel;
    private JSONArray industyInfos;
    private String bankAccount, limit, billDay, payDay, acqcode, isLuodi, isZiXuan;
    private String bindId;
    private String startDate, endDate, dayPeriod, oldDayPeriod;
    private NewSimpleCalendarDialogFragment mNewSimpleCalendarDialogFragment;
    private List<Date> selectedDates = new ArrayList<>();
    private String cityId, oldCityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_make_design;
    }

    @Override
    public void initData() {
        tvTitle.setText("制定计划");
        zhia = getIntent().getBooleanExtra("zhia", false);
        mBindCard = (BindCard) getIntent().getSerializableExtra("bindCard");
        mChannel = (ChannelBean.Channel) getIntent().getSerializableExtra("channel");
        bankAccount = mBindCard.getBANK_ACCOUNT();
        limit = mBindCard.getLIMIT_MONEY();
        billDay = mBindCard.getBILL_DAY();
        payDay = String.valueOf(mBindCard.getREPAYMENT_DAY());
        bindId = mBindCard.getID();
        acqcode = mChannel.getAcqcode();
        isLuodi = mChannel.getIsluodi();
        isZiXuan = mChannel.getIszixuan();
        tvPayStartDay.setMovementMethod(ScrollingMovementMethod.getInstance());
        tvPayStartDay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN
                        || event.getAction() == MotionEvent.ACTION_MOVE) {
                    //按下或滑动时请求父节点不拦截子节点
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //抬起时请求父节点拦截子节点
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });
//        calendar = Calendar.getInstance();
//        if (zhia) {
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        tvPayStartDay.setText(DateUtil.formateDateTOYMD(calendar.getTime()));
//        startDate = tvPayStartDay.getText().toString().trim();
        list = new ArrayList<>();
        list.add("每日还款1笔");
        list.add("每日还款2笔");
        adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, list);
        adapter.setDropDownViewResource(R.layout.spiner_drop_down_style);
        spinner.setAdapter(adapter);
        String name = StorageCustomerInfo02Util.getInfo("bankAccountName", this);
        tvUserName.setText(name);

        String bankAccount = mBindCard.getBANK_ACCOUNT();
        if (!StringUtil.isEmpty(bankAccount) && bankAccount.length() > 4) {
            String bankNum2 = bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
            tvBankAccount.setText("尾号：" + bankNum2);
        }
        if (zhia) {
            btCalculateWorkingFund.setText("计算手续费");
            tishiyu.setVisibility(View.GONE);
            caidan.setVisibility(View.GONE);
            llOpenAddress.setVisibility(View.VISIBLE);
            tvMoneyLimit.setText("注：还款金额不能低于500,不能超过20万");
        } else {
            btCalculateWorkingFund.setText("计算周转金");
            tishiyu.setVisibility(View.VISIBLE);
            caidan.setVisibility(View.VISIBLE);
            tvMoneyLimit.setText("注：还款金额不能低于500,不能超过20万");
        }
        tvBankName.setText(MyApplication.bankCodeList.get(mBindCard.getBANK_NAME()));
        Utils.initBankCodeColorIcon(mBindCard.getBANK_NAME(), ivBankIcon, this);
        tvLimit.setText(limit);
        tvBillDay.setText(billDay);
        tvPayDay.setText(payDay);
        initListener();
    }

    private void initListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payAmountPerDay = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etInputPayAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    int value = Integer.parseInt(s.toString());
                    if (value < 500) {
                        tvMoneyLimit.setTextColor(Color.RED);
                    } else {
                        tvMoneyLimit.setTextColor(Color.parseColor("#b4acac"));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    int value = Integer.parseInt(s.toString());
                    if (zhia) {
                        if (value > 20 * 10000) {
                            s.clear();
                            s.append("200000");
                        }
                    } else {
                        if (value > 20 * 10000) {
                            s.clear();
                            s.append("200000");
                        }
                    }
                }
            }
        });
    }

    /**
     * 提交计划后，自动关闭页面
     *
     * @param event
     */
    @Subscribe
    public void onEvent(PlanCloseEvent event) {
        ViewUtils.overridePendingTransitionBack(context);
    }

    @OnClick({R.id.iv_back, R.id.ll_payStartDay, R.id.tv_payStartDay, R.id.ll_payEndDay, R.id.bt_calculateWorkingFund, R.id.bt_previewPlan, R.id.tv_choiceArea})
    public void onViewClicked(View view) {
        if (CommonUtils.isFastDoubleClick2()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_payStartDay:
            case R.id.ll_payStartDay:
                KeyBoardUtils.hideKeyboard(etInputPayAmount);
            mNewSimpleCalendarDialogFragment = NewSimpleCalendarDialogFragment.getInstance(zhia);
            mNewSimpleCalendarDialogFragment.setCalendarSelectCallback(new CalendarSelectCallback() {
                @Override
                public void success(List<Date> dateList, String day) {
                    selectedDates = dateList;
                    if (selectedDates.size() > 0) {
                        startDate = DateUtil.formateDateTOYMD(selectedDates.get(0));
                        endDate = DateUtil.formateDateTOYMD(selectedDates.get(selectedDates.size() - 1));
                    }
                    StringBuilder sb_days = new StringBuilder();
                    for (Date date : selectedDates) {
                        sb_days.append(DateUtil.formateDateTOYMD(date)).append(",");
                    }
                    dayPeriod = sb_days.substring(0, sb_days.length() - 1);
//                        startDate = day;
                    tvPayStartDay.setText(day);
                }
            });

            mNewSimpleCalendarDialogFragment.show(getSupportFragmentManager(), "start");
            break;
            case R.id.ll_payEndDay:
                KeyBoardUtils.hideKeyboard(etInputPayAmount);
                mNewSimpleCalendarDialogFragment = NewSimpleCalendarDialogFragment.getInstance(zhia);
                mNewSimpleCalendarDialogFragment.setCalendarSelectCallback(new CalendarSelectCallback() {
                    @Override
                    public void success(List<Date> dateList, String day) {
                        endDate = day;
                        tvPayEndDay.setText(day);
                    }
                });

                mNewSimpleCalendarDialogFragment.show(getSupportFragmentManager(), "end");
                break;
            case R.id.bt_calculateWorkingFund:

                if (CheckInfoIfComplete()) {
                    if (area == null) {
                        ViewUtils.makeToast(context, "请先开启自选地区", 500);
                        return;
                    }
//                    dayPeriod = DateUtil.getDateBetweenTwoDate(startDate, endDate);
                    if (zhia) {
                        zhouzhuanlay.setVisibility(View.GONE);
                        viewa.setVisibility(View.VISIBLE);
                        zonge.setVisibility(View.VISIBLE);
                        // TODO: 2019/4/1
                        calculateQYKMoney();
                    } else {
                        zhouzhuanlay.setVisibility(View.VISIBLE);
                        viewa.setVisibility(View.VISIBLE);
                        zonge.setVisibility(View.VISIBLE);
                        calculateYKMoney();
                    }
                } else {
                    isCalculate = false;
                    return;
                }
                break;
            case R.id.bt_previewPlan:
                // : 2019/4/1 预览计划
                goPreviewPlan();
                break;
            case R.id.tv_choiceArea:
                if (!rbSavePsw.isChecked()) {
                    ViewUtils.makeToast(context, "请先开启自选地区", 500);
                    return;
                }
                startActivityForResult(new Intent(context, ChoiceAreaActivity.class)
                        .putExtra("acqCode", acqcode)
                        .putExtra("onlyP_C", true)
                        .putExtra("bindid", bindId)
                        .putExtra("channel", mChannel), 1);
                break;
            default:
                break;
        }
    }

    /**
     * 计算全额还手续费
     */
    private void calculateQYKMoney() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390048");
        httpParams.put("7", payAmountPerDay + "");
        httpParams.put("8", etInputPayAmount.getText().toString());

        httpParams.put("10", dayPeriod);
        httpParams.put("11", bankAccount);
        httpParams.put("12", bindId);
        httpParams.put("43", acqcode);
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

                    oldCityId = cityId;

                    btPreviewPlan.setVisibility(View.VISIBLE);
                    oldAmt = etInputPayAmount.getText().toString();
                    oldStartTime = startDate;
                    oldEndTime = endDate;
                    oldDayPeriod = dayPeriod;
                    oldPayAmountPerDay = payAmountPerDay;
                    isCalculate = true;
                    llCalculateWorkingFund.setVisibility(View.VISIBLE);
                    BigDecimal fee = CommonUtils.formatNewWithScale(model.getStr9(), 2);
                    BigDecimal timesFee = CommonUtils.formatNewWithScale(model.getStr7(), 2);

                    String dates = model.getStr10();
                    if (dates.contains(",")) {
                        //多日
                        startDate = dates.substring(0, dates.indexOf(","));
                        endDate = dates.substring(dates.lastIndexOf(",") + 1);
                    } else {//单日
                        startDate = dates;
                        endDate = dates;
                    }
                    //手续费
                    tvPayFee.setText(String.valueOf(fee));
                    tvPayTimesFee.setText(String.valueOf(timesFee));
                    BigDecimal totalFee = fee.add(timesFee);
                    tvPendingPayAmount.setText(String.valueOf(totalFee));


                    previewPlanModel.setWorkingFund("0");
                    previewPlanModel.setTimesFee(timesFee.toString());
                    previewPlanModel.setFee(fee.toString());
                    previewPlanModel.setStartDate(startDate);
                    previewPlanModel.setEndDate(endDate);
                    previewPlanModel.setDayTimes(payAmountPerDay + "");
                    previewPlanModel.setAcqcode(acqcode);
                    previewPlanModel.setF57(model.getStr57());
                    previewPlanModel.setRepayAmount(CommonUtils.formatNewWithScale(etInputPayAmount.getText().toString(), 2).toString());
                    previewPlanModel.setTotalServiceFee(totalFee.toString());
                }
            }
        });
    }

    /**
     * 进入计划预览页面
     */
    private void goPreviewPlan() {
        if (!isCalculate) {
            if (CheckInfoIfComplete()) {
                ViewUtils.makeToast(this, zhia ? "请先计算手续费" : "请先计算周转金", 1500);
            }
            return;
        }
        if (!checkDateChange()) {
            ViewUtils.makeToast(this, "数据已修改，请重新计算" + (zhia ? "请先计算手续费" : "请先计算周转金"), 1500);
            return;
        }
        if (!StringUtil.isEmpty(cityId) && !cityId.equals(oldCityId)) {
            ViewUtils.makeToast(this, "数据已修改，" + (zhia ? "请先计算手续费" : "请先计算周转金"), 1500);
            return;
        }

//        if (!zhia) {
        if (checkCustomIndustry() && area == null) {
            ViewUtils.makeToast(this, "请选择地区", 1500);
            return;
        }
        if (area == null) {
            HashMap<String, Area> map = new HashMap<>();
            Area area = new Area();
            area.setId("-1");
            area.setDivisionName("");
            map.put("province", area);
            previewPlanModel.setArea(map);
        } else {
            previewPlanModel.setArea(area);
            previewPlanModel.setGround(true);
        }
        previewPlanModel.setIndustryJson(industy_Json);
//        }
        previewPlanModel.setIsLuodi(isLuodi);
        previewPlanModel.setIsZiXuan(isZiXuan);

        previewPlanModel.setZhia(zhia);

        Intent intent = new Intent(context, PreviewPlanActivity.class);
        intent.putExtra("previewModel", previewPlanModel);
        intent.putExtra("bindCard", mBindCard);
        startActivity(intent);


    }

    /**
     * 检查内容是否有变动
     */
    private boolean checkDateChange() {

        if (TextUtils.equals(oldAmt, etInputPayAmount.getText().toString())
                && TextUtils.equals(oldDayPeriod, dayPeriod)
                ) {
            if (!zhia) {
                return oldPayAmountPerDay == payAmountPerDay;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否需要自选商户
     *
     * @return
     */
    private boolean checkCustomIndustry() {
        return TextUtils.equals(isLuodi, "1") && TextUtils.equals(isZiXuan, "1");
    }

    /**
     * 计算周转金
     */
    private void calculateYKMoney() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "193000");
        httpParams.put("7", payAmountPerDay + "");
        httpParams.put("8", etInputPayAmount.getText().toString());
        httpParams.put("9", "2");
        httpParams.put("10", dayPeriod);
        httpParams.put("11", bankAccount);
        httpParams.put("35", area.get("city").getId());
        httpParams.put("36", area.get("province").getId());
        httpParams.put("12", bindId);
        httpParams.put("43", acqcode);
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

                    oldCityId = cityId;

                    btPreviewPlan.setVisibility(View.VISIBLE);
                    oldAmt = etInputPayAmount.getText().toString();
                    oldStartTime = startDate;
                    oldEndTime = endDate;
                    oldDayPeriod = dayPeriod;
                    oldPayAmountPerDay = payAmountPerDay;
                    isCalculate = true;
                    llCalculateWorkingFund.setVisibility(View.VISIBLE);
                    BigDecimal fee = CommonUtils.formatNewWithScale(model.getStr9(), 2);
                    BigDecimal timesFee = CommonUtils.formatNewWithScale(model.getStr7(), 2);
                    //需要预留的金额
                    BigDecimal workingFund = CommonUtils.formatNewWithScale(model.getStr40(), 2);
                    String dates = model.getStr10();
                    if (dates.contains(",")) {
                        //多日
                        startDate = dates.substring(0, dates.indexOf(","));
                        endDate = dates.substring(dates.lastIndexOf(",") + 1);
                    } else {//单日
                        startDate = dates;
                        endDate = dates;
                    }
                    //周转金
                    tvWorkingFund.setText(String.valueOf(workingFund));
                    //手续费
                    tvPayFee.setText(String.valueOf(fee));
                    tvPayTimesFee.setText(String.valueOf(timesFee));
                    tvFees.setText("周转金总额");
                    BigDecimal totalFee = workingFund.add(fee).add(timesFee);
                    tvPendingPayAmount.setText(String.valueOf(totalFee));
//                    if (checkCustomIndustry()) {
//                        llOpenAddress.setVisibility(View.VISIBLE);
//                    }

                    previewPlanModel.setWorkingFund(workingFund.toString());
                    previewPlanModel.setTimesFee(timesFee.toString());
                    previewPlanModel.setFee(fee.toString());
                    previewPlanModel.setStartDate(startDate);
                    previewPlanModel.setEndDate(endDate);
                    previewPlanModel.setDayTimes(payAmountPerDay + "");
                    previewPlanModel.setAcqcode(acqcode);
                    previewPlanModel.setF57(model.getStr57());
                    previewPlanModel.setRepayAmount(CommonUtils.formatNewWithScale(etInputPayAmount.getText().toString(), 2).toString());
                    previewPlanModel.setTotalFee(totalFee.toString());
                    previewPlanModel.setTotalServiceFee(fee.add(timesFee).toString());
                }
            }
        });
    }

    private boolean CheckInfoIfComplete() {
        String inputPayAmount = etInputPayAmount.getText().toString().trim();
        String startDay = tvPayStartDay.getText().toString();
        String endDay = tvPayEndDay.getText().toString();
        if (TextUtils.isEmpty(inputPayAmount)) {
            ViewUtils.makeToast(context, "请输入还款金额", 1000);
            return false;
        }
        long payAmount = Long.parseLong(inputPayAmount);
        if (payAmount < 500) {
            ViewUtils.makeToast(context, "还款金额不得小于500", 1000);
            return false;
        }
        if (selectedDates.size() == 0) {
            ViewUtils.makeToast(context, "请选择还款日期", 1000);
            return false;
        }
//        if (StringUtil.isEmpty(endDay)) {
//            ViewUtils.makeToast(context, "请选择还款结束时间", 1000);
//            return false;
//        }

        long interval = DateUtil.getDayBetweenTWodDate(startDate, endDate);
        if (interval > 25) {
            tvPayCycleLimitDesc.setTextColor(Color.RED);
            ViewUtils.makeToast(context, "还款周期不能超过25天", 1000);
            return false;
        }
//        Date startDates = null, endDates = null;
//        try {
//            startDates = DateUtil.parseDateYMD(startDate);
//            endDates = DateUtil.parseDateYMD(endDate);
//            int result = startDates.compareTo(endDates);
//            if (result < 0) {
//                return true;
//            } else if (result == 0) {
//                ViewUtils.makeToast(context, "还款结束时间与还款开始时间不能为同一天", 1500);
//                return false;
//            } else {
//                ViewUtils.makeToast(context, "还款结束时间不能早于还款开始时间", 1500);
//                return false;
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // : 2019/4/1 选择了商户后
            area = (HashMap<String, Area>) data.getSerializableExtra("data");
            industy_Json = data.getStringExtra("industry_JSON");
            if (!TextUtils.isEmpty(industy_Json)) {
                industyInfos = JSONArray.parseArray(industy_Json);
            }
            cityId = area.get("city").getId();
            tvChoiceArea.setText(String.format("%s-%s", area.get("province").getDivisionName(), area.get("city").getDivisionName()));
            btPreviewPlan.setVisibility(View.VISIBLE);

        }

    }
}
