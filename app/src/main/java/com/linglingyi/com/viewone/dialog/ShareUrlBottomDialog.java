package com.linglingyi.com.viewone.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linglingyi.com.utils.ViewUtils;
import com.wuyouchuangke.com.R;

import java.util.HashMap;

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

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/1 15:37
 * @功能 贷款 操作流程
 **/
public class ShareUrlBottomDialog extends DialogFragment {


    public Dialog loadingDialog;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.tv_wechat_friend)
    TextView tvWechatFriend;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_qq_space)
    TextView tvQqSpace;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.pop_layout)
    LinearLayout popLayout;
    Unbinder unbinder;
    private Activity mActivity;
    private String title, titleUrl, imageUrl, content;

    public static ShareUrlBottomDialog getInstance(String title, String titleUrl, String imageUrl, String content) {
        ShareUrlBottomDialog shareUrlBottomDialog = new ShareUrlBottomDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("titleUrl", titleUrl);
        bundle.putString("imageUrl", imageUrl);
        bundle.putString("content", content);
        shareUrlBottomDialog.setArguments(bundle);
        return shareUrlBottomDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyProgressDialog);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        title = bundle.getString("title");
        titleUrl = bundle.getString("titleUrl");
        imageUrl = bundle.getString("imageUrl");
        content = bundle.getString("content");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        unbinder.unbind();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_share, container);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        getDialog().setCanceledOnTouchOutside(false);
        loadingDialog = ViewUtils.createLoadingDialog(getActivity(), getString(R.string.loading_wait), false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = window.getAttributes();
        ((WindowManager.LayoutParams) layoutParams).gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes((WindowManager.LayoutParams) layoutParams);
    }


    @OnClick({R.id.tv_wechat, R.id.tv_wechat_friend, R.id.tv_qq, R.id.tv_qq_space, R.id.tv_cancel})
    public void onViewClicked(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.tv_wechat:
                loadingDialog.show();
                goShare(Wechat.NAME, "wechat");
                break;
            case R.id.tv_wechat_friend:
                loadingDialog.show();
                goShare(WechatMoments.NAME, "friend");
                break;
            case R.id.tv_qq:
                loadingDialog.show();
                goShare(QQ.NAME, "qq");
                break;
            case R.id.tv_qq_space:
                loadingDialog.show();
                goShare(QZone.NAME, "qzone");
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    private void goShare(String name, String type) {
        Platform.ShareParams sp4 = new Platform.ShareParams();

        switch (type) {
            case "wechat":
                sp4.setShareType(Platform.SHARE_WEBPAGE);
                sp4.setTitle(title);
                sp4.setImageUrl(imageUrl);
                sp4.setUrl(titleUrl);
                sp4.setText(content);
                break;
            case "friend":
                sp4.setShareType(Platform.SHARE_WEBPAGE);
                sp4.setTitle(title);
                sp4.setImageUrl(imageUrl);
                sp4.setUrl(titleUrl);
                sp4.setText(content);
                break;
            case "qq":
                sp4.setTitle(title);
                sp4.setImageUrl(imageUrl);
                sp4.setTitleUrl(titleUrl);
                sp4.setText(content);
                break;
            case "qzone":
                sp4.setTitle(title);
                sp4.setImageUrl(imageUrl);
                sp4.setTitleUrl(titleUrl);
                sp4.setText(content);
                sp4.setSiteUrl(titleUrl);
                break;
        }
        Platform qzone = ShareSDK.getPlatform(name);
        qzone.share(sp4);
        qzone.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                loadingDialog.dismiss();
                ViewUtils.makeToast(mActivity,"分享成功",500);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                loadingDialog.dismiss();
                ViewUtils.makeToast(mActivity,"分享失败",500);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                loadingDialog.dismiss();
                ViewUtils.makeToast(mActivity,"分享取消",500);
            }
        });

    }


}
