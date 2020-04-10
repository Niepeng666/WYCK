package com.linglingyi.com.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.ClientListAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ClientModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.PermissionUtil;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyClientDetailActivity extends BaseActivity {


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
    @BindView(R.id.et_search_phone)
    EditText etSearchPhone;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private List<ClientModel> mList = new ArrayList<>();
    private ClientListAdapter mClientListAdapter;
    private int pageIndex = 1, count;
    private String level, phone, isReal = "10B", type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_my_client_detail;
    }

    @Override
    public void initData() {
        level = getIntent().getStringExtra("level");
        String title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");
        tvTitle.setText(title);
        rvList.setLayoutManager(new LinearLayoutManager(context));
        mClientListAdapter = new ClientListAdapter(mList, type);
        mClientListAdapter.bindToRecyclerView(rvList);

        loadData();
        initListener();
    }

    private void initListener() {
        mClientListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (count > 0 && count > pageIndex) {
                    pageIndex++;
                    loadData();
                } else {
                    mClientListAdapter.loadMoreEnd();
                }
            }
        }, rvList);
        mClientListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (!"1".equals(mList.get(position).getStatus())) {
//                    ViewUtils.makeToast(context, "间推用户不可直接联系", 500);
                    return;
                }
                if (view.getId() == R.id.ly_phone) {
                    if (!PermissionUtil.CALL_PHONE(context)) {
                        return;
                    }
                    showCallServerDialog(mList.get(position).getLinkPhone());
                }
            }
        });

        tlTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    isReal = "10B";
                } else {
                    isReal = "10A";
                }

                refreshData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void refreshData() {
        pageIndex = 1;
        mList.clear();
        loadData();
    }


    private void showCallServerDialog(final String phone) {
        Button confirmBt, cancleBt;
        final Dialog mydialog = new Dialog(context, R.style.MyProgressDialog);
        mydialog.setContentView(R.layout.callserver_dialog);
        mydialog.setCanceledOnTouchOutside(false);
        TextView phonenum = mydialog.findViewById(R.id.phoneNum);
        TextView tvTitle = mydialog.findViewById(R.id.tv_dialogTitle);
        tvTitle.setText("拨打电话");
        phonenum.setText(phone);
        confirmBt = mydialog.findViewById(R.id.bt_cancelPlan);
        cancleBt = mydialog.findViewById(R.id.bt_suspendCancel);
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonUtils.isFastDoubleClick2()) {
                    return;
                }
                mydialog.dismiss();
                String serviceNumber = phone.replace("-", "");
                Intent phoneIntent = new Intent(
                        "android.intent.action.CALL", Uri.parse("tel:"
                        + serviceNumber));
                startActivity(phoneIntent);
            }
        });
        cancleBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mydialog.dismiss();
            }

        });

        mydialog.show();
    }

    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "399200");
        httpParams.put("42", getMerNo());
        if (!StringUtil.isEmpty(level)) {
            httpParams.put("43", level);
        }
        if (!StringUtil.isEmpty(type)) {
            httpParams.put("44", type);
        }
        httpParams.put("45", isReal);
        httpParams.put("46", pageIndex);
        if (!StringUtil.isEmpty(phone)) {
            httpParams.put("41", phone);
        }
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
                mClientListAdapter.loadMoreComplete();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                mClientListAdapter.loadMoreComplete();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    count = CommonUtils.strToInt(model.getStr37());
                    List<ClientModel> list = JSONArray.parseArray(model.getStr57(), ClientModel.class);
                    if (list != null && list.size() > 0) {
                        mList.addAll(list);
                    }
                    if (pageIndex == 1 && mList.size() == 0) {
                        mClientListAdapter.setEmptyView(R.layout.layout_empty, rvList);
                    }
                    mClientListAdapter.setNewData(mList);
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.bt_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.bt_search:
                phone = etSearchPhone.getText().toString();
                refreshData();
                break;
        }
    }
}
