package com.linglingyi.com.viewone.dialog;

import android.app.Dialog;
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
import com.linglingyi.com.model.PreviewPlanModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/27 10:47
 * @功能 公告
 **/
public class PlanTotalPriceDialog extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_workingFund)
    TextView tvWorkingFund;
    @BindView(R.id.tv_repayment_fee)
    TextView tvRepaymentFee;
    @BindView(R.id.tv_pay_time_fee)
    TextView tvPayTimeFee;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.ll_workingfound)
    RelativeLayout llWorkingfound;
    @BindView(R.id.tv_total_title)
    TextView tvTotalTitle;

    private PreviewPlanModel model;
    private boolean showTotalPrice=true;

    public static PlanTotalPriceDialog getInstance(PreviewPlanModel model, boolean showTotalPrice) {
        PlanTotalPriceDialog dialog = new PlanTotalPriceDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", model);
        bundle.putSerializable("showTotalPrice", showTotalPrice);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.custom_Dialog);
        model = (PreviewPlanModel) getArguments().getSerializable("model");
        showTotalPrice = getArguments().getBoolean("showTotalPrice");
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
        View view = inflater.inflate(R.layout.dialog_plan_total_price, container);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        if (model == null) {
            return;
        }
        tvWorkingFund.setText(model.getWorkingFund());
        tvRepaymentFee.setText(model.getFee());
        tvPayTimeFee.setText(model.getTimesFee());
        tvTotalPrice.setText(model.getTotalFee());
        if (!showTotalPrice){
            llWorkingfound.setVisibility(View.GONE);
            tvTotalTitle.setText("手续费小计");
            tvTotalPrice.setText(model.getTotalServiceFee());
        }
    }


    @OnClick(R.id.iv_close)
    public void onViewClicked() {
        dismiss();
    }
}
