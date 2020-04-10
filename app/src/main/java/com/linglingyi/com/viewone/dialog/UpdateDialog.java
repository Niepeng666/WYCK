package com.linglingyi.com.viewone.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.activity.HomeNewActivity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.DeviceUtils;
import com.linglingyi.com.utils.DownloadUtil;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.PermissionUtil;
import com.linglingyi.com.utils.StorageCustomerInfo02Util;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okHttp.OkHttp3Util;
import com.lzy.okgo.OkGo;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/7
 */
public class UpdateDialog extends DialogFragment {

    Unbinder unbinder;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.pro_bar)
    ProgressBar proBar;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.ll_no_update)
    LinearLayout llNoUpdate;
    @BindView(R.id.ll_updateing)
    LinearLayout llUpdateing;
    @BindView(R.id.rl_update)
    RelativeLayout rlUpdate;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_browser)
    TextView tvBrowser;

    private boolean isForce;
    private Activity mActivity;
    private boolean canInstall;
    private File mFile;
    private String newVersion, downloadApkUrl;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtils.i(msg.obj + "");
            if (msg.what == 1) {
                if (proBar == null) {
                    return;
                }
                proBar.setProgress(Integer.valueOf((Integer) msg.obj));
                tvProgress.setText("正在更新..." + Integer.valueOf((Integer) msg.obj) + "%");
            } else if (msg.what == 2) {
                if (btnUpdate == null) {
                    return;
                }
                mFile = new File((String) msg.obj);
                btnUpdate.setText("安装");
                tvCancel.setVisibility(View.GONE);
                canInstall = true;
                llUpdateing.setVisibility(View.GONE);
                llNoUpdate.setVisibility(View.VISIBLE);

                installApk();
            } else if (msg.what == 0) {
                btnUpdate.setText("请重新登录下载");
            }
        }
    };

    public static UpdateDialog getInstance(boolean isForce, String newVersion) {
        UpdateDialog updateDialog = new UpdateDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isForce", isForce);
        bundle.putString("newVersion", newVersion);
        updateDialog.setArguments(bundle);
        return updateDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isForce = getArguments().getBoolean("isForce");
            newVersion = getArguments().getString("newVersion");
        }
        mActivity = getActivity();
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MyProgressDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setLayout((int) (DeviceUtils.getScreenWidth(mActivity) * 0.70), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_update, container);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        getDialog().setCanceledOnTouchOutside(false);
        tvContent.setText(newVersion);
        tvCancel.setVisibility(isForce ? View.GONE : View.VISIBLE);
        String url = StorageCustomerInfo02Util.getInfo("apkUrl", mActivity);
        downloadApkUrl = StringUtil.isEmpty(url) ? Constant.DOWNLOAD_APK : url;
        tvBrowser.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }

    @OnClick({R.id.tv_cancel, R.id.btn_update, R.id.iv_close, R.id.tv_browser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                if (!isForce) {
                    Intent intent_start = new Intent();
                    intent_start.setClass(mActivity, HomeNewActivity.class);
                    intent_start.putExtra("fromLogin", true);
                    startActivity(intent_start);
                    ViewUtils.overridePendingTransitionComeFinish(mActivity);
                }
                dismiss();
                break;
            case R.id.btn_update:
                if (canInstall && mFile != null) {
                    installApk();
                    return;
                }
                llNoUpdate.setVisibility(View.GONE);
                llUpdateing.setVisibility(View.VISIBLE);

                downFile(downloadApkUrl);
                break;
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_browser:
                DownloadUtil.openBrowser(mActivity, downloadApkUrl);
                break;

        }
    }

    /**
     * 后台在下面一个Apk 下载完成后返回下载好的文件
     *
     * @param httpUrl
     * @return
     */
    private void downFile(final String httpUrl) {
        if (CommonUtils.getConnectedType(mActivity) == -1) {
            ViewUtils.makeToast(mActivity,
                    getString(R.string.nonetwork), 500);
            return;
        }
        OkHttp3Util.download(mActivity, httpUrl, "wyck.apk", new OkHttp3Util.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                Message msg = new Message();
                msg.what = 2;
                msg.obj = String.valueOf(file.getAbsolutePath());
                mHandler.sendMessage(msg);
            }

            @Override
            public void onDownloading(int progress) {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = Integer.valueOf(progress);
                mHandler.sendMessage(msg);
            }

            @Override
            public void onDownloadFailed(Exception e) {
                Message msg = new Message();
                msg.what = 0;
                mHandler.sendMessage(msg);
            }
        });

    }

    /**
     * 安装APK
     *
     * @param
     */
    private void installApk() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri apkUri = FileProvider.getUriForFile(mActivity, "com.wuyouchuangke.com.fileprovider", mFile);
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                boolean hasInstallPermission = mActivity.getPackageManager().canRequestPackageInstalls();
//                if (!hasInstallPermission) {
//                    startInstallPermissionSettingActivity(mActivity);
//                    return;
//                }
                PermissionUtil.INSTALL_PACKAGES(mActivity);

            }

            startActivity(install);
        } else {
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(mFile), "application/vnd.android.package-archive");
            startActivity(install);
        }
    }

    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(final Context context) {
        //注意这个是8.0新API
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
