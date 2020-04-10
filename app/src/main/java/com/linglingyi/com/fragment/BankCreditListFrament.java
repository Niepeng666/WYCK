package com.linglingyi.com.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.linglingyi.com.activity.AddBankCardActivity;
import com.linglingyi.com.activity.BankCreditDetailActivity;
import com.linglingyi.com.adapter.BankCardAdpter;
import com.linglingyi.com.base.BaseFragment;
import com.linglingyi.com.event.BankCardEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class BankCreditListFrament extends BaseFragment {

    @BindView(R.id.rv_credit_list)
    RecyclerView rvCreditList;
    @BindView(R.id.rl_container)
    RelativeLayout rlContainer;
    Unbinder unbinder;
    private BankCardAdpter mCardAdapter;
    private List<BindCard> mList = new ArrayList<>();
    private BindCard chooseBindCard;

    public static BankCreditListFrament newInstance() {
        return new BankCreditListFrament();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int initLayout() {
        return R.layout.frag_bank_credit;
    }

    @Override
    public void initData(View v) {
        rvCreditList.setLayoutManager(new LinearLayoutManager(context));
        mCardAdapter = new BankCardAdpter(mList);

        mCardAdapter.setEmptyView(R.layout.layout_empty, rvCreditList);
        rvCreditList.setAdapter(mCardAdapter);
//        mCardAdapter.addFooterView(getFooterView());
        initListener();
        requestData();
    }

    private void initListener() {

        mCardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(context, BankCreditDetailActivity.class).putExtra("bindCard", mList.get(position)).putExtra("title","银行卡详情"));

            }
        });
    }

    private View getFooterView() {

        TextView tvAdd = new TextView(context);
        tvAdd.setText("+ 添加信用卡");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);
        params.setMargins(20, 50, 20, 0);
        tvAdd.setGravity(Gravity.CENTER);
        tvAdd.setBackgroundColor(Color.WHITE);
        tvAdd.setLayoutParams(params);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
//                if (!checkBindCard()) {
//                    return;
//                }
                startActivity(new Intent(context, AddBankCardActivity.class));
            }
        });
        return tvAdd;
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
                    mList = JSONArray.parseArray(model.getStr57(), BindCard.class);
//                    mList.addAll(list);
                    mCardAdapter.setNewData(mList);
                }
            }
        });
    }

    /**
     * 绑卡成功后，自动刷新数据
     *
     * @param event
     */
    @Subscribe
    public void onEvent(BankCardEvent event) {
        requestData();
    }


}
