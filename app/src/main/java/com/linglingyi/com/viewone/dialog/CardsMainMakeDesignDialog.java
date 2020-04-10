package com.linglingyi.com.viewone.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.ChoiceAreaActivity;
import com.linglingyi.com.callback.SuccessCallback;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.MakeCardModel;
import com.linglingyi.com.utils.CheckOutMessage;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/27 10:47
 * @功能 公告
 **/
public class CardsMainMakeDesignDialog extends DialogFragment {


    @BindView(R.id.et_inputPayAmount)
    EditText etInputPayAmount;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.et_reserve_money)
    EditText etReserveMoney;
    @BindView(R.id.switch_defalut)
    Switch switchDefalut;
    @BindView(R.id.btn_previewPlan)
    Button btnPreviewPlan;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    Unbinder unbinder;
    private MakeCardModel model;
    private Activity mActivity;
    private HashMap<String, Area> area;
    private String cityId;
    private SuccessCallback mSuccessCallback;

    public static CardsMainMakeDesignDialog getInstance(MakeCardModel model) {
        CardsMainMakeDesignDialog dialog = new CardsMainMakeDesignDialog();
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

    @OnClick({R.id.tv_city, R.id.btn_previewPlan, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                startActivityForResult(new Intent(mActivity, ChoiceAreaActivity.class)
                                .putExtra("onlyP_C", true)
                                .putExtra("zhia", false)
                                .putExtra("bindid", model.getBankId())
                        , 1);
                break;
            case R.id.btn_previewPlan:
                String inputPayAmount = etInputPayAmount.getText().toString().trim();
                String inputWorkfund = etReserveMoney.getText().toString().trim();
                if (TextUtils.isEmpty(inputPayAmount)) {
                    CheckOutMessage.isEmpty(mActivity, "还款金额", inputPayAmount);
                    return;
                }
                if (StringUtil.isEmpty(cityId)) {
                    ViewUtils.makeToast(mActivity, "请选择地区", 500);
                    return;
                }
                if (TextUtils.isEmpty(inputWorkfund)) {
                    CheckOutMessage.isEmpty(mActivity, "卡内预留金", inputWorkfund);
                    return;
                }
                if (mSuccessCallback != null) {
                    model.setBalanecMoney(inputWorkfund);
                    model.setDebtMoney(inputPayAmount);
                    model.setCityId(cityId);
                    mSuccessCallback.success(model);
                    dismiss();
                }
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // : 2019/4/1 选择了商户后
            area = (HashMap<String, Area>) data.getSerializableExtra("data");
            cityId = area.get("city").getId();
            tvCity.setText(String.format("%s-%s", area.get("province").getDivisionName(), area.get("city").getDivisionName()));
            model.setProvinceCity(area.get("province").getDivisionName() + "-" + area.get("city").getDivisionName());
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_cards_main_make_design, container);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        switchDefalut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                是否返还原卡
                LogUtils.i("isChecked" + isChecked);
                model.setBackOldCard(isChecked);
            }
        });
    }

    private void initData() {
        if (model == null) {
            return;
        }
        if (model.getDebtMoney() != null) {
            etInputPayAmount.setText(model.getDebtMoney());
        }
        if (model.getBalanecMoney() != null) {
            etReserveMoney.setText(model.getBalanecMoney());
        }
        if (model.getProvinceCity() != null) {
            tvCity.setText(model.getProvinceCity());
            cityId = model.getCityId();
        }
        switchDefalut.setChecked(model.isBackOldCard());


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
}
