package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.BenefitEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CheckOutMessage;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.dialog.WithdrawalDialog;
import com.linglingyi.com.viewone.dialog.withdrawPassDialog;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/21 17:42
 * @功能 提现页面
 **/
public class WithdrawalActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.textView01)
    TextView textView01;
    @BindView(R.id.tv_bank_account)
    TextView tvBankAccount;
    @BindView(R.id.textView02)
    TextView textView02;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.imageView01)
    View imageView01;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_allMoney)
    TextView tvAllMoney;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.bank_iv)
    ImageView bankIv;
    @BindView(R.id.bank_tv)
    TextView bankTv;
    @BindView(R.id.bank_llt)
    LinearLayout bankLlt;
    @BindView(R.id.tv_fee)
    TextView tvFee;
    private String loanAmount;
    private String money;
    private withdrawPassDialog mWithdrawPassDialog;
    private String type = "1";
    /**
     * 提现手续费
     */
    private String fee;

    @OnClick({R.id.iv_back, R.id.btn_submit, R.id.tv_allMoney, R.id.tv_right})
    public void onViewClicked(View view) {
        if (CommonUtils.isFastDoubleClick2()) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_submit:
                money = etMoney.getText().toString().trim();
                if (CheckOutMessage.isEmpty(context, "金额", money)) {
                    return;
                }
                if (".".equals(money)) {
                    ViewUtils.makeToast(context, "请输入正确的金额", 500);
                    return;
                }
                submit(money);
//                final WithdrawalDialog dialog = WithdrawalDialog.getInstance("", money,
//                        fee);
//                dialog.setOnButtonClickListener(new WithdrawalDialog.OnButtonClickListener() {
//                    @Override
//                    public void onConfirmClick() {
//                        dialog.dismiss();
//                        submit(money);
//                    }
//                });
//                dialog.show(getFragmentManager(), "");

                break;
            case R.id.tv_allMoney:
                etMoney.setText(loanAmount);
                break;
            case R.id.tv_right:
                startActivity(new Intent(context, WithdrawListActivity.class).putExtra("type",type));
                break;
        }
    }

    private void submit(String money) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190888");
        httpParams.put("5", money);
        if ("2".equals(type)){
            httpParams.put("43","10L");
        }
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
                    ViewUtils.makeToast(context, "提现成功", 500);
                    EventBus.getDefault().post(new BenefitEvent());
                    finish();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_withdrawal;
    }

    @Override
    public void initData() {
        tvTitle.setText("提现");
        tvRight.setText("提现记录");
        tvRight.setVisibility(View.VISIBLE);
        loanAmount = getIntent().getStringExtra("money");
        type = getIntent().getStringExtra("type");//1:默认余额提现，2：领主余额
        fee = StorageCustomerInfo02Util.getInfo("withdrawFee", context);
        tvMoney.setText("账户余额：￥" + loanAmount);
        tvFee.setText("提现手续费：" + fee + "元/笔，100元起提");
        String bankAccounts = StorageCustomerInfo02Util.getInfo("bankAccount", context);
        if (!StringUtil.isEmpty(bankAccounts) && bankAccounts.length() > 0) {
            String str = bankAccounts.substring(bankAccounts.length() - 4, bankAccounts.length());
            tvBankAccount.setText("尾号" + str);
        }
        String bankName = StorageCustomerInfo02Util.getInfo("bankDetail", context);
        bankTv.setText(bankName);


        Utils.initBankCodeColorIcon(MyApplication.bankNameList.get(bankName), bankIv, context);

    }
}