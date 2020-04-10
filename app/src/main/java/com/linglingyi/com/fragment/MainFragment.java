package com.linglingyi.com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.BankCardListActivity;
import com.linglingyi.com.activity.BusinessClassActivity;
import com.linglingyi.com.activity.CardHonorActivity;
import com.linglingyi.com.activity.CardScoreActivity;
import com.linglingyi.com.activity.FriendListActivity;
import com.linglingyi.com.activity.HomeNewActivity;
import com.linglingyi.com.activity.ImageActivity;
import com.linglingyi.com.activity.NoticeListActivity;
import com.linglingyi.com.activity.RecordListActivity;
import com.linglingyi.com.activity.ServiceCenterActivity;
import com.linglingyi.com.activity.ShopMallActivity;
import com.linglingyi.com.activity.SignActivity;
import com.linglingyi.com.activity.SwipeCardActivity;
import com.linglingyi.com.activity.X5WebViewActivity;
import com.linglingyi.com.base.BaseFragment;
import com.linglingyi.com.event.NoticeEvent;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.ImageScrollModel;
import com.linglingyi.com.model.NoticeModel;
import com.linglingyi.com.model.UrlModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.GlideImageLoader;
import com.linglingyi.com.viewone.dialog.NoticeDialog;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/21 14:22
 * @功能 首页
 **/
public class MainFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.tv_swipe)
    TextView tvSwipe;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_honor)
    TextView tvHonor;
    @BindView(R.id.tv_shop)
    TextView tvShop;
    @BindView(R.id.tv_security)
    TextView tvSecurity;
    @BindView(R.id.tv_credit)
    TextView tvCredit;
    @BindView(R.id.tv_loan)
    TextView tvLoan;
    @BindView(R.id.tv_online_service)
    TextView tvOnlineService;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.tv_notice_date)
    TextView tvNoticeDate;
    @BindView(R.id.ll_notice)
    LinearLayout llNotice;
    @BindView(R.id.iv_lingzhu)
    ImageView ivLingzhu;
    @BindView(R.id.iv_record)
    ImageView ivRecord;
    @BindView(R.id.iv_video)
    ImageView ivVideo;
    @BindView(R.id.iv_friend)
    ImageView ivFriend;
    @BindView(R.id.iv_zhongjie)
    ImageView ivZhongjie;
    @BindView(R.id.iv_business)
    ImageView ivBusiness;
    @BindView(R.id.tv_zhongjie)
    TextView tvZhongjie;


    private List<String> imageViews = new ArrayList<>();
    private List<ImageScrollModel> list = new ArrayList<>();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initData(View v) {
        tvTitle.setText("无忧创客");
        ivBack.setVisibility(View.GONE);
        tvRight.setText("签到");
        tvRight.setVisibility(View.VISIBLE);
        EventBus.getDefault().register(this);
        loadImageData();
        loadNotice(true);
        initListener();

    }


    /**
     * 动态获取首页图片
     */
    private void loadImageData() {
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "190108");
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    if (StringUtil.isEmpty(model.getStr57())) {
                        return;
                    }
                    list = JSONArray.parseArray(model.getStr57(), ImageScrollModel.class);
                    for (ImageScrollModel imageModel : list) {
                        imageViews.add(imageModel.getSingleNo());
                    }
                    banner.setImageLoader(new GlideImageLoader());
                    banner.setImages(imageViews);
                    banner.setDelayTime(4000);
                    banner.start();
                }
            }
        });
    }

    private void initListener() {
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

                if (!StringUtil.isEmpty(list.get(position).getOrderPaymentId().trim()) && list.get(position).getOrderPaymentId().startsWith("http")) {
                    goWebView(list.get(position).getOrderPaymentId(), "详情");
                }
            }
        });
    }

    /**
     * 跳转至h5链接
     *
     * @param
     */
    private void goWebView(String url, String title) {

        Intent webIntent = new Intent(context, X5WebViewActivity.class);
        webIntent.putExtra("title", title);
        webIntent.putExtra("url", url);
        startActivity(webIntent);
    }

    /**
     * 获取通知
     */
    private void loadNotice(final boolean isShowDialog) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "190103");
        httpParams.put("42", getMerNo());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    if (StringUtil.isEmpty(model.getStr60())) {
                        return;
                    }
                    List<NoticeModel> list = JSONArray.parseArray(model.getStr60(), NoticeModel.class);
                    if (list != null && list.size() > 0) {
                        NoticeModel model1 = list.get(0);
                        tvNotice.setText(model1.getTitle());
                        tvNoticeDate.setText(DateUtil.formatDateToHM(model1.getEffectiveFromTime().getTime()));
//                        for (NoticeModel noticeModel : list) {
//                            ivNotice.setImageResource(noticeModel.getHasRead() == 0 ? R.drawable.main_notice_2 : R.drawable.main_notice);
//                            break;
//                        }
                        if (isShowDialog) {
                            NoticeDialog noticeDialog = NoticeDialog.getIntence(model1);
                            noticeDialog.show(getChildFragmentManager(), "");
                        }
                    }
                }
            }
        });
    }

    @Subscribe
    public void onEvent(NoticeEvent noticeEvent) {
        loadNotice(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (banner != null) {
                banner.startAutoPlay();
            }
            loadNotice(false);
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
////        ivNotice.setImageResource(R.drawable.main_notice);
////        loadNotice();
//    }

    @Override
    public void onPause() {
        super.onPause();
        if (banner != null) {
            banner.stopAutoPlay();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_notice, R.id.tv_right, R.id.tv_card, R.id.tv_swipe, R.id.tv_shop, R.id.tv_credit,
            R.id.tv_loan, R.id.tv_security, R.id.tv_score, R.id.tv_honor, R.id.iv_lingzhu,
            R.id.tv_online_service, R.id.iv_friend, R.id.iv_zhongjie, R.id.iv_business, R.id.iv_video, R.id.iv_record, R.id.tv_zhongjie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_notice:
                startActivity(new Intent(context, NoticeListActivity.class));
                break;
            case R.id.tv_right:
                if (StorageCustomerInfo02Util.getIntInfo(context, "qd", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                // TODO: 2019/10/9 签到 页面
                startActivity(new Intent(context, SignActivity.class));
                break;
            case R.id.tv_card:
                if (CommonUtils.isFastDoubleClick2()) {
                    return;
                }

                Intent intent = new Intent();
                intent.setClass(context, BankCardListActivity.class);
                intent.putExtra("title", "智能还款");
                startActivity(intent);
                break;
            case R.id.tv_swipe:
                startActivity(new Intent(context, SwipeCardActivity.class));
                break;
            case R.id.tv_shop:
                if (StorageCustomerInfo02Util.getIntInfo(context, "sc", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                if (CommonUtils.isFastDoubleClick2()) {
                    return;
                }
                startActivity(new Intent(context, ShopMallActivity.class));
                // TODO: 2019/10/9 在线商城
                break;
            case R.id.tv_credit:
                if (StorageCustomerInfo02Util.getIntInfo(context, "bk", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                loadUrlData("2", "金融服务");
                break;
            case R.id.tv_loan:
                if (StorageCustomerInfo02Util.getIntInfo(context, "jf", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                loadUrlData("3", "积分兑换");
                break;
            case R.id.tv_security:
                // TODO: 2019/9/2 保险
                if (StorageCustomerInfo02Util.getIntInfo(context, "bx", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                loadUrlData("4", "保险业务");
                break;
            case R.id.tv_score:     //卡测评
//                if (StorageCustomerInfo02Util.getIntInfo(context, "kcp", -1) == 0) {
//                    ViewUtils.makeToast(context, "暂未开放", 500);
//                    return;
//                }
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                if (!checkBindCard()) {
                    return;
                }
                startActivity(new Intent(context, CardScoreActivity.class));
                break;
            case R.id.tv_honor:
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                if (!checkBindCard()) {
                    return;
                }
                startActivity(new Intent(context, CardHonorActivity.class));
                break;
            case R.id.iv_lingzhu:

                ((HomeNewActivity) getActivity()).goLingzhu();
//                if (!checkCustomerInfoCompleteShowToast()) {
//                    return;
//                }
                // TODO: 2019/10/9 领主权益
//                startActivity(new Intent(context, LordRightsActivity.class));
                break;
            case R.id.tv_online_service:    //在线客服
                startActivity(new Intent(context, ServiceCenterActivity.class));
                break;
            case R.id.iv_friend:
                if (StorageCustomerInfo02Util.getIntInfo(context, "kyq", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                startActivity(new Intent(context, FriendListActivity.class));
                break;
            case R.id.tv_zhongjie:
                loadImage();
                break;
            case R.id.iv_business:
                if (StorageCustomerInfo02Util.getIntInfo(context, "sxy", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                startActivity(new Intent(context, BusinessClassActivity.class));
                break;
            case R.id.iv_video:
                if (StorageCustomerInfo02Util.getIntInfo(context, "zb", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                ViewUtils.makeToast(context, "暂未开放", 500);
                break;
            case R.id.iv_record:
                if (StorageCustomerInfo02Util.getIntInfo(context, "lhb", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                startActivity(new Intent(context, RecordListActivity.class));
                break;

            default:
                break;
        }
    }

    /**
     * 获取链接
     */
    private void loadUrlData(final String type, final String title) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "690034");
        httpParams.put("41", getMerId());
//        httpParams.put("43", type);//1 贷款 2 办卡
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
                    UrlModel urlModel = JSONObject.parseObject(model.getStr42(), UrlModel.class);
                    if ("2".equals(type)) {
                        goWebView(urlModel.getBK(), title);
                    } else if ("1".equals(type)) {
                        goWebView(urlModel.getDK(), title);
                    } else if ("3".equals(type)) {
                        goWebView(urlModel.getJF(), title);
                    } else if ("4".equals(type)) {
                        goWebView(urlModel.getDS(), title);
                    }
                }
            }
        });
    }

    /**
     * 获取中介代还图片
     */
    private void loadImage() {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "390006");
        httpParams.put("42", getMerNo());
        httpParams.put("43", "10T");
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onSuccess(Response<BaseEntity> response) {
                super.onSuccess(response);
                loadingDialog.dismiss();

                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    List<String> json = JSONArray.parseArray(model.getStr57(), String.class);
                    //升级费率图片
                    if (json.size() > 0) {
                        Intent intent = new Intent(context, ImageActivity.class);
                        intent.putExtra("title", "中介代还");
                        intent.putExtra("url", json.get(0));
                        startActivity(intent);
                    }


                }
            }

            @Override
            public void onError(Response<BaseEntity> response) {
                super.onError(response);
                loadingDialog.dismiss();
            }
        });
    }

}
