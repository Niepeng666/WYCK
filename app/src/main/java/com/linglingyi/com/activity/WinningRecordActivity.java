package com.linglingyi.com.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.WinningRecordAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.LordRightsModel;
import com.linglingyi.com.model.WinningRecordModel;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/17 15:57
 * @功能 中标记录
 **/
public class WinningRecordActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private WinningRecordAdapter winningRecordAdapter;
    private List<WinningRecordModel> list = new ArrayList<>();
    private int page = 1, allPage = 1;
    /**
     * 月份
     */
    private String selMonth;

    @Override
    public int initLayout() {
        return R.layout.activity_winning_record;
    }

    @Override
    public void initData() {
        tvTitle.setText("中标记录");
        tvRight.setText("月份");
        tvRight.setVisibility(View.VISIBLE);

        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadmoreListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        winningRecordAdapter = new WinningRecordAdapter(list);
        winningRecordAdapter.bindToRecyclerView(recyclerView);
        winningRecordAdapter.setEmptyView(R.layout.layout_empty, recyclerView);
        smartRefreshLayout.autoRefresh();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        if (page > 0 && page < allPage) {
            page++;
            requestData(false);
        } else {
            refreshlayout.finishLoadmoreWithNoMoreData();
        }
    }

    private void requestData(boolean isRefresh) {
        if (isRefresh) {
            smartRefreshLayout.finishRefresh();
        } else {
            smartRefreshLayout.finishLoadmore();
        }
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "393005");
        if (!StringUtil.isEmpty(selMonth)) {
            httpParams.put("35", selMonth);
        }
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
                    List<WinningRecordModel> lordRightsModels = JSONArray.parseArray(model.getStr57(), WinningRecordModel.class);
                    list.addAll(lordRightsModels);
                    winningRecordAdapter.setNewData(list);
                }
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        list.clear();
        page = 1;
        refreshlayout.resetNoMoreData();
        requestData(true);
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                showTimePickerDialog();
                break;
        }
    }

    /**
     * 显示日期选择 7.0 7.1显示年月日
     */
    private void showTimePickerDialog() {
        //获取当前日期
        TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                String yearStr = String.valueOf(year).substring(0, 4);
                String monthStr = month < 10 ? "0" + month : month + "";
                selMonth = yearStr + "-" + monthStr;
                smartRefreshLayout.autoRefresh();
            }
        })
                .setType(new boolean[]{true, true, false, false, false, false})
                .isDialog(false)
                .build();
        pvTime.show();

    }
}
