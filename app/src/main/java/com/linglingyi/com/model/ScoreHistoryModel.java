package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/3
 */
public class ScoreHistoryModel implements Serializable {
    /**
     * "bankAccount": "6222021202029267029",
     * "bankName": "工商银行",
     * "createTime": "2018-11-15 14:24:05",
     * "id": "4CE570491F3A4FB49E295FF2C984549A",
     * "idCard": "331082199204198550",
     * "merchantId": "F75D34C91B57440D8D03C42804AC569E",
     * "name": "葛立斌",
     * "phone": "",
     * "trxAmt": "9.90",
     * "url": "http://daifu.llyzf.cn:8080/hatchet-lly/cardAppraisal/index.html?orderId=CCB3E7CB41B6407D9E8DA8140ABA66B3"
     */

    private String bankAccount;
    //    private CreateTimeBean createTime;
    private String createTime;
    private String id;
    private String idCard;
    private String merchantId;
    private String name;
    private String phone;
    private double trxAmt;
    private String url;
    private String bankName;

    public String getBankAccount() {
        return bankAccount == null ? "" : bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard == null ? "" : idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMerchantId() {
        return merchantId == null ? "" : merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getTrxAmt() {
        return trxAmt;
    }

    public void setTrxAmt(double trxAmt) {
        this.trxAmt = trxAmt;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBankName() {
        return bankName == null ? "" : bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
