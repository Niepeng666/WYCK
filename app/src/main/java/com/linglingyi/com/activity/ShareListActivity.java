package com.linglingyi.com.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.permissions.Permission;
import com.linglingyi.com.utils.PermissionsUtils;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.adapter.PicAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.FileUtils;
import com.linglingyi.com.utils.GlideUtils;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.SharePopupWindow;
import com.linglingyi.com.viewone.viewpager_layoutmanager.CenterSnapHelper;
import com.linglingyi.com.viewone.viewpager_layoutmanager.ScaleLayoutManager;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
 * @date: 2019/8/15
 */
public class ShareListActivity extends BaseActivity implements PlatformActionListener {


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
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_container)
    RelativeLayout llContainer;
    private ScaleLayoutManager scaleLayoutManager;
    private PicAdapter mAdapter;
    private List<String> list = new ArrayList<>();
    private SharePopupWindow mSharePopupWindow;
    private String shareUrl;
    private Bitmap qrBit;

    @Override
    public void onResume() {
        super.onResume();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (mSharePopupWindow != null && mSharePopupWindow.isShowing()) {
            mSharePopupWindow.dismiss();
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            case R.id.tv_right:
                if (CommonUtils.isFastDoubleClick2()) {
                    return;
                }
                showPopupWindow();
                break;
        }
    }

    /**
     * 图文分享或者，url分享
     */
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
                        /**判断是否具有某些权限**/
                        String [] permission={Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_EXTERNAL_STORAGE};
                        if(PermissionsUtils.isHasPermissionall(context, permission)){

                            Platform.ShareParams sp4 = new Platform.ShareParams();
                            sp4.setImagePath(FileUtils.getAppExternalFilePath(context, "share") + File.separator + "share.jpg");
                            sp4.setTitleUrl(FileUtils.getAppExternalFilePath(context, "share") + File.separator + "share.jpg");
                            Platform qzone = ShareSDK.getPlatform(QZone.NAME);
                            qzone.setPlatformActionListener(ShareListActivity.this);
                            // 执行图文分享
                            qzone.share(sp4);
                        }else {
                            ViewUtils.makeToast(context,"请开启权限",1000);
                            PermissionsUtils.gotoPermissionSettings(context);//跳转授权界面授权
                        }
                        break;
                    case R.id.tv_qq:
                        String [] permission1={Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_EXTERNAL_STORAGE};
                        if(PermissionsUtils.isHasPermissionall(context, permission1)){
                            Platform.ShareParams sp3 = new Platform.ShareParams();
                            sp3.setImagePath(FileUtils.getAppExternalFilePath(context, "share") + File.separator + "share.jpg");
                            //3、非常重要：获取平台对象
                            Platform qq = ShareSDK.getPlatform(QQ.NAME);
                            qq.setPlatformActionListener(ShareListActivity.this);
                            // 执行分享
                            qq.share(sp3);
                        }else {
                            ViewUtils.makeToast(context,"请开启权限",1000);
                            PermissionsUtils.gotoPermissionSettings(context);//跳转授权界面授权
                        }


                        break;
                    case R.id.tv_wechat_friend:
                        String [] permission2={Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_EXTERNAL_STORAGE};
                        if(PermissionsUtils.isHasPermissionall(context, permission2)){
                            Platform.ShareParams sp2 = new Platform.ShareParams();
                            sp2.setShareType(Platform.SHARE_IMAGE);
                            sp2.setImagePath(FileUtils.getAppExternalFilePath(context, "share") + File.separator + "share.jpg");
                            //3、非常重要：获取平台对象
                            Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
                            wechatMoments.setPlatformActionListener(ShareListActivity.this);
                            // 执行分享
                            wechatMoments.share(sp2);
                        }else {
                            ViewUtils.makeToast(context,"请开启权限",1000);
                            PermissionsUtils.gotoPermissionSettings(context);//跳转授权界面授权
                        }
                        break;
                    case R.id.tv_wechat:
                        String [] permission3={Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_EXTERNAL_STORAGE};
                        if(PermissionsUtils.isHasPermissionall(context, permission3)){
                            Platform.ShareParams sp1 = new Platform.ShareParams();

                            sp1.setShareType(Platform.SHARE_IMAGE);
                            sp1.setImagePath(FileUtils.getAppExternalFilePath(context, "share") + File.separator + "share.jpg");
                            Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                            wechat.setPlatformActionListener(ShareListActivity.this);
                            // 执行分享
                            wechat.share(sp1);
                        }else {
                            ViewUtils.makeToast(context,"请开启权限",1000);
                            PermissionsUtils.gotoPermissionSettings(context);//跳转授权界面授权
                        }

                        break;
                    default:
                        mSharePopupWindow.dismiss();
                        break;
                }

            }
        });

        //显示窗口
        mSharePopupWindow.showAtLocation(context.findViewById(R.id.ll_container), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        loadingDialog.dismiss();
        ViewUtils.makeToast(context, "分享成功", 500);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        loadingDialog.dismiss();
        ViewUtils.makeToast(context, "分享失败", 500);
    }

    @Override
    public void onCancel(Platform platform, int i) {
        loadingDialog.dismiss();
        ViewUtils.makeToast(context, "分享取消", 500);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_share;
    }

    @Override
    public void initData() {
        tvTitle.setText("推广");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("分享");
        String phone = StorageCustomerInfo02Util.getInfo("phone", context);
        shareUrl = Constant.BASE_URL + "/lly-posp-proxy/toAPPRegister.app?phone=" + phone + "&product=" + Constant.product_name;
        //  shareUrl ="http://futengyijia.llyzf.cn/WYCK_down.html";
        qrBit = EncodingUtils.createQRCode(shareUrl, 200, 200, null);

        scaleLayoutManager = new ScaleLayoutManager.Builder(context, CommonUtils.dp2px(context, 10))
                .setMinScale(1.f)
                .setMaxVisibleItemCount(3)
                .build();
        recyclerView.setAdapter(mAdapter = new PicAdapter(list));
        recyclerView.setLayoutManager(scaleLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mAdapter.
                            setCheckedPosi(scaleLayoutManager.getCurrentPosition());
//                    GlideUtils.loadImage(context, mAdapter.getData().get(scaleLayoutManager.getCurrentPosition()), icon);
//                    GlideUtils.loadImage(context, mAdapter.getData().get(scaleLayoutManager.getCurrentPosition()), ivShareBg);
                    showPicLoad(mAdapter.getData().get(scaleLayoutManager.getCurrentPosition()));

                }
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.img) {
                    String checkedImg = (String) adapter.getItem(position);
                    GlideUtils.loadImage(context, checkedImg, icon);
                    recyclerView.smoothScrollToPosition(position);
                    mAdapter.setCheckedPosi(position);
                }
            }
        });
        loadPicData();
    }

    /**
     * 显示图片进度条
     *
     * @param url
     */
    private void showPicLoad(String url) {
        tvRight.setClickable(false);
        Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                tvRight.setClickable(true);
                icon.setImageBitmap(resource);
                ivShareBg.setImageBitmap(resource);

                ivQrCode.setImageBitmap(qrBit);
                qrCodeIv.setImageBitmap(qrBit);
            }
        });
    }

    private void loadPicData() {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "390006");
        httpParams.put("42", getMerNo());
        httpParams.put("43", "10E");
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
                    mAdapter.setNewData(list);
                    scaleLayoutManager.setInfinite(true);
                    new CenterSnapHelper().attachToRecyclerView(recyclerView);
                    if (list != null && list.size() > 0) {
                        showPicLoad(list.get(0));
                    }

                }
            }
        });
    }


}
