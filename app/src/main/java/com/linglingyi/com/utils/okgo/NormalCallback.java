package com.linglingyi.com.utils.okgo;

import android.content.Context;

import com.linglingyi.com.utils.LogUtils;
import com.wuyouchuangke.com.R;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.ViewUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/27
 */
public abstract class NormalCallback extends StringCallback {
    //密钥
    private Context mContext;

    public NormalCallback(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onStart(Request request) {
        super.onStart(request);
        Sign(request.getParams());
    }

    private void Sign(HttpParams params) {
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
        LogUtils.i("sign="+sign);
        LogUtils.i("params="+params.toString());
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

    @Override
    public void onError(Response<String> response) {
        LogUtils.e("response=" + response.message());
//        ToastUtils.error("加载失败！");
        ViewUtils.makeToast(mContext,mContext.getString(R.string.server_error),500);
    }
}
