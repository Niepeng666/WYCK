package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.linglingyi.com.adapter.CardHonorHistoyListAdapter;
import com.linglingyi.com.adapter.ScoreHistoyListAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.CardHonorModel;
import com.linglingyi.com.model.ScoreHistoryModel;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/3
 */
public class CardHonorHistoryListActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private CardHonorHistoyListAdapter mHistoryAdapter;
    private List<CardHonorModel> mList = new ArrayList<>();
    private boolean isScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_recycler_view;
    }

    @Override
    public void initData() {
        tvTitle.setText("历史记录");
        isScore = getIntent().getBooleanExtra("isScore", false);
        rvList.setLayoutManager(new LinearLayoutManager(context));
        mHistoryAdapter = new CardHonorHistoyListAdapter(mList);
        mHistoryAdapter.bindToRecyclerView(rvList);
        mHistoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (StringUtil.isEmpty(mList.get(position).getIcSerNo())) {
                    return;
                }
                Intent intent = new Intent(context, X5WebViewActivity.class);
                intent.putExtra("title", isScore ? "卡测评检测结果" : "征信结果");
                intent.putExtra("url", mList.get(position).getIcSerNo());
                startActivity(intent);
            }
        });
        mHistoryAdapter.setEmptyView(R.layout.layout_empty, rvList);
        loadData();
    }

    private void loadData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", isScore ? "690017" : "690020");
        httpParams.put("42", getMerId());
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
                    List<CardHonorModel> list = JSONArray.parseArray(model.getStr57(), CardHonorModel.class);
                    mList.addAll(list);
                    mHistoryAdapter.setNewData(mList);
                }
            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        com.linglingyi.com.utils.ViewUtils.overridePendingTransitionBack(context);
    }
}
