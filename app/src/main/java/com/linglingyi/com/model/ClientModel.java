package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/7
 */
public class ClientModel implements Serializable {

    /**
     * merchantNo : 220315519092853
     * merchantCnName : 贾秀娟
     * linkPhone : 15231552108
     * createTime : 2019-09-18 00:00:00
     * status : 0
     * freezeStatus : 10B
     * level : 1
     * parentPhone : 17782193350
     */

    private String merchantNo;
    private String merchantCnName;
    private String linkPhone;
    private String createTime;
    private String status;
    private String freezeStatus;
    private String level;
    private String parentPhone;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantCnName() {
        return merchantCnName;
    }

    public void setMerchantCnName(String merchantCnName) {
        this.merchantCnName = merchantCnName;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(String freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }
}
