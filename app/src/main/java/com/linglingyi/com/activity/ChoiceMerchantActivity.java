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
import com.linglingyi.com.model.IndustryModel;
import com.linglingyi.com.utils.ViewUtils;
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
 * @date: 2019/10/29
 */
public class ChoiceMerchantActivity extends BaseActivity {
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
    private String acqCode, cityId, bankId;
    private List<Area> areaList = new ArrayList<Area>();
    private AreaAdapter myAdapter;

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
        tvTitle.setText("选择商户");
        acqCode = getIntent().getStringExtra("acqCode");
        cityId = getIntent().getStringExtra("cityId");
        bankId = getIntent().getStringExtra("bankId");

        myAdapter = new AreaAdapter(areaList);
        rvList.setLayoutManager(new LinearLayoutManager(context));
        rvList.addItemDecoration(new DividerItemDecoration(context, OrientationHelper.VERTICAL));
        myAdapter.bindToRecyclerView(rvList);
        requestIndustry();
        initListener();
    }

    private void initListener() {
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("merchant", areaList.get(position).getDivisionName());
                intent.putExtra("smallPlanPosition", getIntent().getIntExtra("smallPosition",0));
                intent.putExtra("bigPlanPosition", getIntent().getIntExtra("bigPosition",0));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    /**
     * 请求行业列表
     */
    void requestIndustry() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "490006");
        httpParams.put("30", bankId);
        httpParams.put("32", cityId);
        httpParams.put("33", acqCode);
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
                    List<IndustryModel> list = JSONArray.parseArray(model.getStr38(), IndustryModel.class);
                    if (list != null && list.size() != 0) {
                        for (IndustryModel industry :
                                list) {
                            Area area = new Area();
                            area.setId(industry.getAcqMerchantNo());
                            area.setDivisionName(industry.getAcqMerchantName());
                            areaList.add(area);
                        }
                        myAdapter.setNewData(areaList);
                    } else {
                        ViewUtils.makeToast(context, "此地区没有商户", 500);
                    }

                }
            }


        });

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
