package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/29
 */
public class CardsBigPlanModel implements Serializable {


    /**
     * STATUS : 10C
     * SALE_FREE : 17.8
     * money : null
     * DISCOUNTS_MONEY : 0.36
     * progress : 0%
     * CB_AMT : 234
     * PLAN_AMT : 2580
     * PAY_FREE : 15
     */

    private String STATUS;
    private double SALE_FREE;
    private Object money;
    private double DISCOUNTS_MONEY;
    private String progress;
    private double CB_AMT;
    private double PLAN_AMT;
    private double PAY_FREE;
    private String level;
    private String EVERY_NUM;
    private String ACQ_NAME;

    public String getLevel() {
        return level == null ? "" : level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEVERY_NUM() {
        return EVERY_NUM == null ? "" : EVERY_NUM;
    }

    public void setEVERY_NUM(String EVERY_NUM) {
        this.EVERY_NUM = EVERY_NUM;
    }

    public String getACQ_NAME() {
        return ACQ_NAME == null ? "" : ACQ_NAME;
    }

    public void setACQ_NAME(String ACQ_NAME) {
        this.ACQ_NAME = ACQ_NAME;
    }

    public String getProgress() {
        return progress == null ? "" : progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public double getCB_AMT() {
        return CB_AMT;
    }

    public void setCB_AMT(double CB_AMT) {
        this.CB_AMT = CB_AMT;
    }

    public double getPLAN_AMT() {
        return PLAN_AMT;
    }

    public void setPLAN_AMT(double PLAN_AMT) {
        this.PLAN_AMT = PLAN_AMT;
    }

    public double getPAY_FREE() {
        return PAY_FREE;
    }

    public void setPAY_FREE(double PAY_FREE) {
        this.PAY_FREE = PAY_FREE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public double getSALE_FREE() {
        return SALE_FREE;
    }

    public void setSALE_FREE(double SALE_FREE) {
        this.SALE_FREE = SALE_FREE;
    }

    public Object getMoney() {
        return money;
    }

    public void setMoney(Object money) {
        this.money = money;
    }

    public double getDISCOUNTS_MONEY() {
        return DISCOUNTS_MONEY;
    }

    public void setDISCOUNTS_MONEY(double DISCOUNTS_MONEY) {
        this.DISCOUNTS_MONEY = DISCOUNTS_MONEY;
    }


}
