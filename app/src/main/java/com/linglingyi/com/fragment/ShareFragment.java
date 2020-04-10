package com.linglingyi.com.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.FriendListActivity;
import com.linglingyi.com.activity.RegisterFaceActivity;
import com.linglingyi.com.activity.ShareActivity;
import com.linglingyi.com.activity.ShareListActivity;
import com.linglingyi.com.activity.VipActivity;
import com.linglingyi.com.adapter.PicAdapter;
import com.linglingyi.com.base.BaseFragment;
import com.linglingyi.com.callback.CancelCallback;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.FileUtils;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.SharePopupWindow;
import com.linglingyi.com.viewone.dialog.BindParentPhoneDialog;
import com.linglingyi.com.viewone.viewpager_layoutmanager.CenterSnapHelper;
import com.linglingyi.com.viewone.viewpager_layoutmanager.ScaleLayoutManager;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import zxing.encoding.EncodingUtils;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/21 14:24
 * @功能      分享
 **/
public class ShareFragment extends BaseFragment implements PlatformActionListener {


    Unbinder unbinder;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.ll_register)
    LinearLayout llRegister;
    @BindView(R.id.ll_qr_code)
    LinearLayout llQrCode;
    @BindView(R.id.ll_vip)
    LinearLayout llVip;
    @BindView(R.id.ll_share_friend)
    LinearLayout llShareFriend;
    @BindView(R.id.ll_share_wechat)
    LinearLayout llShareWechat;
    @BindView(R.id.ll_share_qq)
    LinearLayout llShareQq;
    @BindView(R.id.ll_share_kongjian)
    LinearLayout llShareKongjian;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(context, "微博分享成功", Toast.LENGTH_LONG).show();
                    break;

                case 2:
                    Toast.makeText(context, "微信分享成功", Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(context, "朋友圈分享成功", Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText(context, "QQ分享成功", Toast.LENGTH_LONG).show();
                    break;

                case 5:
                    Toast.makeText(context, "取消分享", Toast.LENGTH_LONG).show();
                    break;
                case 6:
                    Toast.makeText(context, "分享失败", Toast.LENGTH_LONG).show();
                    break;
                case 7:
                    Toast.makeText(context, "QQ空间分享成功", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }

    };

    private String imgPath = Constant.SHARE_LOGO;
    private String shareUrl;
    private String parentPhone;

    public static ShareFragment newInstance() {
        return new ShareFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public int initLayout() {
        return R.layout.frag_share;
    }

    @Override
    public void initData(View v) {
        ShareSDK.initSDK(context);
        ivBack.setVisibility(View.INVISIBLE);
        tvTitle.setText("分享");
        String phone = StorageCustomerInfo02Util.getInfo("phoneNum", context);
        shareUrl = Constant.BASE_URL + "/lly-posp-proxy/toAPPRegister.app?phone=" + phone + "&product=WYCK";
        //   shareUrl = "http://futengyijia.llyzf.cn/WYCK_down.html";

    }

    private void checkParentPhone() {
        parentPhone = StorageCustomerInfo02Util.getInfo("parentPhone", context);
        if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
            loadParentDialog();
        }
    }

    /**
     * 显示绑定推荐人弹框
     */
    private void loadParentDialog() {
        final BindParentPhoneDialog parentPhoneDialog = BindParentPhoneDialog.getInstance();
        parentPhoneDialog.show(getChildFragmentManager(), "parent");
        parentPhoneDialog.setCancelCallback(new CancelCallback() {
            @Override
            public void cancel() {
                parentPhoneDialog.dismiss();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            checkParentPhone();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        checkParentPhone();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.ll_register, R.id.ll_qr_code, R.id.ll_vip, R.id.ll_share_wechat, R.id.ll_share_friend, R.id.ll_share_qq, R.id.ll_share_kongjian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_register:
                // TODO: 2018/10/26 面对面注册
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
                    loadParentDialog();
                    return;
                }
                startActivity(new Intent(context, RegisterFaceActivity.class));
                break;
            case R.id.ll_qr_code:
                // TODO: 2018/10/26 二维码注册
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
                    loadParentDialog();
                    return;
                }

                Intent intent = new Intent();
                intent.setClass(context, ShareListActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_vip:
                // TODO: 2018/10/26 推荐人升级
                if (StorageCustomerInfo02Util.getIntInfo(context, "kyq", -1) == 0) {
                    ViewUtils.makeToast(context, "暂未开放", 500);
                    return;
                }
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }

                startActivity(new Intent(context, FriendListActivity.class));
                break;

            case R.id.ll_share_wechat:
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
                    loadParentDialog();
                    return;
                }
                // TODO: 2018/10/26 微信分享
                Platform.ShareParams sp1 = new Platform.ShareParams();
                sp1.setShareType(Platform.SHARE_WEBPAGE);//非常重要：一定要设置分享属性
                sp1.setTitle("无忧创客，收款利器，赚钱神器！更低费率，更高返佣");  //分享标题
                sp1.setText("邀请您一起使用无忧创客！365天！24小时秒到！超低费率！超便捷的微信支付宝收款方式！刷卡代还好帮手，美化账单还有谁?");   //分享文本
                sp1.setImageUrl(imgPath);//网络图片rul
                sp1.setUrl(shareUrl);   //网友点进链接后，可以看到分享的详情
                //3、非常重要：获取平台对象
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                wechat.setPlatformActionListener(this); // 设置分享事件回调
                // 执行分享
                wechat.share(sp1);
                break;
            case R.id.ll_share_friend:
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
                    loadParentDialog();
                    return;
                }
                // TODO: 2018/10/26  朋友圈分享
                Platform.ShareParams sp2 = new Platform.ShareParams();
                sp2.setShareType(Platform.SHARE_WEBPAGE); //非常重要：一定要设置分享属性
                sp2.setTitle("无忧创客，收款利器，赚钱神器！更低费率，更高返佣");  //分享标题
                sp2.setText("邀请您一起使用无忧创客！365天！24小时秒到！超低费率！超便捷的微信支付宝收款方式！刷卡代还好帮手，美化账单还有谁?");   //分享文本
                sp2.setImageUrl(imgPath);//网络图片rul
                sp2.setUrl(shareUrl);   //网友点进链接后，可以看到分享的详情
                //3、非常重要：获取平台对象
                Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
                wechatMoments.setPlatformActionListener(this); // 设置分享事件回调
                // 执行分享
                wechatMoments.share(sp2);
                break;
            case R.id.ll_share_qq:
                    if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                    if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
                        loadParentDialog();
                        return;
                    }
                // TODO: 2018/10/26  qq分享
                Platform.ShareParams sp3 = new Platform.ShareParams();
                sp3.setTitle("无忧创客，收款利器，赚钱神器！更低费率，更高返佣");  //分享标题
                sp3.setText("邀请您一起使用无忧创客！365天！24小时秒到！超低费率！超便捷的微信支付宝收款方式！刷卡代还好帮手，美化账单还有谁?");   //分享文本
                sp3.setImageUrl(imgPath);//网络图片rul
                sp3.setTitleUrl(shareUrl);  //网友点进链接后，可以看到分享的详情
                //3、非常重要：获取平台对象
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(this); // 设置分享事件回调
                // 执行分享
                qq.share(sp3);
                break;
            case R.id.ll_share_kongjian:
                if (!checkCustomerInfoCompleteShowToast()) {
                    return;
                }
                if (StringUtil.isEmpty(parentPhone) || "123".equals(parentPhone)) {
                    loadParentDialog();
                    return;
                }
                // TODO: 2018/10/26
                Platform.ShareParams sp4 = new Platform.ShareParams();
                sp4.setTitle("无忧创客，收款利器，赚钱神器！更低费率，更高返佣");  //分享标题
                sp4.setTitleUrl(shareUrl); // 标题的超链接
                sp4.setText("邀请您一起使用无忧创客！365天！24小时秒到！超低费率！超便捷的微信支付宝收款方式！刷卡代还好帮手，美化账单还有谁?");   //分享文本
                sp4.setImageUrl(imgPath);
                sp4.setSite("无忧创客");
                sp4.setSiteUrl(shareUrl);
                Platform qzone = ShareSDK.getPlatform(QZone.NAME);
                qzone.setPlatformActionListener(this); // 设置分享事件回调
                // 执行图文分享
                qzone.share(sp4);
                break;
            default:
                break;
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (platform.getName().equals(Wechat.NAME)) {
            handler.sendEmptyMessage(2);
        } else if (platform.getName().equals(WechatMoments.NAME)) {
            handler.sendEmptyMessage(3);
        } else if (platform.getName().equals(QQ.NAME)) {
            handler.sendEmptyMessage(4);
        } else if (platform.getName().equals(QZone.NAME)) {
            handler.sendEmptyMessage(7);

        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        LogUtils.e("platform=" + platform + "error=" + throwable.getMessage());
        throwable.printStackTrace();
        Message msg = new Message();
        msg.what = 6;
        msg.obj = throwable.getMessage();
        handler.sendMessage(msg);
    }

    @Override
    public void onCancel(Platform platform, int i) {
        handler.sendEmptyMessage(5);
    }
}
