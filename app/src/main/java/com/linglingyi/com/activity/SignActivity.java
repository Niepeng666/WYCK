package com.linglingyi.com.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.LogUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.qiandao.SignDate;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description: 签到
 * @date: 2019/10/15
 */
public class SignActivity extends BaseActivity {
    private static final String TAG = "AttendancePolite";

    boolean isDate;
    String value;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.signDate)
    SignDate signDate;
    @BindView(R.id.btn_qiandao)
    Button btnQiandao;
    @BindView(R.id.tv_sign_money)
    TextView tvSignMoney;
    private String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_sign;
    }

    @Override
    public void initData() {
        tvTitle.setText("签到");
        postInfo(getMerNo());
    }

    private void postInfo(String str) {
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("3", "153260");
        map.put("42", str);
        loadingDialog.show();
        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity body = response.body();
                String result = body.getStr39();
                if ("00".equals(result)) {
                    String str40 = body.getStr40();
                    String[] str = str40.split("\\,");
                    for (int i = 0; i < str.length; i++) {
                        LogUtil.i(TAG, str[i]);
                    }
                    signDate.setAttendance(str);
                    money = body.getStr38();
                    tvSignMoney.setText("今日签到可获得" + body.getStr38() + "元");
                } else {
                    ViewUtils.makeToast(context, result, 500);
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.btn_qiandao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.btn_qiandao:
                post(getMerNo());
                break;
        }
    }

    private void post(String str) {
        HttpParams map = OkClient.getParamsInstance().getParams();
        map.put("3", "153261");
        map.put("42", str);
        loadingDialog.show();
        OkClient.getInstance().post(map, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                Log.i(TAG, "onError" + response.toString());
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();
                BaseEntity body = response.body();
                String result = body.getStr39();
                if ("00".equals(result)) {
                    Date curDate = new Date(System.currentTimeMillis());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                    String format = simpleDateFormat.format(curDate);

                    LogUtil.i("AdapterDate", "addAttendance--" + format + "");

                    String substring = format.substring(0, 1);
                    LogUtil.i("AdapterDate", "addAttendance--" + substring + "");
                    if ("0".equals(substring)) {
                        format = format.substring(format.length() - 1, format.length());
                    }

                    signDate.setAddAttendance(format);
//                    ViewUtils.makeToast(context, body.getStr38(), 500);
                    showSignSuccessDialog();
                } else {
                    ViewUtils.makeToast(context, result, 500);
                }
            }
        });
    }

    /**
     * 显示签到成功的弹框
     *
     * @param
     */
    private void showSignSuccessDialog() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sign_success, null);
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .show();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().setLayout(CommonUtils.dp2px(context, 220), LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        TextView tvMoney = view.findViewById(R.id.tv_money);
        tvMoney.setText("获得" + money + "元");
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}
