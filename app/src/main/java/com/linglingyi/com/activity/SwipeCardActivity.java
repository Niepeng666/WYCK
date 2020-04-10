package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.event.CardsPlanCloseEvent;
import com.linglingyi.com.event.SwipeCloseEvent;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SwipeCardActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.bank_iv)
    ImageView bankIv;
    @BindView(R.id.bank_tv)
    TextView bankTv;
    @BindView(R.id.tv_bank_account)
    TextView tvBankAccount;
    @BindView(R.id.ll_bank)
    LinearLayout llBank;
    @BindView(R.id.shoukuan)
    TextView shoukuan;
    @BindView(R.id.tv_money)
    EditText tvMoney;
    @BindView(R.id.ll_money)
    LinearLayout llMoney;
    @BindView(R.id.calculator_seven_menu)
    LinearLayout calculatorSevenMenu;
    @BindView(R.id.calculator_four_menu)
    LinearLayout calculatorFourMenu;
    @BindView(R.id.calculator_eight_menu)
    LinearLayout calculatorEightMenu;
    @BindView(R.id.calculator_five_menu)
    LinearLayout calculatorFiveMenu;
    @BindView(R.id.calculator_nine_menu)
    LinearLayout calculatorNineMenu;
    @BindView(R.id.calculator_six_menu)
    LinearLayout calculatorSixMenu;
    @BindView(R.id.number1_ll)
    LinearLayout number1Ll;
    @BindView(R.id.calculator_one_menu)
    LinearLayout calculatorOneMenu;
    @BindView(R.id.calculator_zero_menu)
    LinearLayout calculatorZeroMenu;
    @BindView(R.id.calculator_two_menu)
    LinearLayout calculatorTwoMenu;
    @BindView(R.id.calculator_point_menu)
    LinearLayout calculatorPointMenu;
    @BindView(R.id.calculator_three_menu)
    LinearLayout calculatorThreeMenu;
    @BindView(R.id.calculator_twozero_menu)
    LinearLayout calculatorTwozeroMenu;
    @BindView(R.id.number2_ll)
    LinearLayout number2Ll;
    @BindView(R.id.number_ll)
    LinearLayout numberLl;
    private StringBuffer calculator_num = null;// 计算器的输入数字
    private StringBuffer content = null;// 输入的金额

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public int initLayout() {
        return R.layout.activity_swipe_card;
    }

    @Override
    public void initData() {
        tvTitle.setText("收  款");
        EventBus.getDefault().register(this);
        updateAccount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

    /**
     * 显示储蓄卡信息
     */
    private void updateAccount() {
        if (!checkAuth()) {
            bankIv.setVisibility(View.GONE);
            bankTv.setText("未实名");
        } else if (!checkBindCard()) {
            bankIv.setVisibility(View.GONE);
            bankTv.setText("未绑定储蓄卡");
        } else {
            String bankAccount = StorageCustomerInfo02Util.getInfo("bankAccount", context);
            if (!StringUtil.isEmpty(bankAccount) && bankAccount.length() > 4) {
                String bankNum2 = bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
                tvBankAccount.setText("尾号" + bankNum2);
            }
            String bankCode = StorageCustomerInfo02Util.getInfo("bankCode", context);
            Utils.initBankCodeColorIcon(bankCode, bankIv, context);
            bankTv.setText(StorageCustomerInfo02Util.getInfo("bankDetail", context));
        }
    }


    @OnClick({R.id.iv_back, R.id.ll_pay, R.id.calculator_seven_menu, R.id.calculator_four_menu, R.id.calculator_eight_menu, R.id.calculator_five_menu,
            R.id.calculator_nine_menu, R.id.calculator_six_menu,
            R.id.calculator_one_menu, R.id.calculator_zero_menu, R.id.calculator_two_menu,
            R.id.calculator_point_menu, R.id.calculator_three_menu,R.id.calculator_twozero_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
//            case R.id.calculator_csign_menu:
//                if (calculator_num.length() < 1)
//                    return;
//                if (calculator_num.charAt(calculator_num.length() - 1) == '.') {
//                    calculator_num.deleteCharAt(calculator_num.length() - 1);
//                    calculator_num.deleteCharAt(calculator_num.length() - 1);
//                } else {
//                    calculator_num.deleteCharAt(calculator_num.length() - 1);
//                }
//                String csign_menu = CommonUtils.format00(calculator_num.toString());
//                tvMoney.setText(csign_menu);
//                break;
            case R.id.ll_pay:
                // : 2019/4/2 收款
                if (CommonUtils.isFastDoubleClick2()) {
                    return;
                }
                final String moneyVal = tvMoney.getText().toString().replace(",", "");
                if (StringUtil.isEmpty(moneyVal) || "0.00".equals(moneyVal)) {
                    ViewUtils.makeToast(context, "金额不能为空",
                            500);
                    return;
                }
                String isAuth = StorageCustomerInfo02Util.getInfo("isAuth", context);
                if (isAuth.equals("0")) {
                    ViewUtils.makeToast(context, "暂无信用卡，请先去信用卡认证添加信用卡", 1000);
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("money", moneyVal);
                intent.setClass(context, BankCardListActivity.class);
                intent.putExtra("is2Pay", true);
                intent.putExtra("title", "快捷收款");
                startActivity(intent);
                break;
            case R.id.calculator_seven_menu:
                addNum("7");
                break;
            case R.id.calculator_four_menu:
                addNum("4");
                break;
            case R.id.calculator_eight_menu:
                addNum("8");
                break;
            case R.id.calculator_five_menu:
                addNum("5");
                break;
            case R.id.calculator_nine_menu:
                addNum("9");
                break;
            case R.id.calculator_six_menu:
                addNum("6");
                break;
            case R.id.calculator_one_menu:
                addNum("1");
                break;
            case R.id.calculator_zero_menu:
                if (limitNumberLength(calculator_num.length())) {
                    return;
                }
                calculator_num.append("0");
                if (calculator_num.toString().equals("0.00")) {
                    calculator_num = new StringBuffer();
                    calculator_num.append("0.0");
                    return;
                }
                if (calculator_num.toString().equals("00")) {
                    calculator_num = new StringBuffer();
                    calculator_num.append("0");
                    return;
                }
                if ("0".equals(calculator_num.toString())) {
                    calculator_num.deleteCharAt(0);
                }
                String strZero = CommonUtils.format(calculator_num.toString());
                tvMoney.setText(strZero);
                break;
            case R.id.calculator_two_menu:
                // : 2019/4/2 按键2
                addNum("2");
                break;
            case R.id.calculator_point_menu:
                if (calculator_num.length() <= 0 || (calculator_num.toString().contains("."))) {
                    return;
                }
                calculator_num.append(".");
                String strPoint = CommonUtils.format(calculator_num.toString());
                tvMoney.setText(strPoint);
                break;
            case R.id.calculator_three_menu:
                addNum("3");
                break;
            case R.id.calculator_twozero_menu:
                if (calculator_num.length() < 1) {
                    return;
                }
                if (calculator_num.charAt(calculator_num.length() - 1) == '.') {
                    calculator_num.deleteCharAt(calculator_num.length() - 1);
                    calculator_num.deleteCharAt(calculator_num.length() - 1);
                } else {
                    calculator_num.deleteCharAt(calculator_num.length() - 1);
                }
                String csign_menu = CommonUtils.format00(calculator_num.toString());
                tvMoney.setText(csign_menu);
                break;
        }
    }

    private void addNum(String num) {
        if (limitNumberLength(calculator_num.length())) {
            return;
        }
        calculator_num.append(num);

        String str2 = CommonUtils.format(calculator_num.toString());
        tvMoney.setText(str2);
    }

    /**
     * 限制金额为100万以下
     *
     * @param length
     * @return
     */
    private boolean limitNumberLength(int length) {
        String temp = new String(calculator_num);
        if (temp.contains(".")) {
            if (length >= 9) {
                return true;
            } else {
                return false;
            }
        } else {
            if (length >= 6) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initMoneyShow();
        updateAccount();
    }

    public void initMoneyShow() {
        calculator_num = new StringBuffer();
        tvMoney.setText(R.string.defaultnum);
    }

}
