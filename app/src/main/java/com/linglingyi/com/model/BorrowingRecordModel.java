package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/21 17:15
 * @功能
 **/
public class BorrowingRecordModel implements Serializable {

    /**
     * createTime : 2019-02-26
     * days : 20
     * loanAmt : 1000.00
     * loanCycle : 20
     * status : 10F
     */

    private String id;
    private String createTime;
    private String days;
    private String loanAmt;
    private String loanCycle;
    private String status;
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
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
