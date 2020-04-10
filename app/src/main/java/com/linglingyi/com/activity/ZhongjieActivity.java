package com.linglingyi.com.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ChannelBean;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/2
 */
public class ZhongjieActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_image_scroll;
    }

    @Override
    public void initData() {
        tvTitle.setText("中介代还");
        tvRight.setClickable(false);
        loadPicData();
    }

    private void loadPicData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390006");
        httpParams.put("42", getMerNo());
        httpParams.put("43", "10B");
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
                    List<String> list = JSONArray.parseArray(model.getStr57(), String.class);
                    if (list != null && list.size() > 0) {
                        GlideUtils.loadImage(context, list.get(0), ivImage);
                    }
                }
            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
