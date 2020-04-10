package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/17 16:16
 * @功能
 **/
public class WinningRecordModel implements Serializable {


    /**
     * city : 黑河市
     * area : 爱辉区
     * totalMoney : 3300.00
     * endTime : 2019-10-31
     * startTime : 2019-10-01
     */

    private String city;
    private String area;
    private String totalMoney;
    private String endTime;
    private String startTime;
    private String merchantName;

    public String getMerchantName() {
        return merchantName == null ? "" : merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
