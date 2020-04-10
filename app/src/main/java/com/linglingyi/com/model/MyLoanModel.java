package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/1/25 16:00
 * @功能 我的贷款
 **/
public class MyLoanModel implements Serializable {


    /**
     * createTime : Tue Feb 26 17:33:18 CST 2019
     * loanAmt : 1000.00
     * loanCycle : 20
     * status : 10A
     */

    private String createTime;
    private String loanAmt;
    private String loanCycle;
    private String status;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(String loanAmt) {
        this.loanAmt = loanAmt;
    }

    public String getLoanCycle() {
        return loanCycle;
    }

    public void setLoanCycle(String loanCycle) {
        this.loanCycle = loanCycle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
