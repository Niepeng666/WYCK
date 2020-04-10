package com.linglingyi.com.utils.okHttp;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.linglingyi.com.utils.StringUtil;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;

/**
 * OkHttp3异步回调
 */
public class OkHttpCallback implements okhttp3.Callback {

    private String mUrl;
    private Call mCall;
    private OkHttp3Util.MyCallback mCallback;
    private OkHttpHandler mOkHttpHandler;

    public OkHttpCallback(String url, Call call, OkHttp3Util.MyCallback callback) {
        mUrl = url;
        mCall = call;
        mCallback = callback;
        mOkHttpHandler = new OkHttpHandler(this);
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        if (mCallback == null) {
            if (mCall != null && !mCall.isCanceled()) {
                mCall.cancel();
            }
        } else {
            if (!mOkHttpHandler.getLooper().getThread().isAlive()) {
                if (mCall != null && !mCall.isCanceled()) {
                    mCall.cancel();
                }
                return;
            }
            if (e instanceof ConnectException) {
                mOkHttpHandler.sendEmptyMessage(OkHttp3Util.CALL_EXCEPTION_CONNECTION);
            } else if (e instanceof SocketTimeoutException) {
                mOkHttpHandler
                        .sendEmptyMessage(OkHttp3Util.CALL_EXCEPTION_SOCKET_TIME_OUT);
            } else {
                mOkHttpHandler.sendEmptyMessage(OkHttp3Util.CALL_EXCEPTION_ERROR);
            }
        }
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) {
        if (mCallback == null) {
            if (mCall != null && !mCall.isCanceled()) {
                mCall.cancel();
            }
            return;
        }
        if (!mOkHttpHandler.getLooper().getThread().isAlive()) {
            if (mCall != null && !mCall.isCanceled()) {
                mCall.cancel();
            }
            return;
        }
        if (!response.isSuccessful()) {
            mOkHttpHandler.sendEmptyMessage(OkHttp3Util.CALL_EXCEPTION_ERROR);
            return;
        }
        ResponseBody body = response.body();
        if (body == null) {
            mOkHttpHandler.sendEmptyMessage(OkHttp3Util.CALL_EXCEPTION_ERROR);
            return;
        }
        String contentEncoding = response.header("Content-Encoding");
        BufferedSource bufferedSource;
        if (!StringUtil.isEmpty(contentEncoding)
                && "gzip".equalsIgnoreCase(contentEncoding)) {
            GzipSource gzipSource = new GzipSource(body.source());
            bufferedSource = Okio.buffer(gzipSource);
        } else {
            bufferedSource = Okio.buffer(body.source());
        }
        try {
            String result = bufferedSource.readUtf8();
            Message message = mOkHttpHandler.obtainMessage();
            message.arg1 = response.code();
            message.obj = result;
            message.what = OkHttp3Util.CALL_SUCCESS;
            mOkHttpHandler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            mOkHttpHandler.sendEmptyMessage(OkHttp3Util.CALL_EXCEPTION_ERROR);
        }
    }

    /**
     * 异步回调处理
     */
    private class OkHttpHandler extends Handler {
        private OkHttpCallback mOkHttpCallback;

        OkHttpHandler(OkHttpCallback okHttpCallback) {
            mOkHttpCallback = okHttpCallback;
        }

        @Override
        public void handleMessage(Message msg) {
            if (mOkHttpCallback == null) {
                return;
            }
            OkHttp3Util.MyCallback callback = mOkHttpCallback.mCallback;
            String url = mOkHttpCallback.mUrl;
            if (callback == null) {
                if (mOkHttpCallback.mCall != null && !mOkHttpCallback.mCall.isCanceled()) {
                    mOkHttpCallback.mCall.cancel();
                }
                return;
            }
            if (msg.what == OkHttp3Util.CALL_SUCCESS) {
                callback.onRequestComplete(url, msg.arg1, String.valueOf(msg.obj));
            } else {
                OkHttp3Util.callbackException(url, callback, msg.what);
            }
        }

    }
}