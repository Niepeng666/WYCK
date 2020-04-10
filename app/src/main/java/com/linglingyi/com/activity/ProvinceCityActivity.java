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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.AreaAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/21
 */
public class ProvinceCityActivity extends BaseActivity {
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
    private List<Area> areaList = new ArrayList<Area>();
    private AreaAdapter myAdapter;
    /**
     * 存储onlyMer时候传过来的省和市值
     */
    private HashMap<String, Area> map = new HashMap<>();
    private int flag = 0;
    private boolean chooseDistinct;
    private String proId, cityId;

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
        tvTitle.setText("选择地区");
        chooseDistinct = getIntent().getBooleanExtra("chooseDistinct", true);
        cityId = getIntent().getStringExtra("cityId");
        myAdapter = new AreaAdapter(areaList);
        rvList.setLayoutManager(new LinearLayoutManager(context));
        rvList.addItemDecoration(new DividerItemDecoration(context, OrientationHelper.VERTICAL));
        myAdapter.bindToRecyclerView(rvList);
        if (!StringUtil.isEmpty(cityId)) {
            flag = 2;
            requestData(cityId);
        } else {
            requestData("0");
        }
        initListener();
    }

    private void initListener() {
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                flag++;
                Area area = areaList.get(position);
                if (flag == 1) {
                    map.put("province", area);
                    requestData(area.getId());
                } else if (flag == 2) {
                    map.put("city", area);
                    if (chooseDistinct) {
                        requestData(area.getId());
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("data", map);
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                } else if (flag == 3) {
                    map.put("distinct", area);
                    Intent intent = new Intent();
                    intent.putExtra("data", map);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    /**
     * 获取地区
     *
     * @param id
     */
    private void requestData(final String id) {
        loadingDialog.show();
        areaList.clear();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "393004");
        httpParams.put("43", id);
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
                    List<Area> list = JSONArray.parseArray(model.getStr57(), Area.class);
                    areaList.addAll(list);
                    myAdapter.setNewData(areaList);
                }
            }
        });

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
