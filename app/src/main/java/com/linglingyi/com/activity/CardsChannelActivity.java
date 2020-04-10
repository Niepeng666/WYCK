package com.linglingyi.com.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.ChooseCardAccountAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.CardsPlanCloseEvent;
import com.linglingyi.com.model.AccountModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.CardsModel;
import com.linglingyi.com.model.CardsPlanModel;
import com.linglingyi.com.model.CardsPreviewPlanAllModel;
import com.linglingyi.com.model.ChannelBean;
import com.linglingyi.com.model.MakeCardModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/15
 */
public class CardsChannelActivity extends BaseActivity {
    private static final int REQUSRT_CHANNEL = 0x01;
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
    /**
     * 多张卡信息
     */
    private CardsModel mCardsModel;
    private ChooseCardAccountAdapter mChooseCardAccountAdapter;
    private List<ChannelBean.Channel> mChannelList = new ArrayList<>();
    private int selPosition = -1;
    private ChannelBean.Channel mSelChannel;
    /**
     * 多张卡号，逗号分割
     */
    private String bankAccounts;
    private boolean isSel;
    private CardsPreviewPlanAllModel mCardsPreviewPlanAllModel = new CardsPreviewPlanAllModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_recycler_view;
    }

    @Override
    public void initData() {
        tvTitle.setText("选择通道");
        EventBus.getDefault().register(this);
        mCardsModel = (CardsModel) getIntent().getSerializableExtra("cardsModel");
        StringBuilder stringBuilder = new StringBuilder();
        for (MakeCardModel makeCardModel : mCardsModel.getMakeCardModelList()) {
            if (stringBuilder.length() > 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append(makeCardModel.getBankAccount());
        }
//        if (stringBuilder.length() > 1) {
//            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//        }
        bankAccounts = stringBuilder.toString();

        mChooseCardAccountAdapter = new ChooseCardAccountAdapter(mChannelList);
        rvList.setLayoutManager(new LinearLayoutManager(context));

        mChooseCardAccountAdapter.bindToRecyclerView(rvList);
        mChooseCardAccountAdapter.addFooterView(getFooterView());
        loadChannelData();
        initListener();
    }

    private void initListener() {
        mChooseCardAccountAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.chb_select:
                        requestBindStatus(mChannelList.get(position).getAcqcode(), position);
                        break;
                    case R.id.tv_limit_des:
                        Intent intent = new Intent(context, ImageActivity.class);
                        intent.putExtra("title", mChannelList.get(position).getChannelName());
                        intent.putExtra("url", "http://120.78.81.147/icon/icon_channel_" + mChannelList.get(position).getAcqcode() + ".png");
                        startActivity(intent);
                        break;
                }
            }
        });

        mChooseCardAccountAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                requestBindStatus(mChannelList.get(position).getAcqcode(), position);
            }
        });
        mChooseCardAccountAdapter.setMultiCards(true);
    }

    /**
     * 判断是否绑卡
     *
     * @param position
     */
    private void requestBindStatus(String acqCode, final int position) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "393021");
        httpParams.put("42", getMerNo());
        httpParams.put("35", bankAccounts);
        httpParams.put("36", acqCode);
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
                    if (selPosition != position) {
                        selPosition = position;
                        for (int i = 0; i < mChannelList.size(); i++) {
                            mChannelList.get(i).setCheck(false);
                        }
                        mChannelList.get(position).setCheck(true);
                        mChooseCardAccountAdapter.notifyDataSetChanged();
                        mSelChannel = mChannelList.get(position);
                    }
                } else {
                    AccountModel accountModel = JSONObject.parseObject(model.getStr36(), AccountModel.class);
                    for (MakeCardModel makeCardModel : mCardsModel.getMakeCardModelList()) {
                        if (model.getStr34().equals(makeCardModel.getBankAccount())) {
                            Intent intent = new Intent(context, BindCardActivity.class);
                            intent.putExtra("bindCard", makeCardModel.getBindCard());
                            intent.putExtra("type", accountModel.getType());
                            intent.putExtra("category", accountModel.getCategory());
                            startActivity(intent);
                            break;
                        }
                    }

                }
            }


        });
    }

    private View getFooterView() {

        TextView tvAdd = new TextView(context);
        tvAdd.setText("下一步");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);
        params.setMargins(CommonUtils.dp2px(context, 10), 50, CommonUtils.dp2px(context, 10), 0);
        tvAdd.setGravity(Gravity.CENTER);
        int[] attrArray1 = {R.attr.theme_bg_color};
        TypedArray mTypedArray1 = context.obtainStyledAttributes(attrArray1);
        tvAdd.setBackgroundColor(mTypedArray1.getColor(0, 0xFF000000));
        tvAdd.setTextColor(Color.WHITE);
        tvAdd.setLayoutParams(params);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selPosition == -1) {
                    ViewUtils.makeToast(context, "请选择通道", 500);
                    return;
                }
                mCardsModel.setAcqCode(mSelChannel.getAcqcode());
                calculateWorkingfund();

            }
        });
        return tvAdd;
    }

    /**
     * 制定一卡多还的周转金
     */
    private void calculateWorkingfund() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "393043");
        httpParams.put("5", mCardsModel.isBackOldCard() ? "1" : "0");
        httpParams.put("40", JSONArray.parseArray(JSONObject.toJSONString(mCardsModel.getMakeCardModelList())).toJSONString());
        httpParams.put("41", mCardsModel.getCityId());
        httpParams.put("42", getMerNo());
        httpParams.put("35", mCardsModel.getArea());
        httpParams.put("43", mCardsModel.getAcqCode());
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
                    List<CardsPlanModel> list = JSONArray.parseArray(model.getStr57(), CardsPlanModel.class);
                    mCardsPreviewPlanAllModel.setChannelName(mSelChannel.getChannelName());
                    mCardsPreviewPlanAllModel.setList(list);
                    mCardsPreviewPlanAllModel.setTotalFee(model.getStr41());
                    mCardsPreviewPlanAllModel.setNumFee(model.getStr7());
                    mCardsPreviewPlanAllModel.setSaleFee(model.getStr9());
                    mCardsPreviewPlanAllModel.setCityId(mCardsModel.getCityId());
                    mCardsPreviewPlanAllModel.setTotalPrice(model.getStr11());
                    mCardsPreviewPlanAllModel.setStartDate(model.getStr14());
                    mCardsPreviewPlanAllModel.setEndDate(model.getStr15());
                    mCardsPreviewPlanAllModel.setInputWorkFund(mCardsModel.getInputWorkFund());
                    mCardsPreviewPlanAllModel.setPayTotalPrice(model.getStr13());
                    mCardsPreviewPlanAllModel.setAcqCode(mSelChannel.getAcqcode());
                    mCardsPreviewPlanAllModel.setDayNum(model.getStr23());
                    goCardsPreviewDetail(mCardsPreviewPlanAllModel);
                }
            }
        });
    }

    /**
     * 进入一卡多还详情预览页
     */
    private void goCardsPreviewDetail(CardsPreviewPlanAllModel detail) {
        Intent intent = new Intent(context, PreviewCardsDetailActivity.class);
        intent.putExtra("detail", detail);
        startActivity(intent);
    }

    /**
     * 获取全部通道
     */
    private void loadChannelData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390013");
        httpParams.put("42", getMerNo());
        httpParams.put("43", "YK");
        httpParams.put("41","10B");
        httpParams.put("44", mCardsModel.getMakeCardModelList().get(0).getBindCard().getID());
//        httpParams.put("44", mCardsModel.getMakeCardModelList().get(0).getBankAccount());
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
                    LogUtils.i("57=" + model.getStr57());
                    ChannelBean channelBean = JSONObject.parseObject(model.getStr57(), ChannelBean.class);
                    List<ChannelBean.Channel> list = channelBean.getAcqData();
                    mChannelList.addAll(list);
                    if (mChannelList.size() == 0) {
                        mChooseCardAccountAdapter.setEmptyView(R.layout.layout_empty, rvList);
                    }
                    mChooseCardAccountAdapter.setNewData(mChannelList);
                }
            }
        });
    }

    /**
     * 提交一卡多还计划后，自动关闭页面
     *
     * @param event
     */
    @Subscribe
    public void onEvent(CardsPlanCloseEvent event) {
        ViewUtils.overridePendingTransitionBack(context);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUSRT_CHANNEL) {
            mChannelList.clear();
            loadChannelData();
        }

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
