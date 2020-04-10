package com.linglingyi.com.viewone.qiandao;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public interface OnSignedSuccess{
    boolean OnSignedSuccess(int i, List<Integer> days, AdapterDate.ViewHolder viewHolder);

    void attendance(String[] str);
}
