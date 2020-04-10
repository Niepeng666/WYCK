package com.linglingyi.com.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.NoticeModel;
import com.linglingyi.com.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/9
 */
public class NoticeDetailActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    private NoticeModel mNoticeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.notice_detail;
    }

    @Override
    public void initData() {
        mNoticeModel = (NoticeModel) getIntent().getSerializableExtra("detail");
        if (mNoticeModel == null) {
            return;
        }
        tvTitle.setText("公告详情");
        tvName.setText(mNoticeModel.getTitle());
        tvContent.setText(mNoticeModel.getContent());
        tvTime.setText(mNoticeModel.getEffectiveFromTimeStr());
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
