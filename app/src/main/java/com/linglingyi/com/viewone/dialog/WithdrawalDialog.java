package com.linglingyi.com.viewone.dialog;

import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/5/15 10:36
 * @功能
 **/
public class WithdrawalDialog extends DialogFragment {

    @BindView(R.id.tv_withdraw_tip)
    TextView tvBody;

    private String body, money, poundage;
    private OnButtonClickListener onButtonClickListener;

    public static WithdrawalDialog getInstance(String body, String money, String poundage) {
        WithdrawalDialog dialog = new WithdrawalDialog();
        Bundle bundle = new Bundle();
        bundle.putString("body", body);
        bundle.putString("money", money);
        bundle.putString("poundage", poundage);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyProgressDialog);
        body = getArguments().getString("body");
        money = getArguments().getString("money");
        poundage = getArguments().getString("poundage");
    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        params.height = getResources().getDimensionPixelSize(R.dimen.dp_250);
        getDialog().getWindow().setAttributes(params);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_withdraw, container);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        double fee = Double.parseDouble(money) * 0.06 + 1;
        String content = "<html><font color=\"#000000\">您将提现" +
                "</font><font color=\"#FF0000\">" + money + "</font><font color=\"#000000\">元, </font>" +
                "<font color=\"#000000\">收取每笔提现手续费</font></font><font color=\"#FF0000\">" + fee + "</font>" +
                "<font color=\"#000000\">元</font></html>";
        tvBody.setText(fromHtml(content));

    }

    @SuppressWarnings("deprecation")
    public Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    @OnClick({R.id.tv_confirm, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                dismiss();
                if (onButtonClickListener != null)
                    onButtonClickListener.onConfirmClick();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }


    public interface OnButtonClickListener {
        void onConfirmClick();
    }
}
