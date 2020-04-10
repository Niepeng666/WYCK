package com.linglingyi.com.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.ChooseCardAccountAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.ChannelBean;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/15
 */
public class ChannelReportActivity extends BaseActivity {
    private static final int REQUSRT_CHANNEL = 0x01;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    /**
     * 信用卡信息
     */
    private BindCard mBindCard;
    private ChooseCardAccountAdapter mChooseCardAccountAdapter;
    private List<ChannelBean.Channel> mChannelList = new ArrayList<>();
    private boolean fromCards;
    private int selPosition = -1;
    private ChannelBean.Channel mSelChannel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_recycler_view;
    }

    @Override
    public void initData() {
        tvTitle.setText("通道报备");
        mBindCard = (BindCard) getIntent().getSerializableExtra("bindCard");

        fromCards = getIntent().getBooleanExtra("fromCards", false);

        mChooseCardAccountAdapter = new ChooseCardAccountAdapter(mChannelList);
        rvList.setLayoutManager(new LinearLayoutManager(context));

        mChooseCardAccountAdapter.bindToRecyclerView(rvList);
        if (fromCards) {
            mChooseCardAccountAdapter.addFooterView(getFooterView());
        }
        loadChannelData();
        initListener();
    }

    private void initListener() {
        mChooseCardAccountAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.chb_select:
                        if (selPosition != position) {
                            selPosition = position;
                            for (int i = 0; i < mChannelList.size(); i++) {
                                mChannelList.get(i).setCheck(false);
                            }
                            mChannelList.get(position).setCheck(true);
                            mChooseCardAccountAdapter.notifyDataSetChanged();
                            mSelChannel = mChannelList.get(position);
                        }
                        break;
                    case R.id.btn_report:
                        // TODO: 2019/10/12 去绑卡
                        Intent intent1 = new Intent(context, BindCardActivity.class);
                        intent1.putExtra("bindCard", mBindCard);
                        intent1.putExtra("type", mChannelList.get(position).getAcqcode());
                        intent1.putExtra("category", "1");
                        startActivityForResult(intent1, REQUSRT_CHANNEL);
                        break;
                    case R.id.tv_limit_des:
                        Intent intent = new Intent(context, ImageActivity.class);
                        intent.putExtra("title", mChannelList.get(position).getChannelName());
                        intent.putExtra("url", "http://120.78.81.147/icon/icon_channel_" + mChannelList.get(position).getAcqcode() + ".png");
                        startActivity(intent);
                        break;
                }
            }
        });

    }

    private View getFooterView() {

        TextView tvAdd = new TextView(context);
        tvAdd.setText("下一步");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);
        params.setMargins(10, 50, 10, 0);
        tvAdd.setGravity(Gravity.CENTER);
        int[] attrArray1 = {R.attr.theme_bg_color};
        TypedArray mTypedArray1 = context.obtainStyledAttributes(attrArray1);
        tvAdd.setBackgroundColor(mTypedArray1.getColor(0, 0xFF000000));
        tvAdd.setLayoutParams(params);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MakeDesignActivity.class);
                intent.putExtra("zhia", false);
                intent.putExtra("bindCard", mBindCard);
                intent.putExtra("channel", mSelChannel);
                startActivity(intent);

            }
        });
        return tvAdd;
    }

    /**
     * 获取全部通道
     */
    private void loadChannelData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390014");
        httpParams.put("42", getMerNo());
        httpParams.put("43", "YK");
        httpParams.put("44", mBindCard.getID());
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
                    LogUtils.i("57=" + model.getStr57());
                    ChannelBean channelBean = JSONObject.parseObject(model.getStr57(), ChannelBean.class);
                    List<ChannelBean.Channel> list = channelBean.getAcqData();
                    mChannelList.addAll(list);
                    if (mChannelList.size() > 0) {
                        mSelChannel=mChannelList.get(0);
                        mSelChannel.setCheck(true);
                    } else {
                        mChooseCardAccountAdapter.setEmptyView(R.layout.layout_empty, rvList);
                    }
                    mChooseCardAccountAdapter.setNewData(mChannelList);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUSRT_CHANNEL&&resultCode==RESULT_OK) {
            mChannelList.clear();
            loadChannelData();
        }

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
