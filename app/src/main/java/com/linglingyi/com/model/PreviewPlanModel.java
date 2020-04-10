package com.linglingyi.com.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class PreviewPlanModel implements Serializable {
    /**
     * 周转金
     */
    private String workingFund;
    /**
     * 还款手续费
     */
    private String fee;
    private String repayAmount;
    /**
     * 还款笔数费
     */
    private String timesFee;
    private String dayTimes;
    private String startDate;
    private String endDate;
    private String acqcode;
    private String f57;
    private HashMap<String, Area> area;
    private String isLuodi;
    private String isZiXuan;
    private String industryJson;
    /**
     * 养卡 手续费+笔数费+保证金  全额还 手续费+笔数费
     */
    private String totalFee;
    /**
     * 是否养卡
     */
    private boolean zhia;

    private String feeLossAmount;
    /**
     * 是否是落地自选通道
     */
    private boolean isGround;

    /**
     * 养卡/全额还 手续费+笔数费
     */
    private String totalServiceFee;
    private String channelName;
    /**
     * 是否多通道
     */
    private boolean channels;
    /**
     * 大小额 10A 混合10B
     */
    private String channelType;

    public String getChannelType() {
        return channelType == null ? "" : channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public boolean isChannels() {
        return channels;
    }

    public void setChannels(boolean channels) {
        this.channels = channels;
    }

    public String getChannelName() {
        return channelName == null ? "" : channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTotalServiceFee() {
        return totalServiceFee == null ? "" : totalServiceFee;
    }

    public void setTotalServiceFee(String totalServiceFee) {
        this.totalServiceFee = totalServiceFee;
    }

    public boolean isGround() {
        return isGround;
    }

    public void setGround(boolean ground) {
        isGround = ground;
    }

    public String getFeeLossAmount() {
        return feeLossAmount == null ? "" : feeLossAmount;
    }

    public void setFeeLossAmount(String feeLossAmount) {
        this.feeLossAmount = feeLossAmount;
    }

    public boolean isZhia() {
        return zhia;
    }

    public void setZhia(boolean zhia) {
        this.zhia = zhia;
    }

    public String getTotalFee() {
        return totalFee == null ? "" : totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getIndustryJson() {
        return industryJson == null ? "" : industryJson;
    }

    public void setIndustryJson(String industryJson) {
        this.industryJson = industryJson;
    }

    public String getIsLuodi() {
        return isLuodi == null ? "" : isLuodi;
    }

    public void setIsLuodi(String isLuodi) {
        this.isLuodi = isLuodi;
    }

    public String getIsZiXuan() {
        return isZiXuan == null ? "" : isZiXuan;
    }

    public void setIsZiXuan(String isZiXuan) {
        this.isZiXuan = isZiXuan;
    }

    public HashMap<String, Area> getArea() {
        return area;
    }

    public void setArea(HashMap<String, Area> area) {
        this.area = area;
    }

    public String getF57() {
        return f57 == null ? "" : f57;
    }

    public void setF57(String f57) {
        this.f57 = f57;
    }

    public String getWorkingFund() {
        return workingFund == null ? "" : workingFund;
    }

    public void setWorkingFund(String workingFund) {
        this.workingFund = workingFund;
    }

    public String getFee() {
        return fee == null ? "" : fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getRepayAmount() {
        return repayAmount == null ? "" : repayAmount;
    }

    public void setRepayAmount(String repayAmount) {
        this.repayAmount = repayAmount;
    }

    public String getTimesFee() {
        return timesFee == null ? "" : timesFee;
    }

    public void setTimesFee(String timesFee) {
        this.timesFee = timesFee;
    }

    public String getDayTimes() {
        return dayTimes == null ? "" : dayTimes;
    }

    public void setDayTimes(String dayTimes) {
        this.dayTimes = dayTimes;
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

    public String getAcqcode() {
        return acqcode == null ? "" : acqcode;
    }

    public void setAcqcode(String acqcode) {
        this.acqcode = acqcode;
    }
}
