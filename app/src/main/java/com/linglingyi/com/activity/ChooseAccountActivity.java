package com.linglingyi.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linglingyi.com.event.SwipeCloseEvent;
import com.linglingyi.com.utils.BitmapManage;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.viewone.DividerItemDecoration;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.linglingyi.com.adapter.ChannelAdapter;
import com.linglingyi.com.adapter.ChooseAccountAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.PlanCloseEvent;
import com.linglingyi.com.model.AccountModel;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.ChannelBean;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChooseAccountActivity extends BaseActivity {
    private static final int REWUEST_FINISH = 0x20;

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
    @BindView(R.id.cl_container)
    RelativeLayout clContainer;

    private ChooseAccountAdapter mChooseAccountAdapter;
    private List<ChannelBean.Channel> mList = new ArrayList<>();
    private BindCard mBindCard;
    private ChannelBean.Channel mSelChannel;
    /**
     * 全额还 or 养卡
     */
    private boolean zhia;

    private boolean isSel;
    private boolean isPay;
    private String money;

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_choose_account;
    }

    @Override
    public void initData() {
        tvTitle.setText("选择通道");
        tvRight.setText("限额说明");
        tvRight.setVisibility(View.VISIBLE);
        zhia = getIntent().getBooleanExtra("zhia", false);
        mBindCard = (BindCard) getIntent().getSerializableExtra("BindCard");
// : 2019/4/2 快捷收款，获取信息
        isPay = getIntent().getBooleanExtra("is2Pay", false);
        money = getIntent().getStringExtra("money");

        mChooseAccountAdapter = new ChooseAccountAdapter(mList, isPay);
        rvList.addItemDecoration(new DividerItemDecoration(context, OrientationHelper.VERTICAL, CommonUtils.dp2px(context, 10), ContextCompat.getColor(context, R.color.gray_bg_f6)));
        rvList.setLayoutManager(new LinearLayoutManager(context));

        mChooseAccountAdapter.bindToRecyclerView(rvList);
        mChooseAccountAdapter.setEmptyView(R.layout.layout_empty, clContainer);
        loadData();

        initListener();
    }

    private void initListener() {
        mChooseAccountAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (CommonUtils.isFastDoubleClick2()) {
                    return;
                }
                mSelChannel = mList.get(position);
                if (isPay) {
//判断快捷支付金额
                    checkSwipeMoney();
                } else {
                    requestBindStatus();
                }
            }
        });

    }

    /**
     * 获取通道列表
     */
    private void loadData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390013");
        httpParams.put("42", getMerNo());
        if (!isPay) {
            httpParams.put("43", zhia ? "QYK" : "YK");
        }
        httpParams.put("44", mBindCard.getID());
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
                    mList.addAll(list);
                    mChooseAccountAdapter.setNewData(mList);
                }
            }
        });
    }

    /**
     * 提交计划后，自动关闭页面
     *
     * @param event
     */
    @Subscribe
    public void onEvent(PlanCloseEvent event) {
        ViewUtils.overridePendingTransitionBack(context);
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        if (CommonUtils.isFastDoubleClick2()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_right:
                // : 2019/3/31 限额说明
//                backgroundAlpha(0.5f);
//                mPopupWindow.showAsDropDown(tvRight);
                initPopWindow();
                break;

        }
    }

    private void initPopWindow() {
        if (mList.size() == 0) {
            return;
        }
        //在这里使用新的视图
        View view = LayoutInflater.from(this).inflate(R.layout.layout_pingtai, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//初始化Adapter
        ChannelAdapter channelAdapter = new ChannelAdapter(mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(channelAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog mAlertDialog = builder.create();
        mAlertDialog.show();
        mAlertDialog.getWindow().setContentView(view);

        channelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, ImageActivity.class);
                intent.putExtra("title", mList.get(position).getChannelName());
                intent.putExtra("url", "http://120.78.81.147/icon/icon_channel_" + mList.get(position).getAcqcode() + ".png");
                startActivity(intent);
                mAlertDialog.dismiss();
            }
        });


    }


    /**
     * 判断快捷收款金额是否正确
     */
    private void checkSwipeMoney() {

        String bankName = mBindCard.getShort_cn_name().replace("银行", "");
        if (mSelChannel.getRemark().contains(bankName)) {
            ViewUtils.makeToast(ChooseAccountActivity.this, "该通道暂不支持" + mBindCard.getShort_cn_name(), 1500);
            return;
        }
        String limit = mSelChannel.getLimit().replace("/笔", "");
        String[] limits = limit.split("-");
        if (Double.parseDouble(limits[0]) <= Double.parseDouble(money) && Double.parseDouble(money) <= Double.parseDouble(limits[1])) {
            requestBindStatus();
        } else if (Double.parseDouble(limits[0]) > Double.parseDouble(money)) {
            ViewUtils.makeToast(ChooseAccountActivity.this, "您输入的收款金额小于" + limits[0] + "元，请输入" + limit + "元之间", 1500);
        } else if (Double.parseDouble(limits[1]) < Double.parseDouble(money)) {
            ViewUtils.makeToast(ChooseAccountActivity.this, "您输入的收款金额大于" + limits[1] + "元，请输入" + limit + "元之间", 1500);
        }
    }

    /**
     * 判断是否绑卡
     */
    private void requestBindStatus() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390021");
        httpParams.put("42", getMerNo());
        httpParams.put("45", mBindCard.getBANK_ACCOUNT());
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
                    LogUtils.i("36=" + model.getStr36());
                    List<AccountModel> list = JSONArray.parseArray(model.getStr36(), AccountModel.class);
                    for (AccountModel accountModel :
                            list) {
                        if (accountModel.getKstatus().contains(mSelChannel.getAcqcode())) {
                            if (TextUtils.equals("开通", accountModel.getStatus())) {
                                isSel = true;
                                if (isPay) {
                                    // : 2019/4/2 去下单
                                    placeAnOrder();
                                } else {
                                    Intent intent = new Intent(context, MakeDesignActivity.class);
                                    intent.putExtra("zhia", zhia);
                                    intent.putExtra("bindCard", mBindCard);
                                    intent.putExtra("channel", mSelChannel);
                                    startActivity(intent);
                                }
                                break;
                            } else {
                                isSel = true;
                                ViewUtils.makeToast(context, "未绑卡，请先绑卡", 500);
                                Intent intent = new Intent(context, BindCardActivity.class);
                                intent.putExtra("bindCard", mBindCard);
                                intent.putExtra("type", accountModel.getType());
                                intent.putExtra("category", accountModel.getCategory());
                                startActivity(intent);
                                break;
                            }
                        }
                    }
                    if ((!isSel && !isPay)) {
                        ViewUtils.makeToast(context, "请联系客服", 500);
                    } else if (!isSel) {
                        ViewUtils.makeToast(context, "请联系客服", 500);
                    }
                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }
        });
    }

    /**
     * 快捷支付
     */
    private void placeAnOrder() {
        loadingDialog.show();
        String moneyFen = CommonUtils.formatNewFen(money);
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190959");
        httpParams.put("5", moneyFen);
        if (!StringUtil.isEmpty(mSelChannel.getAcqMerchantNo())) {
            httpParams.put("40", mSelChannel.getAcqMerchantNo());
        }
        httpParams.put("41", mBindCard.getBANK_ACCOUNT());
        httpParams.put("42", getMerNo());
        httpParams.put("43", mSelChannel.getAcqcode());
        httpParams.put("44", mSelChannel.getRate());
        httpParams.put("45", mBindCard.getCvn());
        httpParams.put("46", mBindCard.getExpdate().replace("/", ""));
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
                    String url = model.getStr30();
                    if ("00".equals(url)) {
                        ViewUtils.makeToast(context, "下单成功请稍等，交易是否成功以您信用卡的扣款为准", 1500);
                        setResult(Activity.RESULT_OK);
                        finish();
                        return;
                    }
                    Intent intent = new Intent(context, X5WebViewActivity.class);
                    intent.putExtra("title", "无卡支付");
                    intent.putExtra("url", url);
                    intent.putExtra("rightTitle", "首页");
                    intent.putExtra("back", true);
                    startActivityForResult(intent, REWUEST_FINISH);

                    ViewUtils.makeToast(context, "下单成功请稍等", 1500);
                    ViewUtils.overridePendingTransitionCome(context);

                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REWUEST_FINISH && resultCode == RESULT_OK) {
            EventBus.getDefault().post(new SwipeCloseEvent());
            finish();
        }
    }

}
