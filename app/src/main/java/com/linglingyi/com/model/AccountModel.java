package com.linglingyi.com.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class AccountModel implements Serializable {

    /**
     * category : 1
     * type : 8062
     * kstatus : 8062,8064
     * code : 8062
     * name : 银联自选积分通道
     * status : 未开通
     * 通道简介 : xxxxx2
     */

    private String category;
    private String type;
    private String kstatus;
    private String code;
    private String name;
    private String status;
    @JSONField(name = "通道简介")
    private String detail;

    public String getCategory() {
        return category == null ? "0" : category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail() {
        return detail == null ? "" : detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKstatus() {
        return kstatus;
    }

    public void setKstatus(String kstatus) {
        this.kstatus = kstatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
