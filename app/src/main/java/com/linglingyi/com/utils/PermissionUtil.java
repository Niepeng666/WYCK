package com.linglingyi.com.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionUtil {
    public static final int ALL = 998;
    //写入联系人
    public static final int WRITE_CONTACTS = 1;
    //获取联系人
    public static final int GET_ACCOUNTS = 2;
    //读取联系人
    public static final int READ_CONTACTS = 3;
    //读取通话记录
    public static final int READ_CALL_LOG = 4;
    //读取电话状态
    public static final int READ_PHONE_STATE = 5;
    //写入通话记录
    public static final int WRITE_CALL_LOG = 6;
    //未知来源应用
    public static final int INSTALL_PACKAGES = 25;
    //进行录音
    public static final int RECORD_AUDIO = 19;
    //接收短信
    public static final int RECEIVE_SMS = 23;
    //发送短信
    public static final int SEND_SMS = 24;

    private PermissionUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 请求这个应用的全部权限
     *
     * @param activity
     * @return
     */
    public static boolean ALL(Activity activity) {
        int permission = PackageManager.PERMISSION_GRANTED;
        int permission1 = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission1 == permission) {
            return true;
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    998);
            return false;
        }
    }

    /**
     * 拨打电话权限
     *
     * @param activity
     * @return
     */
    public static boolean CALL_PHONE(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CALL_PHONE},
                    103);
            return false;
        }

        return true;
    }

    /**
     * 获取相机权限
     *
     * @param activity
     * @return
     */
    public static boolean CAMERA(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},
                    105);
            return false;
        }
        return true;
    }


    //安装未知来源的应用权限
    public static boolean INSTALL_PACKAGES(Activity activity) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.REQUEST_INSTALL_PACKAGES);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES},
                    INSTALL_PACKAGES);
            return false;
        } else {
            return true;
        }
    }

    //进行录音
    public static boolean RECORD_AUDIO(Activity activity) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    RECORD_AUDIO);
            return false;
        } else {
            return true;
        }
    }

    //接收短信
    public static boolean RECEIVE_SMS(Activity activity) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.RECEIVE_SMS);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.RECEIVE_SMS},
                    RECEIVE_SMS);
            return false;
        } else {
            return true;
        }
    }

    //发送短信
    public static boolean SEND_SMS(Activity activity) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.SEND_SMS);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.SEND_SMS},
                    SEND_SMS);
            return false;
        } else {
            return true;
        }
    }

    //写入联系人
    public static boolean WRITE_CONTACTS(Activity activity) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_CONTACTS);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_CONTACTS},
                    WRITE_CONTACTS);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取联系人
     *
     * @param activity
     * @return
     */
    public static boolean GET_ACCOUNTS(Activity activity) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.GET_ACCOUNTS);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.GET_ACCOUNTS},
                    GET_ACCOUNTS);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 读取联系人
     *
     * @param activity
     * @return
     */
    public static boolean READ_CONTACTS(Activity activity) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_CONTACTS);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACTS);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 读取通话记录
     *
     * @param activity
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static boolean READ_CALL_LOG(Activity activity) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_CALL_LOG);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_CALL_LOG},
                    READ_CALL_LOG);
            return false;
        } else {
            return true;
        }
    }

    /**读取电话状态
     * @param activity
     * @return
     */
    public static boolean READ_PHONE_STATE(Activity activity) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_PHONE_STATE);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    READ_PHONE_STATE);
            return false;
        } else {
            return true;
        }
    }

    //写入通话记录
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static boolean WRITE_CALL_LOG(Activity activity) {
        int checkSelfPermission = ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_CALL_LOG);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_CALL_LOG},
                    WRITE_CALL_LOG);
            return false;
        } else {
            return true;
        }
    }

}
