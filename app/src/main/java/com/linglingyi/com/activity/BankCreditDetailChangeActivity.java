package com.linglingyi.com.activity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.BankCardEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankCreditDetailChangeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_account_2)
    TextView tvBankAccount2;
    @BindView(R.id.tv_name_2)
    TextView tvName2;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_id_card)
    TextView tvIdCard;
    @BindView(R.id.tv_limit)
    TextView tvLimit;
    @BindView(R.id.tv_bill_day)
    TextView tvBillDay;
    @BindView(R.id.tv_pay_day)
    TextView tvPayDay;
    @BindView(R.id.tv_dead_line)
    TextView tvDeadLine;
    @BindView(R.id.tv_cvv)
    TextView tvCvv;
    @BindView(R.id.ll_bank_info)
    LinearLayout llBankInfo;
    @BindView(R.id.et_limit)
    EditText etLimit;
    @BindView(R.id.et_bill_day)
    TextView etBillDay;
    @BindView(R.id.et_pay_day)
    TextView etPayDay;
    @BindView(R.id.ll_card_change)
    LinearLayout llCardChange;
    @BindView(R.id.btn_change)
    Button btnChange;
    private BindCard mBindCard;
    private DatePickerDialog mDatePickerDialog;
    private String limitMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_bank_credit_change;
    }

    @Override
    public void initData() {
        tvTitle.setText("修改卡信息");
        mBindCard = (BindCard) getIntent().getSerializableExtra("bindCard");


        tvBankName.setText(MyApplication.getBankName(mBindCard.getBANK_NAME()));
        tvBankAccount2.setText(mBindCard.getBANK_ACCOUNT());
        tvName2.setText(mBindCard.getBANK_ACCOUNT_NAME());
        tvPhone.setText(mBindCard.getBANK_PHONE());
        tvIdCard.setText(mBindCard.getID_CARD_NUMBER());
        tvLimit.setText(mBindCard.getLIMIT_MONEY());
        tvBillDay.setText(mBindCard.getBILL_DAY());
        tvPayDay.setText(mBindCard.getREPAYMENT_DAY());
        tvDeadLine.setText(mBindCard.getExpdate());
        tvCvv.setText(mBindCard.getCvn());

        tvBillDay.setText(String.format("%s", mBindCard.getBILL_DAY()));
        tvPayDay.setText(String.format("%s", mBindCard.getREPAYMENT_DAY()));
        etLimit.setText(mBindCard.getLIMIT_MONEY());
        etBillDay.setText(mBindCard.getBILL_DAY());
        etPayDay.setText(mBindCard.getREPAYMENT_DAY());

    }

    @OnClick({R.id.iv_back, R.id.btn_change, R.id.et_bill_day, R.id.et_pay_day})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_change:
                limitMoney = etLimit.getText().toString();
                if (StringUtil.isEmpty(limitMoney)) {
                    ViewUtils.makeToast(context, "请输入信用卡额度", 500);
                    return;
                }
                submit();
                break;
            case R.id.et_bill_day:
                if (mDatePickerDialog != null && mDatePickerDialog.isShowing()) {
                    return;
                }
                showTimePickerDialog(1);
                break;
            case R.id.et_pay_day:
                if (mDatePickerDialog != null && mDatePickerDialog.isShowing()) {
                    return;
                }
                showTimePickerDialog(2);
                break;
            default:
                break;
        }
    }

    private void submit() {
        // TODO: 2019/7/2
        final String billDay = etBillDay.getText().toString().trim();
        final String payDay = etPayDay.getText().toString().trim();
        loadingDialog.show();
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("3", "690101");
        map.put("42", mBindCard.getID());
        map.put("43", StringUtil.getNumbers(billDay));
        map.put("44", StringUtil.getNumbers(payDay));
        map.put("41", limitMoney);
        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    ViewUtils.makeToast(context,
                            "尊敬的客户，您的信用卡修改成功", 1500);
                    setResult(RESULT_OK);
                    EventBus.getDefault().post(new BankCardEvent());
                    ViewUtils.overridePendingTransitionBack(context);
                }
            }
        });
    }


    /**
     * 展示日期选择对话框
     *
     * @param type
     */
    private void showTimePickerDialog(final int type) {

        //获取当前日期
        TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                LogUtils.i("date=" + DateUtil.formateDateTOYMD(date.getTime()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                if (type == 1) {
                    etBillDay.setText(String.format("%d", day));
                } else {
                    etPayDay.setText(String.format("%d", day));
                }
            }
        })
                .setType(new boolean[]{false, false, true, false, false, false})
                .isDialog(false)
                .build();
        pvTime.show();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mDatePickerDialog != null && mDatePickerDialog.isShowing()) {
            mDatePickerDialog.dismiss();
        }

    }


}
