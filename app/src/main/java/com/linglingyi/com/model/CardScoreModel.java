package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/3
 */
public class CardScoreModel implements Serializable {


    /**
     * bankAccount : 6225780680305326
     * bankName : 招商银行
     * createTime : 2019-09-10 15:35:32
     * id : 8ED98E7A1A9641459F0BEAF3D7983BA2
     * idCard : 331082199204198550
     * merchantId : C1B1EE13A7D740C18CB9F75C8F8767A9
     * name : 葛立斌
     * phone :
     * trxAmt : 0.01
     * url : http://daifu.llyzf.cn:8080/hatchet-lly/cardAppraisal/index.html?orderId=8DA2F4CDEC074246A93023440A5C0106
     */

    private String bankAccount;
    private String bankName;
    private String createTime;
    private String id;
    private String idCard;
    private String merchantId;
    private String name;
    private String phone;
    private String trxAmt;
    private String url;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrxAmt() {
        return trxAmt;
    }

    public void setTrxAmt(String trxAmt) {
        this.trxAmt = trxAmt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
