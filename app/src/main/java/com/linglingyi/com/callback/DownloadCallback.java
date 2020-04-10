package com.linglingyi.com.callback;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/6
 */
public interface DownloadCallback {
    void onSuccess();

    void onError();

    void onProcess(int process);
}
