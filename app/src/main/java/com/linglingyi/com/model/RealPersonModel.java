package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * 实名认证信息
 */

public class RealPersonModel implements Serializable {
    private String name;//姓名
    private String idcard;//身份证号
    private String phone;//银行预留手机号
    private String bankAccount;//银行卡号
    private String bankName;//银行名称
    private String bankCode;//银行名称code
    private String cityId;
    private String provinceId;
    private String distinctId;

    public String getProvinceId() {
        return provinceId == null ? "" : provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistinctId() {
        return distinctId == null ? "" : distinctId;
    }

    public void setDistinctId(String distinctId) {
        this.distinctId = distinctId;
    }

    public String getCityId() {
        return cityId == null ? "" : cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
