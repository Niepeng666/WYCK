package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/4
 */
public class BenefitModel implements Serializable {


    /**
     * createTime : 2019-06-07 09:47
     * merchantCnName : 李玲飞
     * money : 938.00
     * moneyType : 代还分润
     * tradeType : YKXE
     * trxAmt : 0.46
     * typeName : 分润
     */

    private String createTime;
    private String merchantCnName;
    private String money;
    private String moneyType;
    private String tradeType;
    private String trxAmt;
    private String typeName;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMerchantCnName() {
        return merchantCnName;
    }

    public void setMerchantCnName(String merchantCnName) {
        this.merchantCnName = merchantCnName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTrxAmt() {
        return trxAmt;
    }

    public void setTrxAmt(String trxAmt) {
        this.trxAmt = trxAmt;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
