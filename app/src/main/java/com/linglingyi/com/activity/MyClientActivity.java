package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.AgentInfoModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ExtendModel;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/4
 */
public class MyClientActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_extend_name)
    TextView tvExtendName;
    @BindView(R.id.tv_extend_phone)
    TextView tvExtendPhone;
    @BindView(R.id.tv_agent_name)
    TextView tvAgentName;
    @BindView(R.id.tv_agent_phone)
    TextView tvAgentPhone;
    @BindView(R.id.tl_tab)
    TabLayout tlTab;
    @BindView(R.id.tv_direct_num)
    TextView tvDirectNum;
    @BindView(R.id.tv_direct_today)
    TextView tvDirectToday;
    @BindView(R.id.ll_direct)
    LinearLayout llDirect;
    @BindView(R.id.tv_indirect_num)
    TextView tvIndirectNum;
    @BindView(R.id.tv_indirect_today)
    TextView tvIndirectToday;
    @BindView(R.id.ll_indirect)
    LinearLayout llIndirect;
    @BindView(R.id.ll_client)
    LinearLayout llClient;
    @BindView(R.id.tv_level_1_num)
    TextView tvLevel1Num;
    @BindView(R.id.tv_level_1_today)
    TextView tvLevel1Today;
    @BindView(R.id.ll_level_1)
    LinearLayout llLevel1;
    @BindView(R.id.tv_level_2_num)
    TextView tvLevel2Num;
    @BindView(R.id.tv_level_2_today)
    TextView tvLevel2Today;
    @BindView(R.id.ll_level_2)
    LinearLayout llLevel2;
    @BindView(R.id.tv_level_3_num)
    TextView tvLevel3Num;
    @BindView(R.id.tv_level_3_today)
    TextView tvLevel3Today;
    @BindView(R.id.ll_level_3)
    LinearLayout llLevel3;
    @BindView(R.id.tv_level_4_num)
    TextView tvLevel4Num;
    @BindView(R.id.tv_level_4_today)
    TextView tvLevel4Today;
    @BindView(R.id.ll_level_4)
    LinearLayout llLevel4;
    @BindView(R.id.tv_level_5_num)
    TextView tvLevel5Num;
    @BindView(R.id.tv_level_5_today)
    TextView tvLevel5Today;
    @BindView(R.id.ll_level_5)
    LinearLayout llLevel5;
    @BindView(R.id.ll_vip)
    LinearLayout llVip;
    @BindView(R.id.tv_level_6_num)
    TextView tvLevel6Num;
    @BindView(R.id.tv_level_6_today)
    TextView tvLevel6Today;
    @BindView(R.id.ll_level_6)
    LinearLayout llLevel6;
    @BindView(R.id.tv_level_7_num)
    TextView tvLevel7Num;
    @BindView(R.id.tv_level_7_today)
    TextView tvLevel7Today;
    @BindView(R.id.ll_level_7)
    LinearLayout llLevel7;

    @OnClick({R.id.iv_back, R.id.ll_direct, R.id.ll_indirect, R.id.ll_level_1, R.id.ll_level_2, R.id.ll_level_3,
            R.id.ll_level_4, R.id.ll_level_5, R.id.ll_level_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.ll_direct:
                goClientDetail("直推会员", "10B", "1");
                break;
            case R.id.ll_indirect:
                goClientDetail("间推会员", "10B", "2");
                break;
            case R.id.ll_level_1:
                goClientDetail("普通会员", "10A", "1");
                break;
            case R.id.ll_level_2:
                goClientDetail("VIP会员", "10A", "2");
                break;
            case R.id.ll_level_3:
                goClientDetail("高级VIP", "10A", "3");
                break;
            case R.id.ll_level_4:
                goClientDetail("初级代理", "10A", "4");
                break;
            case R.id.ll_level_5:
                goClientDetail("高级代理", "10A", "5");
                break;
            case R.id.ll_level_6:
                goClientDetail("钻石", "10A", "6");
                break;
        }
    }

    private void goClientDetail(String title, String type, String level) {
        Intent intent = new Intent(context, MyClientDetailActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("level", level);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_my_client;
    }

    @Override
    public void initData() {
        tvTitle.setText("我的团队");

        loadData();
        initListener();
    }

    private void initListener() {

        tlTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    llVip.setVisibility(View.VISIBLE);
                    llClient.setVisibility(View.GONE);
                } else {
                    llClient.setVisibility(View.VISIBLE);
                    llVip.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void loadData() {
        loadingDialog.show();
        String phone = StorageCustomerInfo02Util.getInfo("phone", context);
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "999200");
        httpParams.put("43", phone);
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
                    tvTotal.setText(model.getStr19());
                    String s18 = model.getStr18();
                    String[] extendString = s18.split(",");
                    tvExtendName.setText(extendString[0]);
                    tvExtendPhone.setText(extendString[1]);

                    String s25 = model.getStr25();
                    String[] agentString = s25.split(",");
                    tvAgentName.setText(agentString[0]);
                    tvAgentPhone.setText(agentString[1]);

                    tvDirectNum.setText(model.getStr21());
                    tvDirectToday.setText(model.getStr22());

                    tvIndirectNum.setText(model.getStr23());
                    tvIndirectToday.setText(model.getStr24());

                    tvLevel1Num.setText(model.getStr31());
                    tvLevel1Today.setText(model.getStr34());

                    tvLevel2Num.setText(model.getStr32());
                    tvLevel2Today.setText(model.getStr35());

                    tvLevel3Num.setText(model.getStr33());
                    tvLevel4Num.setText(model.getStr36());
                    tvLevel5Num.setText(model.getStr37());
                    tvLevel6Num.setText(model.getStr38());
                }
            }
        });
    }
}
