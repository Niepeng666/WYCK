package com.linglingyi.com.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.linglingyi.com.adapter.ManagerCreditAdapter;
import com.linglingyi.com.callback.CancelCallback;
import com.linglingyi.com.event.SwipeCloseEvent;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.PermissionUtil;
import com.linglingyi.com.viewone.dialog.BankCardDialog;
import com.linglingyi.com.viewone.dialog.BindParentPhoneDialog;
import com.linglingyi.com.viewone.dialog.NoticeDialog;
import com.linglingyi.com.viewone.dialog.TipDialog;
import com.wuyouchuangke.com.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.linglingyi.com.adapter.BankCardListAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.BankCardEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 信用卡列表页
 */
public class BankCardListActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.cl_container)
    RelativeLayout clContainer;

    private ManagerCreditAdapter mBankCardListAdapter;
    private List<BindCard> mList = new ArrayList<>();
    /**
     * 收款 金额
     */

    private boolean isPay, zhia;
    private String money;

    private BindCard chooseBindCard;

    private boolean isCardManager;
    private String acqcode;
    /**
     * 上级
     */
    private String parentPhone;
    /**
     * 是否从一卡多还进入
     */
    private boolean isCards;

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
        return R.layout.activity_bank_card_list;
    }

    @Override
    public void initData() {
        String title = getIntent().getStringExtra("title");
        tvTitle.setText(!StringUtil.isEmpty(title) ? title : "卡包");
//        ivRight.setVisibility(View.VISIBLE);
//        ivRight.setImageResource(R.drawable.add_icon);
        zhia = getIntent().getBooleanExtra("zhia", false);
        isPay = getIntent().getBooleanExtra("is2Pay", false);
        isCardManager = getIntent().getBooleanExtra("isCardManager", false);
        money = getIntent().getStringExtra("money");
        isCards = getIntent().getBooleanExtra("isCards", false);

        srlRefresh.setRefreshHeader(new ClassicsHeader(context));

        mBankCardListAdapter = new ManagerCreditAdapter(mList);
        mBankCardListAdapter.setIs2Pay(isPay);
        mBankCardListAdapter.setCardManager(isCardManager);
        mBankCardListAdapter.setCards(isCards);
        mBankCardListAdapter.setMakeType(isPay ? "立即收款" : "立即还款");
        rvList.setLayoutManager(new LinearLayoutManager(context));

        mBankCardListAdapter.bindToRecyclerView(rvList);
        if (!isCards) {
            mBankCardListAdapter.addFooterView(getFooterView());
        }
        initListener();
        requestData(true);
    }

    private View getFooterView() {

        TextView tvAdd = new TextView(context);
        tvAdd.setText("+ 添加信用卡");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);
        params.setMargins(10, 50, 10, 0);
        tvAdd.setGravity(Gravity.CENTER);
        tvAdd.setBackgroundColor(Color.WHITE);
        tvAdd.setLayoutParams(params);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                if (!checkBindCard()) {
                    return;
                }
                startActivity(new Intent(context, AddBankCardActivity.class));
            }
        });
        return tvAdd;
    }

    /**
     * 提交一卡多还计划后，自动关闭页面
     *
     * @param event
     */
    @Subscribe
    public void onEvent(SwipeCloseEvent event) {
        ViewUtils.overridePendingTransitionBack(context);
    }


    private void initListener() {
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                requestData(false);
            }
        });

        mBankCardListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BindCard bindCard = mList.get(position);
                if (isCards) {
                    if (bindCard.getPlanCount() > 0) {
                        ViewUtils.makeToast(context, "该卡正在执行计划，请选择其他卡片", 500);
                        return;
                    }
                    if (getIntent().getStringExtra("cards").contains(bindCard.getBANK_ACCOUNT())) {
                        ViewUtils.makeToast(context, "已选择该卡", 500);
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("bindCard", bindCard);
                    setResult(RESULT_OK, intent);
                    finish();
                    return;
                }
                if (isPay) {
                    Intent swipeIntent = new Intent(context, ChooseAccountActivity.class);
                    swipeIntent.putExtra("BindCard", bindCard);
                    swipeIntent.putExtra("is2Pay", isPay);
                    swipeIntent.putExtra("money", money);
                    startActivity(swipeIntent);
                } else {
                    BankCardDialog bankCardDialog = BankCardDialog.getIntence(bindCard);
                    bankCardDialog.show(getSupportFragmentManager(), "");
                }
            }
        });
        mBankCardListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (CommonUtils.isFastDoubleClick2()) {
                    return;
                }
                chooseBindCard = mList.get(position);
                switch (view.getId()) {
                    case R.id.ll_make_design:
                        if (chooseBindCard.getPlanCount() > 0) {
                            final Dialog dialog = new Dialog(context, R.style.MyProgressDialog);
                            dialog.setContentView(R.layout.dialog_plan_tip);
                            TextView bt_known = (TextView) dialog.findViewById(R.id.btn);
                            dialog.setCanceledOnTouchOutside(true);
                            bt_known.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                            return;
                        }
                        showPopupWindow();
                        break;
                    case R.id.tv_look_plan:
                        goLookPlan(position);
                        break;
                    case R.id.tv_look_data:
                        goLookData(position);
                        break;
                    case R.id.tv_unbind:
                    case R.id.tv_card_unbind:
                        showDelDialog(position);
                        break;
                    case R.id.tv_card_score:
                        // : 2019/8/22 评分
                        startActivity(new Intent(context, PercentScoreActivity.class));
                        break;
                    case R.id.btn_make:
                        if (isPay) {
                            Intent swipeIntent = new Intent(context, ChooseAccountActivity.class);
                            swipeIntent.putExtra("BindCard", chooseBindCard);
                            swipeIntent.putExtra("is2Pay", isPay);
                            swipeIntent.putExtra("money", money);
                            startActivity(swipeIntent);
                            return;
                        }

                        if (chooseBindCard.getPlanCount() > 0) {
                            final Dialog dialog = new Dialog(context, R.style.MyProgressDialog);
                            dialog.setContentView(R.layout.dialog_plan_tip);
                            TextView bt_known = (TextView) dialog.findViewById(R.id.btn);
                            dialog.setCanceledOnTouchOutside(true);
                            bt_known.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                            return;
                        }
                        BankCardDialog bankCardDialog = BankCardDialog.getIntence(chooseBindCard);
                        bankCardDialog.show(getSupportFragmentManager(), "");
//                        showPopupWindow();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 进入查看资料
     *
     * @param position
     */
    private void goLookData(int position) {
        Intent intent = new Intent(context, BankCreditDetailActivity.class);
        intent.putExtra("bindCard", mList.get(position));
        intent.putExtra("title", "查看资料");
        startActivity(intent);
    }


    /**
     * 获取信用卡列表
     */
    private void requestData(final boolean showParentDialog) {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190932");
        httpParams.put("42", getMerNo());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
                srlRefresh.finishRefresh();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                srlRefresh.finishRefresh();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    acqcode = model.getStr31();
                    parentPhone = model.getStr40();
                    mList = JSONArray.parseArray(model.getStr57(), BindCard.class);
                    mBankCardListAdapter.setNewData(mList);
                    if (showParentDialog) {
                        if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
                            loadParentDialog();
                        }
                    }

                }
            }
        });
    }

    /**
     * 显示绑定推荐人弹框
     */
    private void loadParentDialog() {
        final BindParentPhoneDialog parentPhoneDialog = BindParentPhoneDialog.getInstance();
        parentPhoneDialog.show(getSupportFragmentManager(), "parent");
        parentPhoneDialog.setCancelCallback(new CancelCallback() {
            @Override
            public void cancel() {
                LogUtils.i("cancel");
                parentPhoneDialog.dismiss();
                ViewUtils.overridePendingTransitionBack(context);
            }
        });
    }


    private void showDelDialog(final int position) {
        new android.app.AlertDialog.Builder(context)
                .setTitle("解绑信用卡")
                .setMessage("是否解绑信用卡")
                .setPositiveButton("解绑", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delCreditCard(position);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    /**
     * 解绑信用卡
     *
     * @param position
     */
    private void delCreditCard(int position) {
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190520");
        httpParams.put("8", mList.get(position).getBANK_ACCOUNT());
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
                    ViewUtils.makeToast(context, "解绑成功", 1500);
                    srlRefresh.autoRefresh();
                }
            }
        });
    }

    /**
     * @param position
     */
    private void goLookPlan(int position) {
        Intent intent = new Intent(context, LookPlanActivity.class);
        intent.putExtra("bindCard", mList.get(position));
        startActivity(intent);
    }

    /**
     * 显示预留， 无预留
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_select_card, null);
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(false);
        mPopWindow.setTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(0.5f);
//添加pop窗口关闭事件
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        TextView wuyuliu = (TextView) contentView.findViewById(R.id.tv_qyk);
        TextView yuliu = (TextView) contentView.findViewById(R.id.tv_yk);
        TextView quxiao = (TextView) contentView.findViewById(R.id.tv_cancel);
        wuyuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (StorageCustomerInfo02Util.getIntInfo(context, "level", 1) == 1) {
//                    ViewUtils.makeToast(context, "请先升级成VIP用户", 500);
//                    return;
//                }
                mPopWindow.dismiss();
                Intent makeDesignIntent = new Intent();
                makeDesignIntent.setClass(context, ChooseAccountActivity.class);
                makeDesignIntent.putExtra("BindCard", chooseBindCard);
                makeDesignIntent.putExtra("zhia", true);
                startActivityForResult(makeDesignIntent, 1000);
            }
        });
        yuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
                Intent makeDesignIntent2 = new Intent();
                makeDesignIntent2.setClass(context, ChooseAccountActivity.class);
                makeDesignIntent2.putExtra("BindCard", chooseBindCard);
                makeDesignIntent2.putExtra("zhia", false);
                startActivityForResult(makeDesignIntent2, 1000);
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        mPopWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });


        mPopWindow.showAtLocation(clContainer, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }


    /**
     * 绑卡成功后，自动刷新数据
     *
     * @param event
     */
    @Subscribe
    public void onEvent(BankCardEvent event) {
        srlRefresh.autoRefresh();
    }

    @OnClick({R.id.iv_back, R.id.iv_right, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.iv_right:
//                // TODO: 2019/3/29 添加信用卡
//                if (!checkCustomerInfoCompleteShowToast()) {
//                    return;
//                }
//                if (!checkBindCard()) {
//                    return;
//                }
//                startActivity(new Intent(context, AddBankCardActivity.class));
                break;
            case R.id.btn_add:
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
//                if (!checkBindCard()) {
//                    return;
//                }
                startActivity(new Intent(context, AddBankCardActivity.class));
                break;
        }
    }
}
