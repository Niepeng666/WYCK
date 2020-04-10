package com.linglingyi.com.utils.okgo;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linglingyi.com.utils.LogUtils;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.MyApplication;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.ViewUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/27
 */
public class OkClient {
    private static final String TAG = "OkClient";
    public volatile static OkClient okClient;
    //请求超时的重试次数 默认超时不重连
    private int retryCount = 0;

    private OkClient() {
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static OkClient getInstance() {
        if (okClient == null) {
            synchronized (OkClient.class) {
                if (okClient == null) {
                    okClient = new OkClient();
                }
            }
        }
        return okClient;
    }

    /**
     * 获取文件HttpParams对象
     *
     * @return
     */
    public static Params getParamsInstance() {
        return Params.getInstance();
    }

    /**
     * 取消所有请求
     */
    public static void CancelAll() {
        OkGo.getInstance().cancelAll();
    }

    private static void Sign(HttpParams params) {
        params.put("0", "0700");
        params.put("59", Constant.VERSION);
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : params.urlParamsMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue().get(0));
        }
        Map<String, Object> resultMap = sortMapByKey(map);
        String sign = "";
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            if ("".equals(sign)) {
                sign = entry.getValue() + "";
            } else {
                sign += entry.getValue();
            }

        }
        if (!sign.equals("")) {
            sign += Constant.mainKey;
        }
        params.put("64", CommonUtils.Md5(sign));
        LogUtils.i("sign=" + sign);
        LogUtils.i("params=" + params.toString());
    }

    /***
     * 进行排序
     * @param map
     * @return
     */
    private static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Object> sortMap = new TreeMap<String, Object>(new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                // 这里改为小写进行比较
                Integer l = Integer.parseInt(lhs);
                Integer r = Integer.parseInt(rhs);
                return l.compareTo(r);
            }
        });
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * 设置超时重连次数
     *
     * @param retryCount
     * @return
     */
    public OkClient setRetryCount(int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    /**
     * get请求
     *
     * @param url            get请求地址
     * @param entityCallback 实体类回调
     */
    public void get(String url, AbsCallback entityCallback) {
        OkGo.get(url)
                .tag(this)
                .retryCount(retryCount)
                .execute(entityCallback);
    }

    /**
     * get请求
     *
     * @param url            get请求地址
     * @param params         可以使用OkMap，就不用考虑转化为String的麻烦，int boolean float都可以
     * @param entityCallback 实体类的接口回调，解析的时候要注意，这里使用的是FastJson
     */
    public void get(String url, HttpParams params, AbsCallback entityCallback) {
        LogUtils.i(url + "\n" + params.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        OkGo.get(url)
                .tag(this)
                .retryCount(retryCount)
                .params(params)
                .execute(entityCallback);
    }

    /**
     * post请求
     *
     * @param url            post请求地址
     * @param params         可以使用OkMap，就不用考虑转化为String的麻烦，int boolean float都可以
     * @param entityCallback 实体类的接口回调，解析的时候要注意，这里使用的是FastJson
     */
    public void post(String url, HttpParams params, AbsCallback entityCallback) {
        LogUtils.i(url + "\n" + params.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        OkGo.post(url)
                .tag(this)
                .retryCount(retryCount)
                .params(params)
                .execute(entityCallback);
    }


    /**
     * @param params
     * @param entityCallback
     */
    public void post(HttpParams params, AbsCallback entityCallback) {
        LogUtils.i(Constant.REQUEST_API + "\n" + params.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        OkGo.<String>post(Constant.REQUEST_API)
                .tag(this)
                .retryCount(retryCount)
                .params(params)
                .execute(entityCallback);

    }


    /**
     * okgo的回调，这里进行实体类的解析
     */
    public abstract static class EntityCallBack<T> extends AbsCallback<T> {

        private Context mContext;
        private Class<T> clazz;
        private BaseEntity baseEntity;

        public EntityCallBack(Context context, Class<T> clazz) {
            this.mContext = context;
            this.clazz = clazz;
        }

        @Override
        public T convertResponse(okhttp3.Response response) throws Throwable {
            synchronized (this) {
                T entity;
                String jsonStr = response.body().string();
                LogUtils.i("json=" + jsonStr);
                try {
                    entity = null;
                    if (clazz != null) {
                        entity = JSON.parseObject(jsonStr, clazz);
                        baseEntity = (BaseEntity) entity;
                    }
                } catch (Exception e) {
                    try {
                        baseEntity = JSON.parseObject(jsonStr, BaseEntity.class);
                    } catch (Exception e1) {
                        LogUtils.e("BaseEntity解析错误:" + e.getMessage());
                    }
                    LogUtils.e(clazz.toString() + "解析错误:" + e.getMessage());
                    return null;
                } finally {
                    response.close();
                }

                return entity;
            }
        }

        @Override
        public void onStart(Request<T, ? extends Request> request) {
            super.onStart(request);
            Sign(request.getParams());
        }


        @Override
        public void onCacheSuccess(Response<T> response) {
            LogUtils.i("onCacheSuccess");
        }

        @Override
        public void onError(Response<T> response) {
            if (response == null) {
                return;
            }
            if (response.getException() != null && response.getException() instanceof ConnectException) {
                ViewUtils.makeToast(mContext, mContext.getString(R.string.nonetwork), 500);
                LogUtils.e("response="+response.getException());
            }else {
                LogUtils.e("response="+response.body());
                ViewUtils.makeToast(mContext, mContext.getString(R.string.server_error), 500);
            }


        }

        @Override
        public void onSuccess(Response<T> response) {
            LogUtils.i("response=" + response.body());
            if (baseEntity == null) {
                ViewUtils.makeToast(mContext, mContext.getString(R.string.server_error), 500);
                return;
            }
            String code = baseEntity.getStr39();
            switch (code) {
                case "00":
                    break;
                case "01":
                    ViewUtils.makeToast(mContext, "请持卡人与发卡银行联系", 500);
                    break;
                case "03":
                    ViewUtils.makeToast(mContext, "无效商户", 500);
                    break;
                case "04":
                    ViewUtils.makeToast(mContext, "此卡被没收", 500);
                    break;
                case "05":
                    ViewUtils.makeToast(mContext, "持卡人认证失败", 500);
                    break;
                case "10":
                    ViewUtils.makeToast(mContext, "显示部分批准金额，提示操作员", 500);
                    break;
                case "11":
                    ViewUtils.makeToast(mContext, "成功，VIP客户", 500);
                    break;
                case "12":
                    ViewUtils.makeToast(mContext, "无效交易", 500);
                    break;
                case "13":
                    ViewUtils.makeToast(mContext, "无效金额", 500);
                    break;
                case "14":
                    ViewUtils.makeToast(mContext, "无效卡号", 500);
                    break;
                case "15":
                    ViewUtils.makeToast(mContext, "此卡无对应发卡方", 500);
                    break;
                case "16":
                    ViewUtils.makeToast(mContext, "信用卡已认证", 500);
                    break;
                case "17":
                    ViewUtils.makeToast(mContext, "请使用信用卡申请认证", 500);
                    break;
                case "21":
                    ViewUtils.makeToast(mContext, "该卡未初始化或睡眠卡", 500);
                    break;
                case "22":
                    ViewUtils.makeToast(mContext, "操作有误，或超出交易允许天数", 500);
                    break;
                case "25":
                    ViewUtils.makeToast(mContext, "没有原始交易，请联系发卡方", 500);
                    break;
                case "30":
                    ViewUtils.makeToast(mContext, "请重试", 500);
                    break;
                case "34":
                    ViewUtils.makeToast(mContext, "作弊卡,呑卡", 500);
                    break;
                case "38":
                    ViewUtils.makeToast(mContext, "密码错误次数超限，请与发卡方联系", 500);
                    break;
                case "40":
                    ViewUtils.makeToast(mContext, "发卡方不支持的交易类型", 500);
                    break;
                case "41":
                    ViewUtils.makeToast(mContext, "挂失卡，请没收", 500);
                    break;
                case "43":
                    ViewUtils.makeToast(mContext, "被窃卡，请没收", 500);
                    break;
                case "45":
                    ViewUtils.makeToast(mContext, "芯片卡交易，请插卡操作", 500);
                    break;
                case "51":
                    ViewUtils.makeToast(mContext, "可用余额不足", 500);
                    break;
                case "54":
                    ViewUtils.makeToast(mContext, "该卡已过期", 500);
                    break;
                case "55":
                    ViewUtils.makeToast(mContext, "用户名或者密码错误", 500);
                    break;
                case "57":
                    ViewUtils.makeToast(mContext, "不允许此卡交易", 500);
                    break;
                case "58":
                    ViewUtils.makeToast(mContext, "发卡方不允许该卡在本终端进行此交易", 500);
                    break;
                case "59":
                    ViewUtils.makeToast(mContext, "卡片校验错", 500);
                    break;
                case "61":
                    ViewUtils.makeToast(mContext, "交易金额超限", 500);
                    break;
                case "62":
                    ViewUtils.makeToast(mContext, "受限制的卡", 500);
                    break;
                case "64":
                    ViewUtils.makeToast(mContext, "交易金额与原交易不匹配", 500);
                    break;
                case "65":
                    ViewUtils.makeToast(mContext, "超出消费次数限制", 500);
                    break;
                case "68":
                    ViewUtils.makeToast(mContext, "交易超时，请重试", 500);
                    break;
                case "75":
                    ViewUtils.makeToast(mContext, "密码错误次数超限", 500);
                    break;
                case "90":
                    ViewUtils.makeToast(mContext, "系统日切，请稍后重试", 500);
                    break;
                case "91":
                    ViewUtils.makeToast(mContext, "发卡方状态不正常，请稍后重试", 500);
                    break;
                case "92":
                    ViewUtils.makeToast(mContext, "发卡方线路异常，请稍后重试", 500);
                    break;
                case "94":
                    ViewUtils.makeToast(mContext, "拒绝，重复交易，请稍后重试", 500);
                    break;
                case "96":
                    ViewUtils.makeToast(mContext, "拒绝，交换中心异常，请稍后重试", 500);
                    break;
                case "97":
                    ViewUtils.makeToast(mContext, "终端未登记", 500);
                    break;
                case "98":
                    ViewUtils.makeToast(mContext, "发卡方超时", 500);
                    break;
                case "99":
                    ViewUtils.makeToast(mContext, "PIN格式错，请重新签到", 500);
                    break;
                case "A0":
                    ViewUtils.makeToast(mContext, "MAC校验错", 500);
                    break;
                case "A1":
                    ViewUtils.makeToast(mContext, "转账货币不一致", 500);
                    break;
                case "A2":
                    ViewUtils.makeToast(mContext, "交易成功，请向发卡行确认", 500);
                    break;
                case "A3":
                    ViewUtils.makeToast(mContext, "账户不正确", 500);
                    break;
                case "A4":
                case "A5":
                case "A6":
                    ViewUtils.makeToast(mContext, "交易成功，请向发卡行确认", 500);
                    break;
                case "A7":
                    ViewUtils.makeToast(mContext, "拒绝，交换中心异常，请稍后重试", 500);
                    break;
                case "B1":
                    ViewUtils.makeToast(mContext, "不支持该终端", 500);
                    break;
                case "F0":
                    ViewUtils.makeToast(mContext, "拒绝，终端初始化失败", 500);
                    break;
                case "W1":
                    ViewUtils.makeToast(mContext, "不允许提现", 500);
                    break;
                case "W2":
                    ViewUtils.makeToast(mContext, "当前时间不允许提现", 500);
                    break;
                case "W3":
                    ViewUtils.makeToast(mContext, "节假日不允许提现", 500);
                    break;
                case "W4":
                    ViewUtils.makeToast(mContext, "提现受理失败，小于提现金额", 500);
                    break;
                case "W5":
                    ViewUtils.makeToast(mContext, "提现受理失败，超出提现次数", 500);
                    break;
                case "W6":
                    ViewUtils.makeToast(mContext, "提现失败，请先完成［申请提额］", 500);
                    break;
                case "W7":
                    ViewUtils.makeToast(mContext, "小于终端日限额", 500);
                    break;
                case "W8":
                    ViewUtils.makeToast(mContext, "商户资料审核不通过，请重新提交", 500);
                    break;
                case "W9":
                    ViewUtils.makeToast(mContext, "提现失败，提现过于频繁", 500);
                    break;
                case "W10":
                    ViewUtils.makeToast(mContext, "代理商不存在", 500);
                    break;
                case "W11":
                    ViewUtils.makeToast(mContext, "超出交易限额或交易次数", 500);
                    break;
                case "ZC":
                    ViewUtils.makeToast(mContext, "验证码错误", 500);
                    break;
                case "ZD":
                    ViewUtils.makeToast(mContext, "手机号码已注册，请直接登录", 500);
                    break;
                case "ZE":
                    ViewUtils.makeToast(mContext, "银行卡实名验证失败", 500);
                    break;
                case "ZV":
                    ViewUtils.makeToast(mContext, "请更新到最新版本", 500);
                    break;
                case "ZZ":
                    ViewUtils.makeToast(mContext, "操作失败", 500);
                    break;
                case "ZZ0":
                    ViewUtils.makeToast(mContext, "版本号为空", 500);
                    break;
                case "ZZ1":
                    ViewUtils.makeToast(mContext, "短信验证操作异常", 500);
                    break;
                case "ZZ2":
                    ViewUtils.makeToast(mContext, "密码不正确", 500);
                    break;
                case "ZZ3":
                    ViewUtils.makeToast(mContext, "验证码为空", 500);
                    break;
                case "ZZ4":
                    ViewUtils.makeToast(mContext, "资质上传失败，请重新上传", 500);
                    break;
                case "ZZ5":
                    ViewUtils.makeToast(mContext, "签名上传失败，请重新上传", 500);
                    break;
                case "ZZ6":
                    ViewUtils.makeToast(mContext, "提现操作异常", 500);
                    break;
                case "ZZ7":
                    ViewUtils.makeToast(mContext, "刷卡头检测及绑定操作异常", 500);
                    break;
                case "ZZ8":
                    ViewUtils.makeToast(mContext, "实名认证异常，请重试", 500);
                    break;
                case "ZZ9":
                    ViewUtils.makeToast(mContext, "商户同步操作异常，请重试", 500);
                    break;
                case "ZZ10":
                    ViewUtils.makeToast(mContext, "保存APP提额商户异常", 500);
                    break;
                case "ZZ11":
                    ViewUtils.makeToast(mContext, "查询APP提额商户异常", 500);
                    break;
                case "ZZ12":
                    ViewUtils.makeToast(mContext, "APP提额商户资质上传异常", 500);
                    break;
                case "ZZ13":
                    ViewUtils.makeToast(mContext, "APP提额商户已存在", 500);
                    break;
                case "ZZ14":
                    ViewUtils.makeToast(mContext, "终端已绑定其它用户，选择正确的用户信息登录", 500);
                    break;
                case "ZZ15":
                    ViewUtils.makeToast(mContext, "删除APP提额商户异常", 500);
                    break;
                case "ZZ16":
                    ViewUtils.makeToast(mContext, "资质图片不能为空", 500);
                    break;
                case "T1":
                    ViewUtils.makeToast(mContext, "交易失败，正在重新审核", 500);
                    break;
                case "S5":
                    ViewUtils.makeToast(mContext, "APP超过交易次数", 500);
                    break;
                case "S7":
                    ViewUtils.makeToast(mContext, "请联系客服开通代理后台", 500);
                    break;
                default:
                    ViewUtils.makeToast(mContext, code, 500);
                    break;
            }

        }
    }

    /**
     * 这里设置参数 HttpParams
     */
    public static class Params {

        private static HttpParams params;

        public static Params getInstance() {
            params = new HttpParams();
            return new Params();
        }

        public HttpParams getParams() {
            return params;
        }

        public Params put(String key, String value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, int value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, long value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, Float value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, double value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, char value) {
            params.put(key, value);
            return this;
        }

        public Params put(String key, File file) {
            params.put(key, file);
            return this;
        }

        public Params put(String key, List<String> filePathList) {
            if (filePathList == null || filePathList.size() == 0) {
                return this;
            }
            for (String filePath : filePathList) {
                if (!TextUtils.isEmpty(filePath)) {
                    File file = new File(filePath);
                    params.put(key, file);
                }
            }
            return this;
        }

        public Params put(String key, ArrayList<File> fileList) {
            if (fileList == null || fileList.size() == 0) {
                return this;
            }
            for (File file : fileList) {
                if (file != null) {
                    params.put(key, file);
                }
            }
            return this;
        }
    }
}
