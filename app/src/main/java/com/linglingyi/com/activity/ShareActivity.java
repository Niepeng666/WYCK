package com.linglingyi.com.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.FileUtils;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.viewone.SharePopupWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import zxing.encoding.EncodingUtils;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/6/17
 */
public class ShareActivity extends BaseActivity implements PlatformActionListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_share_bg)
    ImageView ivShareBg;
    @BindView(R.id.qr_code_iv)
    ImageView qrCodeIv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl)
    ConstraintLayout rl;
    private SharePopupWindow mSharePopupWindow;
    private File file;
    private String imageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_single_pic_share;
    }

    @Override
    public void initData() {
        tvTitle.setText("推广");
        tvRight.setText("分享");
        tvRight.setVisibility(View.VISIBLE);
        imageResource = getIntent().getStringExtra("imageResource");
        String phone = StorageCustomerInfo02Util.getInfo("phone", context);
        String name = StorageCustomerInfo02Util.getInfo("merchantCnName", context);
        tvName.setText(name);
        GlideUtils.loadImage(context, imageResource, ivShareBg);
        String qr_code_content = Constant.BASE_URL + "/lly-posp-proxy/toAPPRegister.app?phone=" + phone + "&product=WYCK";
        Bitmap bitmap = EncodingUtils.createQRCode(qr_code_content, 500, 500, null);
        qrCodeIv.setImageBitmap(bitmap);
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_right:
                showPopupWindow();
                break;
        }
    }

    private void showPopupWindow() {
        mSharePopupWindow = new SharePopupWindow(context, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.show();
                try {
                    saveCurrentImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (v.getId()) {
                    case R.id.tv_qq_space:
                        Platform.ShareParams sp4 = new Platform.ShareParams();
                        sp4.setImagePath(FileUtils.getAppExternalFilePath(context, "share") + File.separator + "share.jpg");
                        Platform qzone = ShareSDK.getPlatform(QZone.NAME);
                        qzone.setPlatformActionListener(ShareActivity.this);
                        // 执行图文分享
                        qzone.share(sp4);
                        break;
                    case R.id.tv_qq:
                        Platform.ShareParams sp3 = new Platform.ShareParams();
                        sp3.setImagePath(FileUtils.getAppExternalFilePath(context, "share") + File.separator + "share.jpg");
                        //3、非常重要：获取平台对象
                        Platform qq = ShareSDK.getPlatform(QQ.NAME);
                        qq.setPlatformActionListener(ShareActivity.this);
                        // 执行分享
                        qq.share(sp3);
                        break;
                    case R.id.tv_wechat_friend:
                        Platform.ShareParams sp2 = new Platform.ShareParams();
                        sp2.setShareType(Platform.SHARE_IMAGE);
                        sp2.setImagePath(FileUtils.getAppExternalFilePath(context, "share") + File.separator + "share.jpg");
                        //3、非常重要：获取平台对象
                        Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
                        wechatMoments.setPlatformActionListener(ShareActivity.this);
                        // 执行分享
                        wechatMoments.share(sp2);
                        break;
                    case R.id.tv_wechat:
                        Platform.ShareParams sp1 = new Platform.ShareParams();
                        sp1.setShareType(Platform.SHARE_IMAGE);
                        sp1.setImagePath(FileUtils.getAppExternalFilePath(context, "share") + File.separator + "share.jpg");
                        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                        wechat.setPlatformActionListener(ShareActivity.this);
                        // 执行分享
                        wechat.share(sp1);
                        break;
                    default:
                        mSharePopupWindow.dismiss();
                        break;
                }
            }
        });

        //显示窗口
        mSharePopupWindow.showAtLocation(context.findViewById(R.id.rl), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        backgroundAlpha(0.5f);
        mSharePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().setAttributes(lp);
    }

    /**
     * 保存图片
     *
     * @throws IOException
     */
    private void saveCurrentImage() throws IOException {
        Bitmap temBitmap;
        View view = rl;
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //从缓存中获取当前屏幕的图片
        temBitmap = view.getDrawingCache();
        String destination = FileUtils.getAppExternalFilePath(context, "share") + File.separator + "share.jpg";
        try {
            FileUtils.saveFile(temBitmap, destination);
        } catch (IOException e) {
//            判断是否保存
            File file = new File(destination);
            if (!file.exists()) {
                FileUtils.saveFile(temBitmap, destination);
            }
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSharePopupWindow != null && mSharePopupWindow.isShowing()) {
            mSharePopupWindow.dismiss();
        }
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }

    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        loadingDialog.dismiss();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        loadingDialog.dismiss();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        loadingDialog.dismiss();
    }
}
