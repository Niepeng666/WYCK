package com.linglingyi.com.viewone.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.ChoiceAreaActivity;
import com.linglingyi.com.activity.PreviewPlanActivity;
import com.linglingyi.com.callback.CalendarSelectCallback;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.ChannelBean;
import com.linglingyi.com.model.PreviewPlanModel;
import com.linglingyi.com.model.WorkingFundModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.KeyBoardUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.NewSimpleCalendarDialogFragment;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/27 10:47
 * @功能 公告
 **/
public class MakeDesignDialog extends DialogFragment {


    public Dialog loadingDialog;
    Unbinder unbinder;
    @BindView(R.id.et_inputPayAmount)
    EditText etInputPayAmount;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.spinner_pay)
    Spinner spinnerPay;
    @BindView(R.id.ll_pay_model)
    LinearLayout llPayModel;
    @BindView(R.id.spinner_pay_number)
    Spinner spinnerPayNumber;
    @BindView(R.id.ll_pay_number)
    LinearLayout llPayNumber;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.btn_calculate)
    TextView btnCalculate;
    @BindView(R.id.btn_previewPlan)
    Button btnPreviewPlan;
    @BindView(R.id.iv_total_price_tip)
    ImageView ivTotalPriceTip;
    @BindView(R.id.tv_total_title)
    TextView tvTotalTitle;
    private BindCard model;
    /**
     * 每日还款笔数
     */
    private List<String> list = new ArrayList<>();
    /**
     * 消还模式
     */
    private List<String> salePayList = new ArrayList<>();
    /**
     * 还款笔数
     */
    private ArrayAdapter<String> adapter;
    /**
     * 还款笔数
     */
    private ArrayAdapter<String> salePayAdapter;
    /**
     * 还款日期
     */
    private NewSimpleCalendarDialogFragment mNewSimpleCalendarDialogFragment;
    /**
     * 选择的日期
     */
    private List<Date> selectedDates = new ArrayList<>();
    private String cityId, oldCityId;
//    private JSONArray industyInfos;
    /**
     * 每日几笔一还
     */
    private int payAmountPerDay = 1, oldPayAmountPerDay;
    /**
     * 消还模式
     */
    private int salePayModel = 1, oldSalePayModel;
    /**
     * 是否计算成功
     */
    private boolean isCalculate = false;
    /**
     * 自选的省市和商户
     */
    private HashMap<String, Area> area;
    private String industy_Json;
    private PreviewPlanModel previewPlanModel = new PreviewPlanModel();
    private String startDate, endDate, dayPeriod, oldDayPeriod, oldAmt;
    private boolean zhia;
    private Activity mActivity;
    private ChannelBean.Channel channel;

    public static MakeDesignDialog getInstance(BindCard model, boolean zhia, ChannelBean.Channel channel) {
        MakeDesignDialog dialog = new MakeDesignDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", model);
        bundle.putSerializable("zhia", zhia);
        bundle.putSerializable("channel", channel);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.normal_Dialog);
        model = (BindCard) getArguments().getSerializable("model");
        zhia = getArguments().getBoolean("zhia");
        channel = (ChannelBean.Channel) getArguments().getSerializable("channel");
        mActivity = getActivity();
        loadingDialog = ViewUtils.createLoadingDialog(mActivity, getString(R.string.loading_wait), false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_date, R.id.tv_city, R.id.btn_calculate, R.id.btn_previewPlan, R.id.iv_total_price_tip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
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
                        tvDate.setText(day);
                        checkInfoIfComplete();
                    }
                });

                mNewSimpleCalendarDialogFragment.show(getChildFragmentManager(), "start");
                break;
            case R.id.tv_city:
                startActivityForResult(new Intent(mActivity, ChoiceAreaActivity.class)
                                .putExtra("acqCode", channel.getAcqcode())
                                .putExtra("onlyP_C", true)
                                .putExtra("zhia", zhia)
                                .putExtra("bindid", model.getID())
                        , 1);
                break;
            case R.id.btn_calculate:
                if (!checkInfoIfComplete()) {
                    return;
                }
                long interval = DateUtil.getDayBetweenTWodDate(startDate, endDate);
                if (interval > 25) {
                    ViewUtils.makeToast(mActivity, "还款周期不能超过25天", 1000);
                    return;
                }
                if (zhia) {
                    calculateQYKMoney();
                } else {
                    calculateYKMoney();
                }
                break;
            case R.id.btn_previewPlan:
                goPreviewPlan();
                break;
            case R.id.iv_total_price_tip:
                // : 2019/10/14 周转金提示
                PlanTotalPriceDialog planTotalPriceDialog = PlanTotalPriceDialog.getInstance(previewPlanModel, true);
                planTotalPriceDialog.show(getChildFragmentManager(), "price");
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
        httpParams.put("11", model.getBANK_ACCOUNT());
        httpParams.put("12", model.getID());
        httpParams.put("43", channel.getAcqcode());
        httpParams.put("42", StorageCustomerInfo02Util.getInfo("customerNum", mActivity));
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(mActivity, BaseEntity.class) {
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
                    ivTotalPriceTip.setVisibility(View.VISIBLE);
                    oldCityId = cityId;

                    btnPreviewPlan.setVisibility(View.VISIBLE);
                    oldAmt = etInputPayAmount.getText().toString();
                    oldDayPeriod = dayPeriod;
                    oldPayAmountPerDay = payAmountPerDay;
                    oldSalePayModel = salePayModel;
                    isCalculate = true;
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
//                    tvPayFee.setText(String.valueOf(fee));
//                    tvPayTimesFee.setText(String.valueOf(timesFee));
                    BigDecimal totalFee = fee.add(timesFee);
//                    tvPendingPayAmount.setText(String.valueOf(totalFee));
                    tvTotalPrice.setText(String.valueOf(totalFee));

                    previewPlanModel.setWorkingFund("0");
                    previewPlanModel.setTimesFee(timesFee.toString());
                    previewPlanModel.setFee(fee.toString());
                    previewPlanModel.setStartDate(startDate);
                    previewPlanModel.setEndDate(endDate);
                    previewPlanModel.setDayTimes(payAmountPerDay + "");
                    previewPlanModel.setAcqcode(channel.getAcqcode());
                    previewPlanModel.setF57(model.getStr57());
                    previewPlanModel.setRepayAmount(CommonUtils.formatNewWithScale(etInputPayAmount.getText().toString(), 2).toString());
                    previewPlanModel.setTotalServiceFee(totalFee.toString());
                }
            }
        });
    }

    /**
     * 计算周转金
     */
    private void calculateYKMoney() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", channel.isChannels() ? "393000" : "193000");
        httpParams.put("7", payAmountPerDay + "");
        httpParams.put("8", etInputPayAmount.getText().toString());
        httpParams.put("9", salePayModel);
        httpParams.put("10", dayPeriod);
        httpParams.put("11", model.getBANK_ACCOUNT());
        httpParams.put("35", area.get("city").getId());
        httpParams.put("36", area.get("province").getId());
        if (channel.isChannels()) {
            httpParams.put("44", channel.getChannelType());
        }
        httpParams.put("12", model.getID());
        httpParams.put("43", channel.getAcqcode());
        httpParams.put("42", StorageCustomerInfo02Util.getInfo("customerNum", mActivity));
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(mActivity, BaseEntity.class) {
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
                    ivTotalPriceTip.setVisibility(View.VISIBLE);
                    oldCityId = cityId;

                    btnPreviewPlan.setVisibility(View.VISIBLE);
                    oldAmt = etInputPayAmount.getText().toString();
                    oldDayPeriod = dayPeriod;
                    oldPayAmountPerDay = payAmountPerDay;
                    oldSalePayModel = salePayModel;
                    isCalculate = true;
//                    llCalculateWorkingFund.setVisibility(View.VISIBLE);
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
//                    tvWorkingFund.setText(String.valueOf(workingFund));
//                    //手续费
//                    tvPayFee.setText(String.valueOf(fee));
//                    tvPayTimesFee.setText(String.valueOf(timesFee));
//                    tvFees.setText("周转金总额");
                    BigDecimal totalFee = workingFund.add(fee).add(timesFee);
//                    tvPendingPayAmount.setText(String.valueOf(totalFee));
//                    if (checkCustomIndustry()) {
//                        llOpenAddress.setVisibility(View.VISIBLE);
//                    }
                    tvTotalPrice.setText(String.valueOf(totalFee));

                    previewPlanModel.setWorkingFund(workingFund.toString());
                    previewPlanModel.setTimesFee(timesFee.toString());
                    previewPlanModel.setFee(fee.toString());
                    previewPlanModel.setStartDate(startDate);
                    previewPlanModel.setEndDate(endDate);
                    previewPlanModel.setDayTimes(payAmountPerDay + "");
                    previewPlanModel.setAcqcode(channel.getAcqcode());
                    previewPlanModel.setF57(model.getStr57());
                    previewPlanModel.setRepayAmount(CommonUtils.formatNewWithScale(etInputPayAmount.getText().toString(), 2).toString());
                    previewPlanModel.setTotalFee(totalFee.toString());
                    previewPlanModel.setTotalServiceFee(fee.add(timesFee).toString());
                }
            }
        });
    }

    /**
     * 实时判断数据是否输入完整
     *
     * @return
     */
    private boolean checkInfoIfComplete() {
        btnCalculate.setClickable(false);
        String inputPayAmount = etInputPayAmount.getText().toString().trim();
        if (TextUtils.isEmpty(inputPayAmount)) {
            btnCalculate.setBackgroundResource(R.drawable.shape_solid_gray_right_corner_5);
            btnCalculate.setTextColor(ContextCompat.getColor(mActivity, R.color.text_color));
            return false;
        }
        if (selectedDates.size() == 0) {
            btnCalculate.setBackgroundResource(R.drawable.shape_solid_gray_right_corner_5);
            btnCalculate.setTextColor(ContextCompat.getColor(mActivity, R.color.text_color));
            return false;
        }
        if (area == null) {
            btnCalculate.setBackgroundResource(R.drawable.shape_solid_gray_right_corner_5);
            btnCalculate.setTextColor(ContextCompat.getColor(mActivity, R.color.text_color));
            return false;
        }
        btnCalculate.setClickable(true);
        btnCalculate.setBackgroundResource(R.drawable.shape_solid_background_right_corner_5);
        btnCalculate.setTextColor(ContextCompat.getColor(mActivity, R.color.btn_text_color));
        return true;
    }

    /**
     * 进入计划预览页面
     */
    private void goPreviewPlan() {
        if (!isCalculate) {
            if (!checkInfoIfComplete()) {
                ViewUtils.makeToast(mActivity, zhia ? "请先计算手续费" : "请先计算周转金", 1500);
            }
            return;
        }
        if (!checkDateChange()) {
            ViewUtils.makeToast(mActivity, "数据已修改，请重新计算" + (zhia ? "请先计算手续费" : "请先计算周转金"), 1500);
            return;
        }
        if (!StringUtil.isEmpty(cityId) && !cityId.equals(oldCityId)) {
            ViewUtils.makeToast(mActivity, "数据已修改，" + (zhia ? "请先计算手续费" : "请先计算周转金"), 1500);
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
        previewPlanModel.setIsLuodi("1");
        previewPlanModel.setIsZiXuan("1");
        previewPlanModel.setChannelName(channel.getChannelName());
        previewPlanModel.setZhia(zhia);
        previewPlanModel.setChannels(channel.isChannels());
        previewPlanModel.setChannelType(channel.getChannelType());
        Intent intent = new Intent(mActivity, PreviewPlanActivity.class);
        intent.putExtra("previewModel", previewPlanModel);
        intent.putExtra("bindCard", model);
        startActivity(intent);
        dismiss();
    }

    /**
     * 检查内容是否有变动
     */
    private boolean checkDateChange() {

        if (TextUtils.equals(oldAmt, etInputPayAmount.getText().toString())
                && TextUtils.equals(oldDayPeriod, dayPeriod)
                ) {
            if (!zhia) {
                return oldPayAmountPerDay == payAmountPerDay && oldSalePayModel == salePayModel;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // : 2019/4/1 选择了商户后
            area = (HashMap<String, Area>) data.getSerializableExtra("data");
//            industy_Json = data.getStringExtra("industry_JSON");
//            if (!TextUtils.isEmpty(industy_Json)) {
//                industyInfos = JSONArray.parseArray(industy_Json);
//            }
            cityId = area.get("city").getId();
            tvCity.setText(String.format("%s-%s", area.get("province").getDivisionName(), area.get("city").getDivisionName()));
            checkInfoIfComplete();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_make_design, container);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initListener();
        return view;
    }

    private void initData() {
        if (model == null) {
            return;
        }
        tvDate.setMovementMethod(ScrollingMovementMethod.getInstance());
        tvDate.setOnTouchListener(new View.OnTouchListener() {
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

        list = new ArrayList<>();
        list.add("1次/日");
        list.add("2次/日");
        adapter = new ArrayAdapter<>(mActivity, R.layout.spinner_layout, list);
        adapter.setDropDownViewResource(R.layout.spiner_drop_down_style);
        spinnerPayNumber.setAdapter(adapter);

        salePayList = new ArrayList<>();
        salePayList.add("消一还一");
        salePayList.add("消二还一");
//        salePayList.add("消三还一");
        salePayAdapter = new ArrayAdapter<>(mActivity, R.layout.spinner_layout, salePayList);
        salePayAdapter.setDropDownViewResource(R.layout.spiner_drop_down_style);
        spinnerPay.setAdapter(salePayAdapter);


        if (zhia) {
            llPayModel.setVisibility(View.GONE);
            llPayNumber.setVisibility(View.GONE);
            tvTotalTitle.setText("手续费小计");
            btnCalculate.setText("计算手续费");
        }
    }

    private void initListener() {
        spinnerPayNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payAmountPerDay = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerPay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                salePayModel = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etInputPayAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInfoIfComplete();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
