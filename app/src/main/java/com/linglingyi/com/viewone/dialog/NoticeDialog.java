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
import android.widget.Button;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.NoticeDetailActivity;
import com.linglingyi.com.model.NoticeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/27 10:47
 * @功能 公告
 **/
public class NoticeDialog extends DialogFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_body)
    TextView tvBody;
    @BindView(R.id.tv_title_1)
    TextView tvTitle1;
    @BindView(R.id.bt_cancel)
    Button btCancel;
    @BindView(R.id.bt_details)
    Button btDetails;

    private NoticeModel model;

    public static NoticeDialog getIntence(NoticeModel model) {
        NoticeDialog dialog = new NoticeDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", model);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyProgressDialog);
        model = (NoticeModel) getArguments().getSerializable("model");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_notice, container);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        tvTitle1.setText(model.getTitle());
        tvBody.setText(model.getContent());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @OnClick({R.id.bt_cancel, R.id.bt_details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancel:
                dismiss();
                break;
            case R.id.bt_details:
                Intent intent = new Intent(getContext(), NoticeDetailActivity.class);
                intent.putExtra("detail", model);
                startActivity(intent);
                dismiss();
                break;
        }
    }


}
