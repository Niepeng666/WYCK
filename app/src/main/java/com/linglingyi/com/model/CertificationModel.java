package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/2/12 11:57
 * @功能
 **/
public class CertificationModel implements Serializable {

    /**
     * remark : 实名认证
     * amount : 5000
     * rate : 0.50
     * isOpen : 1
     * isMust : 1
     * msg : 请完善实名信息
     * status : 0
     */

    private String remark;
    private String amount;
    private String rate;
    private String isOpen;
    private String isMust;
    private String msg;
    private int status;
    private String logo;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getIsMust() {
        return isMust;
    }

    public void setIsMust(String isMust) {
        this.isMust = isMust;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
