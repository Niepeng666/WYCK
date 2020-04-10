package com.linglingyi.com.model;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/29
 */
public class VipModel implements Serializable {
    @JSONField(name = "2")
    private String str2;
    @JSONField(name = "3")
    private String str3;
    @JSONField(name = "4")
    private String str4;
    @JSONField(name = "5")
    private String str5;

    public String getStr2() {
        return str2 == null ? "" : str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return str3 == null ? "" : str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    public String getStr4() {
        return str4 == null ? "" : str4;
    }

    public void setStr4(String str4) {
        this.str4 = str4;
    }

    public String getStr5() {
        return str5 == null ? "" : str5;
    }

    public void setStr5(String str5) {
        this.str5 = str5;
    }
}
