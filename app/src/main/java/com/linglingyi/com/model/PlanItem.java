package com.linglingyi.com.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/2
 */
public class PlanItem implements Comparable<PlanItem>, Serializable {
    private String shouldPayNow;
    private String paidAmountNow;
    private String planProgress;
    private String planCycle;
    private String planStatus;
    private String planId;
    private String createTime;
    private String frozenAmount;
    private String prePayFee;
    private String preTimesAmount;
    private String workingFund;
    private String consumed;
    private String deductFee;
    private String deductTimesFee;
    private String payType;
    private String bankCode;
    private String bankAccount;
    private String bankAccountName;
    private String type;  //10C全额还,10B二还一,10A一还一
    private String ACQ_NAME;
    private double DISCOUNTS_MONEY;
    private String level;

    public String getLevel() {
        return level == null ? "" : level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getDISCOUNTS_MONEY() {
        return DISCOUNTS_MONEY;
    }

    public void setDISCOUNTS_MONEY(double DISCOUNTS_MONEY) {
        this.DISCOUNTS_MONEY = DISCOUNTS_MONEY;
    }

    public String getACQ_NAME() {
        return ACQ_NAME == null ? "" : ACQ_NAME;
    }

    public void setACQ_NAME(String ACQ_NAME) {
        this.ACQ_NAME = ACQ_NAME;
    }

    public String getShouldPayNow() {
        return shouldPayNow == null ? "" : shouldPayNow;
    }

    public void setShouldPayNow(String shouldPayNow) {
        this.shouldPayNow = shouldPayNow;
    }

    public String getPaidAmountNow() {
        return paidAmountNow == null ? "" : paidAmountNow;
    }

    public void setPaidAmountNow(String paidAmountNow) {
        this.paidAmountNow = paidAmountNow;
    }

    public String getPlanProgress() {
        return planProgress == null ? "" : planProgress;
    }

    public void setPlanProgress(String planProgress) {
        this.planProgress = planProgress;
    }

    public String getPlanCycle() {
        return planCycle == null ? "" : planCycle;
    }

    public void setPlanCycle(String planCycle) {
        this.planCycle = planCycle;
    }

    public String getPlanStatus() {
        return planStatus == null ? "" : planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public String getPlanId() {
        return planId == null ? "" : planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFrozenAmount() {
        return frozenAmount == null ? "" : frozenAmount;
    }

    public void setFrozenAmount(String frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public String getPrePayFee() {
        return prePayFee == null ? "0" : prePayFee;
    }

    public void setPrePayFee(String prePayFee) {
        this.prePayFee = prePayFee;
    }

    public String getPreTimesAmount() {
        return preTimesAmount == null ? "0" : preTimesAmount;
    }

    public void setPreTimesAmount(String preTimesAmount) {
        this.preTimesAmount = preTimesAmount;
    }

    public String getWorkingFund() {
        return workingFund == null ? "" : workingFund;
    }

    public void setWorkingFund(String workingFund) {
        this.workingFund = workingFund;
    }

    public String getConsumed() {
        return consumed == null ? "" : consumed;
    }

    public void setConsumed(String consumed) {
        this.consumed = consumed;
    }

    public String getDeductFee() {
        return deductFee == null ? "" : deductFee;
    }

    public void setDeductFee(String deductFee) {
        this.deductFee = deductFee;
    }

    public String getDeductTimesFee() {
        return deductTimesFee == null ? "" : deductTimesFee;
    }

    public void setDeductTimesFee(String deductTimesFee) {
        this.deductTimesFee = deductTimesFee;
    }

    public String getPayType() {
        return payType == null ? "" : payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getBankCode() {
        return bankCode == null ? "" : bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAccount() {
        return bankAccount == null ? "" : bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankAccountName() {
        return bankAccountName == null ? "" : bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlanItem other = (PlanItem) obj;
        if (this.toString().equals(other.toString()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "PlanItem{" +
                "shouldPayNow='" + shouldPayNow + '\'' +
                ", paidAmountNow='" + paidAmountNow + '\'' +
                ", planProgress='" + planProgress + '\'' +
                ", planCycle='" + planCycle + '\'' +
                ", planStatus='" + planStatus + '\'' +
                ", planId='" + planId + '\'' +
                ", createTime=" + createTime +
                ", frozenAmount='" + frozenAmount + '\'' +
                ", prePayFee='" + prePayFee + '\'' +
                ", preTimesAmount='" + preTimesAmount + '\'' +
                ", workingFund='" + workingFund + '\'' +
                ", consumed='" + consumed + '\'' +
                ", deductFee='" + deductFee + '\'' +
                ", deductTimesFee='" + deductTimesFee + '\'' +
                ", payType='" + payType + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull PlanItem planItem) {
        return (String.valueOf(planItem.createTime).compareTo(String.valueOf(this.createTime)));
    }
}
