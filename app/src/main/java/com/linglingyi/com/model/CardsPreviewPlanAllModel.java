package com.linglingyi.com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lilingfei
 * @description: 一卡多还
 * @date: 2019/10/25
 */
public class CardsPreviewPlanAllModel implements Serializable {
    private List<CardsPlanModel> mList;
    private String channelName;
    private String acqCode;
    /**
     * 预留金总额
     */
    private String totalPrice;
    /**
     * 手续费
     */
    private String saleFee;
    /**
     * 笔数费
     */
    private String numFee;
    /**
     * 手续费小计 手续费 笔数费 手续费小计只在主卡上显示
     */
    private String totalFee;
    private String cityId;
    /**
     * 还款时间
     */
    private String startDate;
    private String endDate;
    /**
     * 还款总金额
     */
    private String payTotalPrice;
    /**
     * 输入的预留金金额
     */
    private String inputWorkFund;
    /**
     * 每日笔数
     */
    private String dayNum;

    public String getDayNum() {
        return dayNum == null ? "" : dayNum;
    }

    public void setDayNum(String dayNum) {
        this.dayNum = dayNum;
    }

    public String getStartDate() {
        return startDate == null ? "" : startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate == null ? "" : endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInputWorkFund() {
        return inputWorkFund == null ? "" : inputWorkFund;
    }

    public void setInputWorkFund(String inputWorkFund) {
        this.inputWorkFund = inputWorkFund;
    }

    public String getPayTotalPrice() {
        return payTotalPrice == null ? "" : payTotalPrice;
    }

    public void setPayTotalPrice(String payTotalPrice) {
        this.payTotalPrice = payTotalPrice;
    }


    public String getCityId() {
        return cityId == null ? "" : cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAcqCode() {
        return acqCode == null ? "" : acqCode;
    }

    public void setAcqCode(String acqCode) {
        this.acqCode = acqCode;
    }

    public List<CardsPlanModel> getList() {
        if (mList == null) {
            return new ArrayList<>();
        }
        return mList;
    }

    public void setList(List<CardsPlanModel> list) {
        mList = list;
    }

    public String getChannelName() {
        return channelName == null ? "" : channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTotalPrice() {
        return totalPrice == null ? "" : totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSaleFee() {
        return saleFee == null ? "" : saleFee;
    }

    public void setSaleFee(String saleFee) {
        this.saleFee = saleFee;
    }

    public String getNumFee() {
        return numFee == null ? "" : numFee;
    }

    public void setNumFee(String numFee) {
        this.numFee = numFee;
    }

    public String getTotalFee() {
        return totalFee == null ? "" : totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }
}
