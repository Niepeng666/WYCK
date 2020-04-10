package com.linglingyi.com.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/3/30
 */
public class BindCard implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ID;
    private String BANK_NAME;
    private String ID_CARD_NUMBER;
    private String BANK_ACCOUNT;
    private String BANK_ACCOUNT_NAME;
    private String INCREASE_LIMIT_STATUS;
    private List<CardImg> images;
    private String singleLimit;
    private String EXAMINE_RESULT;
    private int CARD_BIT_CODE;
    private String short_cn_name;
    private String LIMIT_MONEY;
    private String BILL_DAY;
    private String REPAYMENT_DAY;
    private String BANK_PHONE;
    private String cvn;
    private String expdate;
    private int plancount;
    private String is_active;
    private boolean isExpand;
    /**
     * 语音计划固定某个通道
     */
    private String acqCode;
    private String day;
    /**
     * 10B 普通计划   10C全额还  10D 多通道
     */
    private String type;

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDay() {
        return day == null ? "" : day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAcqCode() {
        return acqCode == null ? "" : acqCode;
    }

    public void setAcqCode(String acqCode) {
        this.acqCode = acqCode;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public String getIs_active() {
        return is_active == null ? "" : is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPlanCount() {
        return plancount;
    }

    public void setPlanCount(int plancount) {
        this.plancount = plancount;
    }

    public String getCvn() {
        return cvn;
    }

    public void setCvn(String cvn) {
        this.cvn = cvn;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getShort_cn_name() {
        return short_cn_name;
    }

    public void setShort_cn_name(String short_cn_name) {
        this.short_cn_name = short_cn_name;
    }

    public String getLIMIT_MONEY() {
        return LIMIT_MONEY;
    }

    public void setLIMIT_MONEY(String LIMIT_MONEY) {
        this.LIMIT_MONEY = LIMIT_MONEY;
    }

    public String getBILL_DAY() {
        return BILL_DAY;
    }

    public void setBILL_DAY(String BILL_DAY) {
        this.BILL_DAY = BILL_DAY;
    }

    public String getREPAYMENT_DAY() {
        return REPAYMENT_DAY == null ? "" : REPAYMENT_DAY;
    }

    public void setREPAYMENT_DAY(String REPAYMENT_DAY) {
        this.REPAYMENT_DAY = REPAYMENT_DAY;
    }

    public String getBANK_PHONE() {
        return BANK_PHONE;
    }

    public void setBANK_PHONE(String BANK_PHONE) {
        this.BANK_PHONE = BANK_PHONE;
    }

    public String getEXAMINE_RESULT() {
        return EXAMINE_RESULT;
    }

    public void setEXAMINE_RESULT(String EXAMINE_RESULT) {
        this.EXAMINE_RESULT = EXAMINE_RESULT;
    }

    public String getSingleLimit() {
        return singleLimit;
    }

    public void setSingleLimit(String singleLimit) {
        this.singleLimit = singleLimit;
    }

    public List<CardImg> getImages() {
        return images;
    }

    public void setImages(List<CardImg> images) {
        this.images = images;
    }


    public String getBANK_NAME() {
        return BANK_NAME;
    }

    public void setBANK_NAME(String BANK_NAME) {
        this.BANK_NAME = BANK_NAME;
    }

    public String getID_CARD_NUMBER() {
        return ID_CARD_NUMBER;
    }

    public void setID_CARD_NUMBER(String ID_CARD_NUMBER) {
        this.ID_CARD_NUMBER = ID_CARD_NUMBER;
    }

    public String getBANK_ACCOUNT() {
        return BANK_ACCOUNT;
    }

    public void setBANK_ACCOUNT(String BANK_ACCOUNT) {
        this.BANK_ACCOUNT = BANK_ACCOUNT;
    }

    public String getBANK_ACCOUNT_NAME() {
        return BANK_ACCOUNT_NAME;
    }

    public void setBANK_ACCOUNT_NAME(String BANK_ACCOUNT_NAME) {
        this.BANK_ACCOUNT_NAME = BANK_ACCOUNT_NAME;
    }

    public String getINCREASE_LIMIT_STATUS() {
        return INCREASE_LIMIT_STATUS;
    }

    public void setINCREASE_LIMIT_STATUS(String INCREASE_LIMIT_STATUS) {
        this.INCREASE_LIMIT_STATUS = INCREASE_LIMIT_STATUS;
    }

    public int getCARD_BIT_CODE() {
        return CARD_BIT_CODE;
    }

    public void setCARD_BIT_CODE(int CARD_BIT_CODE) {
        this.CARD_BIT_CODE = CARD_BIT_CODE;
    }

    @Override
    public String toString() {
        return "BindCard{" +
                ", BANK_NAME='" + BANK_NAME + '\'' +
                ", ID_CARD_NUMBER='" + ID_CARD_NUMBER + '\'' +
                ", BANK_ACCOUNT='" + BANK_ACCOUNT + '\'' +
                ", BANK_ACCOUNT_NAME='" + BANK_ACCOUNT_NAME + '\'' +
                ", INCREASE_LIMIT_STATUS='" + INCREASE_LIMIT_STATUS + '\'' +
                ", images=" + images +
                ", singleLimit='" + singleLimit + '\'' +
                ", EXAMINE_RESULT='" + EXAMINE_RESULT + '\'' +
                ", CARD_BIT_CODE=" + CARD_BIT_CODE +
                ", short_cn_name='" + short_cn_name + '\'' +
                ", LIMIT_MONEY='" + LIMIT_MONEY + '\'' +
                ", BILL_DAY='" + BILL_DAY + '\'' +
                ", REPAYMENT_DAY=" + REPAYMENT_DAY +
                ", BANK_PHONE='" + BANK_PHONE + '\'' +
                ", plancount='" + plancount + '\'' +
                '}';
    }
}
