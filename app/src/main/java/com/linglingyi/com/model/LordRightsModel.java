package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/17 11:04
 * @功能
 **/
public class LordRightsModel implements Serializable {
    /**
     * area : 台州市
     * city :
     * endtime : 2019-10-31
     * merchantName :
     * oldMerchantName :
     * totalMoney :
     */

    private String area;
    private String city;
    private String endTime;
    private String merchantName;
    private String oldMerchantName;
    private String totalMoney;
    /**
     * 1 不能抢 可以抢
     */
    private String status;
    private String price;
    private String id;

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price == null ? "" : price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOldMerchantName() {
        return oldMerchantName;
    }

    public void setOldMerchantName(String oldMerchantName) {
        this.oldMerchantName = oldMerchantName;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
}
