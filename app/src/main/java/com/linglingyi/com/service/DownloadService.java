package com.linglingyi.com.service;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.linglingyi.com.callback.DownloadCallback;
import com.linglingyi.com.event.InstallPackagesEvent;
import com.linglingyi.com.utils.AppUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.DataCleanManager;
import com.linglingyi.com.utils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.download.DownloadTask;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/6
 */
public class DownloadService extends Service {
    public static final String URL = "url";
    public static final String FILE_NAME = Constant.product_name+".apk";
    public static final String jjcommunity_task = "wyck_task";
    private OkDownloadListener okDownloadListener;
    private DownloadTask downloadTask;
    private int INSTALL_PACKAGES_REQUESTCODE = 0x122;
    private DownloadCallback mDownloadCallback;



    public void setDownloadCallback(DownloadCallback downloadCallback) {
        mDownloadCallback = downloadCallback;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            final String downloadUrl = intent.getStringExtra(URL);
            if (downloadUrl != null && downloadUrl.contains("http")) {
                GetRequest<File> request = OkGo.<File>get(downloadUrl);
//                //创建通知栏
//                mNotification = new DownloadNotification(getApplicationContext());
                //初始化回调监听
                okDownloadListener = new OkDownloadListener();
                //开始下载
                downloadTask = OkDownload.request(jjcommunity_task, request)
                        .fileName(FILE_NAME)
                        .folder(getExternalCacheDir().getAbsolutePath())
                        .save().register(okDownloadListener);
                downloadTask.start();
            }else {
//                ToastUtils.error("app下载地址错误！");
//                ViewUtils.makeToast();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mNotification.cancel();
        downloadTask.remove(true);
        OkDownload.getInstance().removeTask(jjcommunity_task);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void checkIsAndroidO(File file) {
        //适配android26 的安装权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean b = getPackageManager().canRequestPackageInstalls();
            if (b) {
                AppUtils.installApk(getApplicationContext(), file);
            } else {
                //请求安装未知应用来源的权限
                EventBus.getDefault().post(new InstallPackagesEvent(file));
            }
        } else {
            AppUtils.installApk(getApplicationContext(), file);
        }
    }

    class OkDownloadListener extends DownloadListener {

        OkDownloadListener() {
            super("OkDownLoadListener");
        }

        @Override
        public void onStart(Progress progress) {
//            mNotification.show();
        }

        @Override
        public void onProgress(Progress progress) {
            int position = (int) (progress.fraction * 100);
            String speed = DataCleanManager.getFormatSize(progress.speed);
            LogUtils.i("position="+position+"speed="+speed);
            if (mDownloadCallback!=null){
                mDownloadCallback.onProcess(position);
            }
//            mNotification.changed(position, speed);
        }

        @Override
        public void onError(Progress progress) {
//            mNotification.error();
            if (mDownloadCallback!=null){
                mDownloadCallback.onError();
            }
            progress.exception.printStackTrace();
        }

        @Override
        public void onFinish(File file, Progress progress) {
            if (progress.status == Progress.FINISH) {
                checkIsAndroidO(file);
//                mNotification.cancel();

            }
        }

        @Override
        public void onRemove(Progress progress) {
//            mNotification.cancel();
        }
    }
}
