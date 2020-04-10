
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
import com.linglingyi.com.adapter.LoanAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.ApplyLoanEntity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.DeviceUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
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
public class LoanListActivity extends BaseActivity {


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
    private LoanAdapter mLoanAdapter;
    private List<ApplyLoanEntity> mList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_loan_credit_list;
    }

    @Override
    public void initData() {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("申请记录");
        tvTitle.setText("贷款申请");
        tvCreditTitle.setText("贷款在线申请");
        GridLayoutManager linearLayoutManager = new GridLayoutManager(context, 3);
        rvList.addItemDecoration(new GridDivideItemDecoration(context,CommonUtils.dp2px(context,10),ContextCompat.getColor(context,R.color.gray_bg_f6)));

        rvList.setLayoutManager(linearLayoutManager);
        mLoanAdapter = new LoanAdapter(mList);
        mLoanAdapter.bindToRecyclerView(rvList);
        mLoanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                Intent intent = new Intent(context, ApplyCreditLoanActivity.class);
                intent.putExtra("loan", mList.get(position));
                startActivity(intent);
            }
        });
        loadData();
    }

    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams=OkClient.getParamsInstance().getParams();
        httpParams.put("3","150076");
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context,BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if ("00".equals(model.getStr39())) {
                    List<ApplyLoanEntity> list = JSONArray.parseArray(model.getStr57(), ApplyLoanEntity.class);
                    mList.addAll(list);
                    mLoanAdapter.setNewData(mList);
                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
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
                Intent intent = new Intent(context, ApplyRecordActivity.class);
                intent.putExtra("type", "DK");
                startActivity(intent);
                break;
        }
    }
}
