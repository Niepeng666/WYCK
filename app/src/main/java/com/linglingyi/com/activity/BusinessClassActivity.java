package com.linglingyi.com.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.linglingyi.com.adapter.SchoolBusinessAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.SchoolBusinessModel;
import com.linglingyi.com.utils.FastJsonUtils;
import com.linglingyi.com.utils.ViewUtils;
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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/20
 */
public class BusinessClassActivity extends BaseActivity implements OnRefreshListener, OnLoadmoreListener,
        BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private List<SchoolBusinessModel> list = new ArrayList<>();
    private SchoolBusinessAdapter sAdapter;
    private int page = 1, allpage = 1;

    @Override
    public int initLayout() {
        return R.layout.act_business_class;
    }

    @Override
    public void initData() {
        tvTitle.setText("商学院");

        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadmoreListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        sAdapter = new SchoolBusinessAdapter(context);
        sAdapter.setOnItemChildClickListener(this);
        sAdapter.setNewData(list);
        sAdapter.bindToRecyclerView(recyclerView);

        smartRefreshLayout.autoRefresh();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        list.clear();
        refreshlayout.resetNoMoreData();
        requestData(true);
    }

    private void requestData(final boolean isRefresh) {
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "999001");
        httpParams.put("44", page);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                if (isRefresh) {
                    smartRefreshLayout.finishRefresh();
                } else {
                    smartRefreshLayout.finishLoadmore();
                }
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                if (isRefresh) {
                    smartRefreshLayout.finishRefresh();
                } else {
                    smartRefreshLayout.finishLoadmore();
                }
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
//                    allpage = Integer.parseInt(model.getStr37());
                    List<SchoolBusinessModel> listModel = FastJsonUtils.toList(model.getStr57(), SchoolBusinessModel.class);
                    if (listModel != null) {
                        list.addAll(listModel);
                        sAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.ly_item:
                SchoolBusinessModel model = (SchoolBusinessModel) view.getTag();
                startActivity(new Intent(context, X5WebViewActivity.class).putExtra("title", model.getTitle())
                        .putExtra("url", model.getSkipUrl()));
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        if (page <= allpage) {
            requestData(false);
        } else {
            refreshlayout.finishLoadmoreWithNoMoreData();
        }
    }
}
