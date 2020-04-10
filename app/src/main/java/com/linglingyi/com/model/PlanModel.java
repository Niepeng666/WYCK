package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class PlanModel implements Serializable {
    /**
     * bindID : 4167C3B4D2CC4DDAAB9016626213659B
     * cardNo : 5187107518653859
     * groupNum : 1
     * money : 271
     * status : 10A
     * time : 2019-01-12 11:48:38
     * type : sale
     */

    private String bindID;
    private String cardNo;
    private int groupNum;
    private double money;
    private String status;
    private String time;
    private String type;
    private String cityIndustry;
    private String industryName;
    private String acqCode;

    public String getAcqCode() {
        return acqCode == null ? "" : acqCode;
    }

    public void setAcqCode(String acqCode) {
        this.acqCode = acqCode;
    }

    public String getCityIndustry() {
        return cityIndustry == null ? "" : cityIndustry;
    }

    public String getIndustryName() {
        return industryName == null ? "" : industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public void setCityIndustry(String cityIndustry) {
        this.cityIndustry = cityIndustry;
    }

    public String getBindID() {
        return bindID;
    }

    public void setBindID(String bindID) {
        this.bindID = bindID;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
