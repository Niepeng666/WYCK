package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/25 19:53
 * @功能 费用清单
 **/
public class CostListModel implements Serializable {


    /**
     * consultFee : 0
     * loanServiceFee : 0
     * costMoney : 500
     * interestMoney : 105
     * time : 2019-03-04
     * payAmt : 392
     * withdrawaFee : 3
     */

    private String consultFee;
    private String loanServiceFee;
    private String costMoney;
    private String interestMoney;
    private String time;
    private String payAmt;
    private String withdrawaFee;

    public String getConsultFee() {
        return consultFee;
    }

    public void setConsultFee(String consultFee) {
        this.consultFee = consultFee;
    }

    public String getLoanServiceFee() {
        return loanServiceFee;
    }

    public void setLoanServiceFee(String loanServiceFee) {
        this.loanServiceFee = loanServiceFee;
    }

    public String getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(String costMoney) {
        this.costMoney = costMoney;
    }

    public String getInterestMoney() {
        return interestMoney;
    }

    public void setInterestMoney(String interestMoney) {
        this.interestMoney = interestMoney;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(String payAmt) {
        this.payAmt = payAmt;
    }

    public String getWithdrawaFee() {
        return withdrawaFee;
    }

    public void setWithdrawaFee(String withdrawaFee) {
        this.withdrawaFee = withdrawaFee;
    }
}
