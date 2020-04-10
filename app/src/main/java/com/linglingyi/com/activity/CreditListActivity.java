package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.linglingyi.com.adapter.CreditAdapter;
import com.linglingyi.com.adapter.LoanAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.ApplyCreditEntity;
import com.linglingyi.com.model.ApplyLoanEntity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.GridDivideItemDecoration;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/30
 */
public class CreditListActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_credit_title)
    TextView tvCreditTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private CreditAdapter mCreditAdapter;
    private List<ApplyCreditEntity> mList = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.act_loan_credit_list;
    }

    @Override
    public void initData() {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("申请记录");
        tvTitle.setText("信用卡申请");
        tvCreditTitle.setText("信用卡在线申请");
        GridLayoutManager linearLayoutManager = new GridLayoutManager(context, 3);
        rvList.addItemDecoration(new GridDivideItemDecoration(context,CommonUtils.dp2px(context,10),ContextCompat.getColor(context,R.color.gray_bg_f6)));

        rvList.setLayoutManager(linearLayoutManager);
        mCreditAdapter = new CreditAdapter(mList);
        mCreditAdapter.bindToRecyclerView(rvList);
        mCreditAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                Intent intent = new Intent(context, ApplyCreditLoanActivity.class);
                intent.putExtra("credit", mList.get(position));
                startActivity(intent);
            }
        });
        loadData();
    }

    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams=OkClient.getParamsInstance().getParams();
        httpParams.put("3","150075");
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context,BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if ("00".equals(model.getStr39())) {
                    List<ApplyCreditEntity> list = JSONArray.parseArray(model.getStr57(), ApplyCreditEntity.class);
                    mList.addAll(list);
                    mCreditAdapter.setNewData(mList);
                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_right:
                Intent intent = new Intent(context, ApplyRecordActivity.class);
                intent.putExtra("type", "BK");
                startActivity(intent);
                break;
        }
    }
}
