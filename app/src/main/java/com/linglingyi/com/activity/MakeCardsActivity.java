package com.linglingyi.com.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.MakeCardsAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.callback.SuccessCallback;
import com.linglingyi.com.event.CardsPlanCloseEvent;
import com.linglingyi.com.event.PlanCloseEvent;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.CardsModel;
import com.linglingyi.com.model.MakeCardModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.viewone.dialog.CardsMainMakeDesignDialog;
import com.linglingyi.com.viewone.dialog.CardsSecondMakeDesignDialog;

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
 * @date: 2019/10/25
 */
public class MakeCardsActivity extends BaseActivity {
    private final static Integer SELECT_CARD = 0x01;
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

    private List<MakeCardModel> mList = new ArrayList<>();
    private List<MakeCardModel> mSelcardList = new ArrayList<>();
    private MakeCardsAdapter mMakeCardsAdapter;
    /**
     * 银行卡数据
     */
    private BindCard mBindCard;
    /**
     * 当前选择的位置 0 主卡
     */
    private int selPosition;
    private CardsModel mCardsModel = new CardsModel();

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
        return R.layout.act_make_cards;
    }

    @Override
    public void initData() {
        tvTitle.setText("制定计划");
        EventBus.getDefault().register(this);
        MakeCardModel model1 = new MakeCardModel();
        MakeCardModel model2 = new MakeCardModel();
        MakeCardModel model3 = new MakeCardModel();
        mList.add(model1);
        mList.add(model2);
        mList.add(model3);

        mMakeCardsAdapter = new MakeCardsAdapter(mList);
        rvList.setLayoutManager(new LinearLayoutManager(context));

        mMakeCardsAdapter.bindToRecyclerView(rvList);
        mMakeCardsAdapter.addFooterView(getFooterView());
        initListener();
    }

    private void initListener() {
        mMakeCardsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.cl_add_card:
                        selPosition = position;
                        goBankCardAct();
                        break;
                    case R.id.btn_make:
                        if (position == 0) {
//                            主卡
                            CardsMainMakeDesignDialog cardsMainMakeDesignDialog = CardsMainMakeDesignDialog.getInstance(mList.get(position));
                            cardsMainMakeDesignDialog.show(getSupportFragmentManager(), "cardsMain");
                            cardsMainMakeDesignDialog.setSuccessCallback(new SuccessCallback<MakeCardModel>() {
                                @Override
                                public void success(MakeCardModel model) {
                                    mList.set(position, model);
                                    mMakeCardsAdapter.notifyItemChanged(position);
                                    mCardsModel.setCityId(model.getCityId());
                                    mCardsModel.setArea(model.getProvinceCity());
                                    mCardsModel.setBackOldCard(model.isBackOldCard());
                                    mCardsModel.setInputWorkFund(model.getBalanecMoney());

                                }
                            });
                        } else {
//                            副卡
                            CardsSecondMakeDesignDialog cardsSecondMakeDesignDialog = CardsSecondMakeDesignDialog.getInstance(mList.get(position));
                            cardsSecondMakeDesignDialog.show(getSupportFragmentManager(), "cardsSecond");
                            cardsSecondMakeDesignDialog.setSuccessCallback(new SuccessCallback<MakeCardModel>() {
                                @Override
                                public void success(MakeCardModel model) {
                                    mList.set(position, model);
                                    mMakeCardsAdapter.notifyItemChanged(position);
                                }
                            });
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 进入卡片列表页
     */
    private void goBankCardAct() {
        StringBuilder stringBuilder = new StringBuilder();
        for (MakeCardModel model : mList
                ) {
            if (stringBuilder.length() > 1) {
                stringBuilder.append(",");
            }
            if (model.getBankAccount() != null && !StringUtil.isEmpty(model.getBankAccount())) {
                stringBuilder.append(model.getBankAccount());

            }
        }

        Intent intent = new Intent(context, BankCardListActivity.class);
        intent.putExtra("title", "选择卡片");
        intent.putExtra("isCards", true);
        intent.putExtra("cards", stringBuilder.toString());
        startActivityForResult(intent, SELECT_CARD);
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
// TODO: 2019/10/25 添加计划,至少选择两张卡片
                int num = 0;
                int planNum = 0;
                for (MakeCardModel cardModel :
                        mList) {
                    if (cardModel.isAdd()) {
                        num++;
                    }
                    if (!StringUtil.isEmpty(cardModel.getDebtMoney())) {
                        planNum++;
                    }
                }
                if (num < 2) {
                    ViewUtils.makeToast(context, "请至少添加两张卡片", 500);
                    return;
                }
                if (!mList.get(0).isAdd()){
                    ViewUtils.makeToast(context, "请添加主卡", 500);
                    return;
                }
                if (planNum < 2) {
                    ViewUtils.makeToast(context, "请先制定计划", 500);
                    return;
                }
                if (num!=planNum) {
                    ViewUtils.makeToast(context, "还有卡片没有制定计划", 500);
                    return;
                }
                mSelcardList.clear();
                for (MakeCardModel cardModel :
                        mList) {
                    if (cardModel.isAdd()) {
                        mSelcardList.add(cardModel);
                    }
                }
                mCardsModel.setMakeCardModelList(mSelcardList);
                goChannel();
            }
        });
        return tvAdd;
    }

    /**
     * 进入选择通道页面
     */
    private void goChannel() {
        Intent intent = new Intent(context, CardsChannelActivity.class);
        intent.putExtra("cardsModel", mCardsModel);
        startActivity(intent);
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

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_CARD) {
            mBindCard = (BindCard) data.getSerializableExtra("bindCard");
            MakeCardModel cardModel = mList.get(selPosition);
            cardModel.setBindCard(mBindCard);
            cardModel.setAdd(true);
            cardModel.setBankId(mBindCard.getID());
            cardModel.setBankAccount(mBindCard.getBANK_ACCOUNT());
            mList.set(selPosition, cardModel);
            mMakeCardsAdapter.notifyItemChanged(selPosition);
        }
    }
}
