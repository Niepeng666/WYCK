package com.linglingyi.com.viewone.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.callback.SuccessCallback;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.MakeCardModel;
import com.linglingyi.com.utils.CheckOutMessage;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/27 10:47
 * @功能 公告
 **/
public class CardsSecondMakeDesignDialog extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.et_inputPayAmount)
    EditText etInputPayAmount;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.btn_previewPlan)
    Button btnPreviewPlan;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    private MakeCardModel model;
    private SuccessCallback mSuccessCallback;
    private Activity mActivity;

    public static CardsSecondMakeDesignDialog getInstance(MakeCardModel model) {
        CardsSecondMakeDesignDialog dialog = new CardsSecondMakeDesignDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", model);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.custom_Dialog);
        model = (MakeCardModel) getArguments().getSerializable("model");
        mActivity = getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setSuccessCallback(SuccessCallback successCallback) {
        mSuccessCallback = successCallback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_cards_second_make_design, container);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
    }

    private void initData() {
        if (model == null) {
            return;
        }
        etInputPayAmount.setText( StringUtil.isEmpty(model.getDebtMoney()) ? "" : model.getDebtMoney());

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int cardDay = StringUtil.stringToInt(model.getBindCard().getREPAYMENT_DAY());
        LogUtils.i("month=" + month + ",day=" + day + ",cardDay=" + cardDay);
        if (day >= cardDay) {
//            当前时间>还款日
            calendar.add(Calendar.MONTH, 1);
        }
        calendar.set(Calendar.DAY_OF_MONTH, cardDay);
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        Date date = calendar.getTime();
        String dateStr = formatter.format(date);
        tvDate.setText(dateStr);
        model.setEndDate(dateStr);
    }


    @OnClick({R.id.btn_previewPlan, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_previewPlan:
                String inputPayAmount = etInputPayAmount.getText().toString().trim();
                if (TextUtils.isEmpty(inputPayAmount)) {
                    CheckOutMessage.isEmpty(mActivity, "还款金额", inputPayAmount);
                    return;
                }
                if (mSuccessCallback != null) {
                    model.setDebtMoney(inputPayAmount);
                    mSuccessCallback.success(model);
                    dismiss();
                }
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }
}
