package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description: 制定周转金保存的数据
 * @date: 2019/11/1
 */
public class WorkingFundModel implements Serializable {
    private String inputPayMoney;
    private String payDate;
    /**
     * 消还模式
     */
    private String salePayPosition;
    private String payAmountPerDayPostion;
    private String provinceCity;
    private String cityId;
    private String workgingfund;
    private String totalPrice;
    private String totalFee;

    public String getInputPayMoney() {
        return inputPayMoney == null ? "" : inputPayMoney;
    }

    public void setInputPayMoney(String inputPayMoney) {
        this.inputPayMoney = inputPayMoney;
    }

    public String getPayDate() {
        return payDate == null ? "" : payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getSalePayPosition() {
        return salePayPosition == null ? "" : salePayPosition;
    }

    public void setSalePayPosition(String salePayPosition) {
        this.salePayPosition = salePayPosition;
    }

    public String getPayAmountPerDayPostion() {
        return payAmountPerDayPostion == null ? "" : payAmountPerDayPostion;
    }

    public void setPayAmountPerDayPostion(String payAmountPerDayPostion) {
        this.payAmountPerDayPostion = payAmountPerDayPostion;
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

    public String getWorkgingfund() {
        return workgingfund == null ? "" : workgingfund;
    }

    public void setWorkgingfund(String workgingfund) {
        this.workgingfund = workgingfund;
    }

    public String getTotalPrice() {
        return totalPrice == null ? "" : totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalFee() {
        return totalFee == null ? "" : totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }
}
