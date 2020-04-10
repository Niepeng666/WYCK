package com.linglingyi.com.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.RecordListAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.RecordListModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.GlideUtils;
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
 * @description:
 * @date: 2019/8/20
 */
public class RecordListActivity extends BaseActivity {
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
    @BindView(R.id.tv_no)
    TextView tvNo;
    @BindView(R.id.ll_no)
    RelativeLayout llNo;
    @BindView(R.id.iv_no_4)
    ImageView ivNo4;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.ll_invite_num)
    LinearLayout llInviteNum;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.iv_level_icon)
    ImageView ivLevelIcon;
    @BindView(R.id.tv_invite_title_1)
    TextView tvInviteTitle1;
    @BindView(R.id.tv_invite_title_2)
    TextView tvInviteTitle2;
    @BindView(R.id.cl_no)
    CardView clNo;
    private List<RecordListModel> mList = new ArrayList<>();
    private RecordListAdapter mRecordListAdapter;
    private String type = "10A";
    private RecordListModel mRecordListModel;

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_record_list;
    }

    @Override
    public void initData() {
        tvTitle.setText("龙虎榜");
        rvList.setLayoutManager(new LinearLayoutManager(context));
        mRecordListAdapter = new RecordListAdapter(mList);
        mRecordListAdapter.bindToRecyclerView(rvList);
//        mRecordListAdapter.setType(type);
        rvList.addItemDecoration(new DividerItemDecoration(context, OrientationHelper.VERTICAL));
        loadData();
        initListener();
        setTabLine(20, 20);
    }

    private void initListener() {
        tlTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                rvList.scrollToPosition(0);

                if (tab.getPosition() == 0) {
                    type = "10A";

                } else if (tab.getPosition() == 1) {
                    type = "10B";

                }
                loadDataDelay();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void loadDataDelay() {
        tlTab.postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.clear();
                loadData();
            }
        }, 50);
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

    /**
     * 获取龙虎榜数据
     */
    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "390015");
        httpParams.put("42", getMerNo());
        httpParams.put("43", type);
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
                    List<RecordListModel> mDayList = JSONArray.parseArray(model.getStr22(), RecordListModel.class);
                    mRecordListModel = JSONArray.parseArray(model.getStr21(), RecordListModel.class).get(0);
                    updateMineDate();
                    mList.addAll(mDayList);
                    mRecordListAdapter.setType(type);
                    mRecordListAdapter.setNewData(mList);
                }
            }


        });
    }

    /**
     * 显示自己排名
     */
    private void updateMineDate() {
        if (mRecordListModel == null) {
            return;
        }
        GlideUtils.loadAvatar(context, mRecordListModel.getHEAD_URL(), ivNo4);
        tvPhone.setText(CommonUtils.translateShortNumber(mRecordListModel.getPHONE(), 3, 4));
        tvNumber.setText("10A".equals(type) ? CommonUtils.formatNewWithScale(mRecordListModel.getCount().toString(),0) + "" : mRecordListModel.getCount() + "");
        tvNo.setText(mRecordListModel.getRownum() == 0 ? "暂无排名" : "NO." + mRecordListModel.getRownum());
        if ("10A".equals(type)) {
            tvInviteTitle1.setText("团队VIP");
            tvInviteTitle2.setText("位");
        } else {
            tvInviteTitle1.setText("刷卡金额");
            tvInviteTitle2.setText("元");
        }
    }
}
