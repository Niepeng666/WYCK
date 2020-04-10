package com.linglingyi.com.model;

import com.linglingyi.com.utils.StringUtil;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/25
 */
public class MakeCardModel implements Serializable {
    private BindCard mBindCard;
    /**
     * 是否添加卡片
     */
    private boolean isAdd;
    /**
     * 卡内预留金
     */
    private String balanecMoney;
    private String bankId;
    private String bankAccount;
    /**
     * 应还金额
     */
    private String debtMoney;
    /**
     * 还款日前三天
     */
    private String endDate;

    /**
     * 地区 城市id，暂存
     */
    private String cityId;
    private String provinceCity;
    /**
     * 是否返回原卡
     */
    private boolean backOldCard;

    public boolean isBackOldCard() {
        return backOldCard;
    }

    public void setBackOldCard(boolean backOldCard) {
        this.backOldCard = backOldCard;
    }

    public String getProvinceCity() {
        return provinceCity == null ? "" : provinceCity;
    }

    public void setProvinceCity(String provinceCity) {
        this.provinceCity = provinceCity;
    }

    public String getCityId() {
        return cityId == null ? "" : cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getBalanecMoney() {
        return balanecMoney == null ? "" : balanecMoney;
    }

    public void setBalanecMoney(String balanecMoney) {
        this.balanecMoney = balanecMoney;
    }

    public String getBankId() {
        return bankId == null ? "" : bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankAccount() {
        return bankAccount == null ? "" : bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getDebtMoney() {
        return debtMoney == null ? "" : debtMoney;
    }

    public void setDebtMoney(String debtMoney) {
        this.debtMoney = debtMoney;
    }

    public String getEndDate() {
        return endDate == null ? "" : endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public BindCard getBindCard() {
        return mBindCard;
    }

    public void setBindCard(BindCard bindCard) {
        mBindCard = bindCard;
    }
}
