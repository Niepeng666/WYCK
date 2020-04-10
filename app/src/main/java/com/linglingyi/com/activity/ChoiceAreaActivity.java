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
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.LogUtils;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.linglingyi.com.adapter.AreaAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ChannelBean;
import com.linglingyi.com.model.IndustryModel;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.LinearLayoutManagerWrapper;
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
 * @date: 2019/4/1
 */
public class ChoiceAreaActivity extends BaseActivity {


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
    private ChannelBean.Channel channel;
    private List<Area> areaList = new ArrayList<Area>();
    private AreaAdapter myAdapter;

    private int flag = 0;
    private HashMap<String, Area> selectedArea = new HashMap<>();
    private String selectPriId, selectCityId, selectMerNo;
    private boolean onlyMer;//已选地区 只需要选择商户
    private boolean justLocation = false;   //只当作地区选择器，不会调用行业
    private HashMap<String, Area> map = new HashMap<>();//存储onlyMer时候传过来的省和市值
    private boolean onlyP_C = false;
    private String acqCode, number;
    /**
     * true 全额还款 false 余额还款
     */
    private boolean zhia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_recycler_view;
    }

    @Override
    public void initData() {
        acqCode = getIntent().getStringExtra("acqCode");
        number = getIntent().getStringExtra("number");
        onlyMer = getIntent().getBooleanExtra("onlyMer", false);
        onlyP_C = getIntent().getBooleanExtra("onlyP_C", false);
        String title = getIntent().getStringExtra("title");
        channel = (ChannelBean.Channel) getIntent().getSerializableExtra("channel");
        zhia=getIntent().getBooleanExtra("zhia",false);
        justLocation = getIntent().getBooleanExtra("justLocation", false);
        tvTitle.setText(!StringUtil.isEmpty(title) ? title : "选择商户");

        myAdapter = new AreaAdapter(areaList);
        rvList.setLayoutManager(new LinearLayoutManagerWrapper(context));
        rvList.addItemDecoration(new DividerItemDecoration(context, OrientationHelper.VERTICAL));
        myAdapter.bindToRecyclerView(rvList);

        if (onlyMer) {
            map = (HashMap<String, Area>) getIntent().getSerializableExtra("area");
            requestIndustry(map.get("province").getId(), map.get("city").getId());
        } else {
            requestData("0");
        }

        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (CommonUtils.isFastDoubleClick()) {
                    return;
                }
                Area area = areaList.get(position);
                if (onlyMer) {
                    selectMerNo = area.getId();
                    map.put("mer", area);
                    Intent intent = new Intent();
                    intent.putExtra("data", map);//把替换了商户的Map返回
                    intent.putExtra("acqcode", acqCode);
                    intent.putExtra("number", number);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    flag++;
                    if (flag == 1) {
                        selectPriId = area.getId();
                        selectedArea.put("province", area);
                        requestData(area.getId());
                    } else if (flag == 2) {
                        selectCityId = area.getId();
                        selectedArea.put("city", area);
                        if (justLocation) {
                            Intent intent = new Intent();
                            selectedArea.put("mer", area);
                            intent.putExtra("data", selectedArea);
                            intent.putExtra("acqcode", acqCode);
                            intent.putExtra("number", number);
                            setResult(RESULT_OK, intent);
                            finish();
                            return;
                        }
                        if (onlyP_C && !zhia) {
                            Intent intent = new Intent();
                            intent.putExtra("data", selectedArea);
                            setResult(RESULT_OK, intent);
                            finish();
                            return;
                        }

                        requestIndustry(selectPriId, selectCityId);
                    } else if (flag == 3) {
                        selectMerNo = area.getId();
                        Intent intent = new Intent();
                        selectedArea.put("mer", area);
                        intent.putExtra("data", selectedArea);//把省市和商户Map返回
                        intent.putExtra("acqcode", acqCode);
                        intent.putExtra("number", number);
                        intent.putExtra("channel", channel);
                        setResult(RESULT_OK, intent);
                        finish();

                    }
                }
            }
        });
    }

    /**
     * 请求行业列表
     *
     * @param priId
     * @param cityId
     */
    void requestIndustry(String priId, String cityId) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "490006");
        httpParams.put("30", getIntent().getStringExtra("bindid"));
        httpParams.put("31", priId);
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
//                    LogUtils.i("38=" + model.getStr38());
                    List<IndustryModel> list = JSONArray.parseArray(model.getStr38(), IndustryModel.class);
                    if (list != null && list.size() != 0) {
                        if (onlyP_C) {
                            Intent intent = new Intent();
                            intent.putExtra("data", selectedArea);
                            intent.putExtra("industry_JSON", model.getStr38());
                            setResult(RESULT_OK, intent);
                            finish();
                            return;
                        }
                        areaList.clear();
                        for (IndustryModel industry :
                                list) {
                            Area area = new Area();
                            area.setId(industry.getAcqMerchantNo());
                            area.setDivisionName(industry.getAcqMerchantName());
                            areaList.add(area);
                        }
                        myAdapter.setNewData(areaList);

                    } else {
                        flag--;
                        ViewUtils.makeToast(context, "此地区没有商户", 500);
                    }

                } else {
                    flag--;
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
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "490004");
        httpParams.put("41", id);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    LogUtils.i("38=" + model.getStr38());
                    List<Area> list = JSONArray.parseArray(model.getStr38(), Area.class);
                    areaList.clear();
                    areaList.addAll(list);
                    myAdapter.setNewData(areaList);
                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }
        });

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
