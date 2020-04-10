package com.linglingyi.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.ApplyCreditEntity;
import com.linglingyi.com.model.ApplyLoanEntity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CheckOutMessage;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/30
 */
public class ApplyCreditLoanActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_id_card)
    EditText etIdCard;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private ApplyCreditEntity mCreditEntity;
    private ApplyLoanEntity mLoanEntity;
    private String type, name, idCard, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_apply_credit_loan;
    }

    @Override
    public void initData() {
        mCreditEntity = (ApplyCreditEntity) getIntent().getSerializableExtra("credit");
        mLoanEntity = (ApplyLoanEntity) getIntent().getSerializableExtra("loan");
        if (mCreditEntity != null) {
            type = "BK";
            tvDes.setText(String.format("正在申请%s", mCreditEntity.getName()));
            tvTitle.setText(mCreditEntity.getName());
        } else {
            type = "DK";
            tvDes.setText(String.format("正在申请%s", mLoanEntity.getName()));
            tvTitle.setText(mLoanEntity.getName());
        }
    }

    @OnClick({R.id.iv_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_submit:
                name = etName.getText().toString();
                idCard = etIdCard.getText().toString();
                phone = etMobile.getText().toString();
                if (CheckOutMessage.isEmpty(context, "姓名", name)) {
                    return;
                }
                if (CheckOutMessage.isEmpty(context, "身份证号", idCard)) {
                    return;
                }
                if (CheckOutMessage.isEmpty(context, "手机号", phone)) {
                    return;
                }

                submit();

                break;
        }
    }

    private void submit() {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "150077");
        httpParams.put("10", type);
        if ("DK".equals(type)) {
            httpParams.put("11", mLoanEntity.getPr_id());
            httpParams.put("12", mLoanEntity.getName());
        } else {
            httpParams.put("11", mCreditEntity.getPr_id());
            httpParams.put("12", mCreditEntity.getName());
            httpParams.put("13", mCreditEntity.getChannelId());
        }
        httpParams.put("42", getMerNo());
        httpParams.put("43", name);
        httpParams.put("44", idCard);
        httpParams.put("45", phone);
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
                if ("00".equals(model.getStr39())) {
                    String url = model.getStr38();
                    if (!StringUtil.isEmpty(url)) {
                        Intent intent = new Intent(context, WebViewActivity.class);
                        intent.putExtra("url", url);
                        intent.putExtra("title",tvTitle.getText().toString());
                        startActivity(intent);
                        ViewUtils.overridePendingTransitionBack(context);
                    }
                }
            }
        });
    }
}
