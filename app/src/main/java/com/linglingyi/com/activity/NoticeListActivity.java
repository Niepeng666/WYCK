package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.linglingyi.com.event.NoticeEvent;
import com.linglingyi.com.utils.LogUtils;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.linglingyi.com.adapter.NoticeListAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.NoticeModel;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/9
 */
public class NoticeListActivity extends BaseActivity {
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
    private List<NoticeModel> mList = new ArrayList<>();
    private NoticeListAdapter mNoticeListAdapter;

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
        tvTitle.setText("公告列表");
        rvList.setLayoutManager(new LinearLayoutManager(context));
        rvList.addItemDecoration(new DividerItemDecoration(context, OrientationHelper.VERTICAL));
        mNoticeListAdapter = new NoticeListAdapter(mList);
        mNoticeListAdapter.bindToRecyclerView(rvList);
//        loadData(null);
        initListener();

    }

    private void initListener() {
        mNoticeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                loadData(mList.get(position));
            }
        });
    }

    private void loadData(final NoticeModel noticeModel) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190103");
        if (noticeModel != null) {
            httpParams.put("21", noticeModel.getId());
        }
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
                    LogUtils.i("57=" + model.getStr60());
                    if (noticeModel != null) {
                        Intent intent = new Intent(context, NoticeDetailActivity.class);
                        intent.putExtra("detail", noticeModel);
                        startActivity(intent);
                        EventBus.getDefault().post(new NoticeEvent());
                    }
                    if (StringUtil.isEmpty(model.getStr60())) {
                        return;
                    }
                    List<NoticeModel> list = JSONArray.parseArray(model.getStr60(), NoticeModel.class);
                    mList.addAll(list);
                    mNoticeListAdapter.setNewData(mList);

                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mList.clear();
        loadData(null);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
