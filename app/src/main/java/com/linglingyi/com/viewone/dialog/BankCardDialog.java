package com.linglingyi.com.viewone.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.BankCreditDetailActivity;
import com.linglingyi.com.activity.CardsPlanDetailActivity;
import com.linglingyi.com.activity.ChannelReportActivity;
import com.linglingyi.com.activity.LookPlanActivity;
import com.linglingyi.com.activity.MakeNewDesignActivity;
import com.linglingyi.com.activity.PlanCardsActivity;
import com.linglingyi.com.activity.PreviewCardsDetailActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/27 10:47
 * @功能 公告
 **/
public class BankCardDialog extends DialogFragment {


    @BindView(R.id.iv_bank_icon)
    ImageView ivBankIcon;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_account)
    TextView tvBankAccount;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_make_yk_design)
    TextView tvMakeYkDesign;
    @BindView(R.id.tv_yk_plan_tip)
    TextView tvYkPlanTip;
    @BindView(R.id.ll_make_yk_design)
    RelativeLayout llMakeYkDesign;
    @BindView(R.id.tv_make_qyk_design)
    TextView tvMakeQykDesign;
    @BindView(R.id.tv_qyk_plan_tip)
    TextView tvQykPlanTip;
    @BindView(R.id.ll_make_qyk_design)
    RelativeLayout llMakeQykDesign;
    @BindView(R.id.tv_make_design)
    TextView tvMakeDesign;
    @BindView(R.id.tv_channels_plan_tip)
    TextView tvChannelsPlanTip;
    @BindView(R.id.ll_make_channels_design)
    RelativeLayout llMakeChannelsDesign;
    @BindView(R.id.tv_look_plan)
    TextView tvLookPlan;
    @BindView(R.id.tv_look_data)
    TextView tvLookData;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    Unbinder unbinder;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_cards_design)
    TextView tvCardsDesign;
    @BindView(R.id.tv_cards_plan_tip)
    TextView tvCardsPlanTip;
    @BindView(R.id.ll_make_cards_design)
    RelativeLayout llMakeCardsDesign;
    private BindCard model;

    public static BankCardDialog getIntence(BindCard model) {
        BankCardDialog dialog = new BankCardDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", model);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.custom_Dialog);
        model = (BindCard) getArguments().getSerializable("model");
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.85), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bank_card, container);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        if (model == null) {
            return;
        }
        String banNum = model.getBANK_ACCOUNT();
        if (banNum.length() > 4) {
            String bankNum1 = banNum.substring(0, 4);
            String bankNum2 = banNum.substring(banNum.length() - 4, banNum.length());
            tvBankAccount.setText(bankNum1 + " **** **** " + bankNum2);
        }
        tvBankName.setText(model.getShort_cn_name());
        Utils.initBankCodeColorIcon(model.getBANK_NAME(), ivBankIcon, getActivity());
        tvName.setText("持卡人：" + CommonUtils.translateShortNumber(model.getBANK_ACCOUNT_NAME(), 1, 0));
        tvPhone.setText("预留手机号：" + CommonUtils.translateShortNumber(model.getBANK_PHONE(), 3, 4));
        if (model.getPlanCount() > 0) {
            switch (model.getType()) {
                case "10B":
//                    预留还款
                    tvYkPlanTip.setText("进行中");
                    break;
                case "10C":
//                    全额还
                    tvQykPlanTip.setText("进行中");
                    break;
                case "10D":
//                    多通道
                    tvChannelsPlanTip.setText("进行中");
                    break;
                case "10Y":
//                    一卡多还
                    tvCardsPlanTip.setText("进行中");
                    break;
            }
        }
    }

    @OnClick({R.id.ll_make_yk_design, R.id.ll_make_qyk_design, R.id.ll_make_channels_design,
            R.id.iv_close, R.id.tv_look_plan, R.id.tv_look_data, R.id.ll_make_cards_design})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_make_yk_design:
                if (model.getPlanCount() > 0) {
                    final Dialog dialog = new Dialog(getActivity(), R.style.MyProgressDialog);
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
                goMakeDesign(false);
                dismiss();
                break;
            case R.id.ll_make_qyk_design:
                if (model.getPlanCount() > 0) {
                    final Dialog dialog = new Dialog(getActivity(), R.style.MyProgressDialog);
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
                goMakeDesign(true);
                dismiss();
                break;
            case R.id.ll_make_channels_design:
                if (model.getPlanCount() > 0) {
                    final Dialog dialog = new Dialog(getActivity(), R.style.MyProgressDialog);
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
                loadUseChannel();

                break;
            case R.id.ll_make_cards_design:
                // TODO: 2019/10/25 一卡多还
                startActivity(new Intent(getActivity(), PlanCardsActivity.class));
                dismiss();
                break;
            case R.id.tv_look_plan:
                goLookPlan();
                dismiss();
                break;
            case R.id.tv_look_data:
                goLookData();
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    /**
     * 获取可以用的通道
     */
    private void loadUseChannel() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390022");
        httpParams.put("42", StorageCustomerInfo02Util.getInfo("customerNum", getActivity()));
        httpParams.put("45", model.getBANK_ACCOUNT());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(getActivity(), BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);

            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);

                BaseEntity model1 = response.body();
                if (model1 == null) {
                    return;
                }
                if ("00".equals(model1.getStr39())) {
                    if (CommonUtils.strToInt(model1.getStr35()) >= 2) {
                        Intent intent = new Intent(getActivity(), MakeNewDesignActivity.class);
                        intent.putExtra("zhia", false);
                        intent.putExtra("channels", true);
                        intent.putExtra("bindCard", model);
                        startActivity(intent);
                        dismiss();
                    } else {
                        Intent intent = new Intent(getActivity(), ChannelReportActivity.class);
                        intent.putExtra("bindCard", model);
                        startActivity(intent);
                        dismiss();
                    }

                }
            }
        });
    }

    private void goMakeDesign(boolean zhia) {
        Intent intent = new Intent(getActivity(), MakeNewDesignActivity.class);
        intent.putExtra("zhia", zhia);
        intent.putExtra("bindCard", model);
        startActivity(intent);

    }

    /**
     * 查看资料
     */
    private void goLookData() {
        Intent intent = new Intent(getActivity(), BankCreditDetailActivity.class);
        intent.putExtra("bindCard", model);
        intent.putExtra("title","查看资料");
        startActivity(intent);
    }

    /**
     * 查看计划
     */
    private void goLookPlan() {
        Intent intent = new Intent(getActivity(), LookPlanActivity.class);
        intent.putExtra("bindCard", model);
        startActivity(intent);
    }

}
