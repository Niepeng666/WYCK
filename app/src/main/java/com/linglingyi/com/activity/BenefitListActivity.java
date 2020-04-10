package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.BenefitListAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BenefitModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description: 10A分润 10B提现 10C返佣 10D管理
 * @date: 2019/4/4
 */
public class BenefitListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tl_tab)
    TabLayout tlTab;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_seven_num)
    TextView tvSevenNum;
    @BindView(R.id.view_center_line)
    View viewCenterLine;
    @BindView(R.id.tv_seven_money_title)
    TextView tvSevenMoneyTitle;
    @BindView(R.id.tv_seven_money_left)
    TextView tvSevenMoneyLeft;
    @BindView(R.id.tv_seven_money)
    TextView tvSevenMoney;
    @BindView(R.id.ll_money)
    LinearLayout llMoney;
    @BindView(R.id.tv_total_money_title)
    TextView tvTotalMoneyTitle;
    @BindView(R.id.tv_seven_num_title)
    TextView tvSevenNumTitle;
    private BenefitListAdapter mBenefitListAdapter;
    private List<BenefitModel> mList = new ArrayList<>();
    /**
     * 10A 分润 10C红包 10E返佣
     */
    private String type;
    private String title;
    private int pageIndex = 1;
    private int count;
    private String day;

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }


    @Override
    public int initLayout() {
        return R.layout.act_benefit_list;
    }

    @Override
    public void initData() {
        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");
        day = getIntent().getStringExtra("day");
        tvTitle.setText(title);

        mBenefitListAdapter = new BenefitListAdapter(mList, type);
        rvList.setLayoutManager(new LinearLayoutManager(context));
        rvList.addItemDecoration(new DividerItemDecoration(context, OrientationHelper.VERTICAL));
        mBenefitListAdapter.bindToRecyclerView(rvList);
        mBenefitListAdapter.setEmptyView(R.layout.layout_empty, rvList);
        loadData();
        initListener();
        setTabLine(30, 30);
    }


    public void setTabLine(int left, int right) {
        try {
            Class<?> tablayout = tlTab.getClass();
            Field tabStrip = tablayout.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout ll_tab = (LinearLayout) tabStrip.get(tlTab);
            for (int i = 0; i < ll_tab.getChildCount(); i++) {
                View child = ll_tab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                //修改两个tab的间距
                params.setMarginStart(CommonUtils.dp2px(context, left));
                params.setMarginEnd(CommonUtils.dp2px(context, right));
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void initListener() {
        mBenefitListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent(context, BenefitDetailActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("type", type);
                intent.putExtra("detail", mList.get(position));
                startActivity(intent);
            }
        });
        mBenefitListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (count > 0 && count > pageIndex) {
                    pageIndex++;
                    loadData();
                } else {
                    mBenefitListAdapter.loadMoreEnd();
                }
            }
        }, rvList);
        tlTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mList.clear();
                switch (tab.getPosition()) {
                    case 0:
                        type = "1";

                        break;
                    case 1:
                        type = "2";

                        break;
                    case 2:
                        type = "3";

                        break;
                }
                loadData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    /**
     * 获取列表
     */
    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "690036");
        httpParams.put("43", pageIndex + "");
        httpParams.put("45", type);
        if (!StringUtil.isEmpty(day)) {
            httpParams.put("44", day);
        }
        httpParams.put("42", getMerNo());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
                mBenefitListAdapter.loadMoreComplete();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                mBenefitListAdapter.loadMoreComplete();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    count = StringUtil.stringToInt(model.getStr55());
                    List<BenefitModel> list = JSONArray.parseArray(model.getStr57(), BenefitModel.class);
                    mList.addAll(list);
                    mBenefitListAdapter.setType(type);
                    mBenefitListAdapter.setNewData(mList);
                }
            }
        });
    }
}
