package com.linglingyi.com.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linglingyi.com.utils.LogUtils;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.linglingyi.com.adapter.ChooseAccountAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.PlanCloseEvent;
import com.linglingyi.com.model.AccountModel;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.ChannelBean;
import com.linglingyi.com.model.PreviewPlanModel;
import com.linglingyi.com.utils.CheckOutMessage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MakeQuickDesignActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.et_total_money)
    EditText etTotalMoney;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.et_working_fund)
    EditText etWorkingFund;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.rb_save_psw)
    CheckBox rbSavePsw;
    @BindView(R.id.ll_open_address)
    LinearLayout llOpenAddress;

    private String totalMoney, workingFund;
    private BindCard mBindCard;
    private ChooseAccountAdapter mChooseAccountAdapter;
    private List<ChannelBean.Channel> mList = new ArrayList<>();
    private ChannelBean.Channel mSelChannel;
    private int selPosition = -1;
    /**
     *
     */
    private PreviewPlanModel previewPlanModel = new PreviewPlanModel();
    private JSONArray industyInfos;

    /**
     * 自选的省市和商户
     */
    private HashMap<String, Area> area;
    private String industy_Json;
    private int oldPosition;

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
        return R.layout.activity_make_new_design;
    }

    @Override
    public void initData() {
        tvTitle.setText("快速还款");
        mBindCard = (BindCard) getIntent().getSerializableExtra("BindCard");

        mChooseAccountAdapter = new ChooseAccountAdapter(mList, false);
        rvList.addItemDecoration(new DividerItemDecoration(context, OrientationHelper.VERTICAL));
        rvList.setLayoutManager(new LinearLayoutManager(context));

        mChooseAccountAdapter.bindToRecyclerView(rvList);
        mChooseAccountAdapter.setEmptyView(R.layout.layout_empty, rvList);

        initListener();
        loadData();
    }

    private void initListener() {
        mChooseAccountAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                oldPosition = selPosition;

                if (selPosition != position) {
                    selPosition = position;
                    mSelChannel = mList.get(selPosition);
                    requestBindStatus();
                }
            }
        });
        etTotalMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    int value = Integer.parseInt(s.toString());
                    if (value < 500) {
                        tvTip.setTextColor(Color.RED);
                    } else {
                        tvTip.setTextColor(Color.parseColor("#b4acac"));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    int value = Integer.parseInt(s.toString());
                    if (value > 20 * 10000) {
                        s.clear();
                        s.append("200000");
                    }

                }
            }
        });

    }

    /**
     * 判断是否绑卡
     */
    private void requestBindStatus() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390021");
        httpParams.put("42", getMerNo());
        httpParams.put("45", mBindCard.getBANK_ACCOUNT());
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
                    LogUtils.i("36=" + model.getStr36());
                    List<AccountModel> list = JSONArray.parseArray(model.getStr36(), AccountModel.class);
                    for (AccountModel accountModel :
                            list) {
                        if (accountModel.getKstatus().contains(mSelChannel.getAcqcode())) {
                            if (!TextUtils.equals("开通", accountModel.getStatus())) {
                                selPosition = oldPosition;
                                if (selPosition == -1) {
                                    mSelChannel = null;
                                } else {
                                    mSelChannel = mList.get(selPosition);
                                }

                                ViewUtils.makeToast(context, "未绑卡，请先绑卡", 500);

                                Intent intent = new Intent(context, BindCardActivity.class);
                                intent.putExtra("bindCard", mBindCard);
                                intent.putExtra("type", accountModel.getType());
                                intent.putExtra("category", accountModel.getCategory());
                                startActivity(intent);

                            } else {
                                for (int i = 0; i < mList.size(); i++) {
                                    mList.get(i).setCheck(false);
                                }
                                mList.get(selPosition).setCheck(true);
                                mChooseAccountAdapter.notifyDataSetChanged();
                                mSelChannel = mList.get(selPosition);
                                tvAddress.setText("");
                                area = null;
                                if (oldPosition != -1) {
                                    ViewUtils.makeToast(context, "通道已更换，请重新选择地区", 500);
                                }
                                llOpenAddress.setVisibility(checkCustomIndustry() ? View.VISIBLE : View.GONE);
                            }
                            break;
                        }
                    }

                }
            }


        });

    }

    /**
     * 判断是否需要自选商户
     *
     * @return
     */
    private boolean checkCustomIndustry() {
        String isLuodi = mSelChannel.getIsluodi();
        String isZiXuan = mSelChannel.getIszixuan();
        return TextUtils.equals(isLuodi, "1") && TextUtils.equals(isZiXuan, "1");
    }

    /**
     * 获取通道列表
     */
    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390013");
        httpParams.put("42", getMerNo());
        httpParams.put("43", "YK");
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
//                    LogUtils.i("57=" + model.getStr57());
                    ChannelBean channelBean = JSONObject.parseObject(model.getStr57(), ChannelBean.class);
                    List<ChannelBean.Channel> list = channelBean.getAcqData();
                    mList.addAll(list);
                    mChooseAccountAdapter.setNewData(mList);
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

    @OnClick({R.id.iv_back, R.id.btn_next, R.id.rl_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_next:
                totalMoney = etTotalMoney.getText().toString();
                workingFund = etWorkingFund.getText().toString();
                if (CheckOutMessage.isEmpty(context, "还款金额", totalMoney)) {
                    return;
                }
                if (CheckOutMessage.isEmpty(context, "启动金额", workingFund)) {
                    return;
                }
                if (mList.size() == 0) {
                    ViewUtils.makeToast(context, "通道为空，请联系客服", 500);
                    return;
                }
                if (selPosition == -1) {
                    ViewUtils.makeToast(context, "请先选择通道", 500);
                    return;
                }
                if (area == null) {
                    ViewUtils.makeToast(context, "请选择地区", 500);
                    return;
                }
                getWorkFund();
                break;
            case R.id.rl_address:
                if (!rbSavePsw.isChecked()){
                    ViewUtils.makeToast(context, "请先开启自选地区", 500);
                    return;
                }
                startActivityForResult(new Intent(context, ChoiceAreaActivity.class)
                        .putExtra("acqCode", mSelChannel.getAcqcode())
                        .putExtra("onlyP_C", true)
                        .putExtra("bindid", mBindCard.getID())
                        .putExtra("channel", mSelChannel), 1);
                break;
            default:
                break;
        }
    }

    /**
     * 计算周转金
     */
    private void getWorkFund() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "130013");
        httpParams.put("8", totalMoney);
        httpParams.put("11", mBindCard.getBANK_ACCOUNT());
        httpParams.put("12", mBindCard.getID());
        httpParams.put("41", workingFund);
        httpParams.put("42", getMerNo());
        httpParams.put("43", mSelChannel.getAcqcode());
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
                    String allDate = model.getStr10();
                    String[] dates = allDate.split(",");
                    String startDate = dates[0];
                    String endDate = dates[0];
                    if (dates.length > 1) {
                        endDate = dates[dates.length - 1];
                    }
                    BigDecimal fee = CommonUtils.formatNewWithScale(model.getStr9(), 2);
                    BigDecimal timesFee = CommonUtils.formatNewWithScale(model.getStr7(), 2);
                    BigDecimal workingFund = CommonUtils.formatNewWithScale(model.getStr40(), 2);
                    BigDecimal totalFee = workingFund.add(fee).add(timesFee);
                    previewPlanModel.setWorkingFund(model.getStr40());
                    previewPlanModel.setTimesFee(model.getStr7());
                    previewPlanModel.setFee(model.getStr9());
                    previewPlanModel.setStartDate(startDate);
                    previewPlanModel.setEndDate(endDate);
                    previewPlanModel.setDayTimes("3");
                    previewPlanModel.setAcqcode(model.getStr43());
                    previewPlanModel.setF57(model.getStr57());
                    previewPlanModel.setRepayAmount(CommonUtils.formatNewWithScale(model.getStr8(), 2).toString());
                    previewPlanModel.setTotalFee(totalFee.toString());
                    previewPlanModel.setTotalServiceFee(fee.add(timesFee).toString());
                    goNext();
                }
            }
        });
    }

    private void goNext() {

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
        previewPlanModel.setIsLuodi(mSelChannel.getIsluodi());
        previewPlanModel.setIsZiXuan(mSelChannel.getIszixuan());

        previewPlanModel.setZhia(false);

        Intent intent = new Intent(context, WorkingFundActivity.class);
        intent.putExtra("previewModel", previewPlanModel);
        intent.putExtra("bindCard", mBindCard);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            area = (HashMap<String, Area>) data.getSerializableExtra("data");
            industy_Json = data.getStringExtra("industry_JSON");
            if (!TextUtils.isEmpty(industy_Json)) {
                industyInfos = JSONArray.parseArray(industy_Json);
            }
            tvAddress.setText(String.format("%s-%s", area.get("province").getDivisionName(), area.get("city").getDivisionName()));


        }

    }
}
