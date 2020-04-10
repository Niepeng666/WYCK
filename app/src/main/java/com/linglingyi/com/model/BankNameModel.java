package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/30
 */
public class BankNameModel implements Serializable {

    /**
     * bankId : CMB
     * bankCode : 308
     * shortCnName : 招商银行
     * rescode : 00
     * resmsg : 获取成功
     */

    private String bankId;
    private String bankCode;
    private String shortCnName;
    private String rescode;
    private String resmsg;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getShortCnName() {
        return shortCnName;
    }

    public void setShortCnName(String shortCnName) {
        this.shortCnName = shortCnName;
    }

    public String getRescode() {
        return rescode;
    }

    public void setRescode(String rescode) {
        this.rescode = rescode;
    }

    public String getResmsg() {
        return resmsg;
    }

    public void setResmsg(String resmsg) {
        this.resmsg = resmsg;
    }
}
