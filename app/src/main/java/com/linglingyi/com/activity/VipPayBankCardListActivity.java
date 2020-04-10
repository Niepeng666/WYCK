package com.linglingyi.com.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.linglingyi.com.adapter.BankCardListAdapter;
import com.linglingyi.com.adapter.ManagerCreditAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wuyouchuangke.com.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 会员升级银行卡支付
 */
public class VipPayBankCardListActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.cl_container)
    RelativeLayout clContainer;

    private ManagerCreditAdapter mBankCardListAdapter;
    private List<BindCard> mList = new ArrayList<>();
    /**
     * 收款 金额
     */
    private boolean isPay;
    private String money;
    private boolean isApplyLord;
    private boolean isVip;

    @Override
    public int initLayout() {
        return R.layout.activity_refresh_recycler_view;
    }

    @Override
    public void initData() {
        String title = getIntent().getStringExtra("title");
        tvTitle.setText(!StringUtil.isEmpty(title) ? title : "卡包");
        isPay = getIntent().getBooleanExtra("is2Pay", false);
        money = getIntent().getStringExtra("money");
        isApplyLord = getIntent().getBooleanExtra("isApplyLord", false);

        srlRefresh.setRefreshHeader(new ClassicsHeader(context));

        mBankCardListAdapter = new ManagerCreditAdapter(mList);
        mBankCardListAdapter.setIs2Pay(isPay);
        mBankCardListAdapter.setVip(getIntent().getBooleanExtra("isVip", false));
        mBankCardListAdapter.setMakeType(isApplyLord ? "立即付费" : "立即升级");
        rvList.setLayoutManager(new LinearLayoutManager(context));

        mBankCardListAdapter.bindToRecyclerView(rvList);
        if (!isApplyLord) {
            mBankCardListAdapter.addFooterView(getFooterView());
        }
        initListener();
        requestData();
    }

    private View getFooterView() {

        TextView tvAdd = new TextView(context);
        tvAdd.setText("+ 添加信用卡");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);
        params.setMargins(10, 50, 10, 0);
        tvAdd.setGravity(Gravity.CENTER);
        tvAdd.setBackgroundColor(Color.WHITE);
        tvAdd.setLayoutParams(params);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }

                startActivity(new Intent(context, AddBankCardActivity.class));
            }
        });
        return tvAdd;
    }


    private void initListener() {
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                requestData();
            }
        });

        mBankCardListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtils.i("positon=" + position);
                if (isPay) {
                    showDialog(mList.get(position));
                }

            }
        });
    }

    /**
     * 获取信用卡列表
     */
    private void requestData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190932");
        httpParams.put("42", getMerNo());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
                srlRefresh.finishRefresh();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                srlRefresh.finishRefresh();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {

                    mList = JSONArray.parseArray(model.getStr57(), BindCard.class);
                    mBankCardListAdapter.setNewData(mList);
                }
            }
        });
    }

    /**
     * 是否支付弹框
     *
     * @param bindCard
     */
    private void showDialog(final BindCard bindCard) {
        final Dialog checkDialog = new Dialog(context, R.style.MyProgressDialog);
        checkDialog.setContentView(R.layout.chose_dialog_upload);
        checkDialog.setCanceledOnTouchOutside(false);
        Button dialog_confirmBt = (Button) checkDialog.findViewById(R.id.left_bt);
        Button cancleButton = (Button) checkDialog.findViewById(R.id.right_bt);
        TextView dialog_title_text = ((TextView) checkDialog.findViewById(R.id.title_text));
        dialog_title_text.setText("确认使用该卡支付?");
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDialog.dismiss();
            }
        });
        dialog_confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDialog.dismiss();
                if (isApplyLord) {
                    goApplyLord(bindCard);
                } else {
                    gotoVipUp(bindCard);
                }
            }
        });


        checkDialog.show();
    }

    /**
     * 申请领主
     *
     * @param bindCard
     */
    private void goApplyLord(BindCard bindCard) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "393003");
        httpParams.put("35", money + "");
        httpParams.put("43", getIntent().getStringExtra("areaId"));
        httpParams.put("42", getMerNo());
        httpParams.put("44", bindCard.getBANK_ACCOUNT());
        httpParams.put("45", "10B");
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
                    ViewUtils.makeToast(context, "申请已提交", 500);
                    setResult(RESULT_OK);
                    finish();

                }
            }
        });
    }

    /**
     * 信用卡付款
     */
    private void gotoVipUp(final BindCard bindCard) {
        loadingDialog.show();
        String moneyFen = CommonUtils.formatNewFen(money);
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190111");
        httpParams.put("5", moneyFen);
        httpParams.put("8", "b");
        httpParams.put("10", bindCard.getBANK_ACCOUNT());
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

                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    finish();
                    ViewUtils.makeToast2(context,
                            "支付成功,请重新登录", 500, LoginNewActivity.class,
                            "PAY");
                } else if ("01".equals(model.getStr39())) {
                    Intent intent = new Intent(context, BindCardActivity.class);
                    intent.putExtra("bindCard", bindCard);
                    intent.putExtra("bindId", bindCard.getID());
                    intent.putExtra("limit", bindCard.getLIMIT_MONEY());
                    intent.putExtra("billDay", bindCard.getBILL_DAY());
                    intent.putExtra("payDay", bindCard.getREPAYMENT_DAY() + "");
                    intent.putExtra("bankAccount", bindCard.getBANK_ACCOUNT());
                    intent.putExtra("expiryDay", bindCard.getExpdate());
                    intent.putExtra("cvn", bindCard.getCvn());
                    intent.putExtra("type", model.getStr40());
                    intent.putExtra("category", "1");
                    intent.putExtra("isNotModify", true);
                    startActivity(intent);
                } else {
                    ViewUtils.makeToast(context, model.getStr39(), 1000);
                }
            }
        });
    }


    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_right:
                // TODO: 2019/3/29 添加信用卡
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                startActivity(new Intent(context, AddressAddActivity.class).putExtra("type", "YK"));
                break;
        }
    }
}
