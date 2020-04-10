package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/20
 */
public class RecordListModel implements Serializable {


    /**
     * MERCHANT_CN_NAME : 18858656827
     * PHONE : 18858656827
     * count : 1
     * HEAD_URL : null
     * LEVEL : 1
     */

    private String MERCHANT_CN_NAME;
    private String PHONE;
    private Double count;
    private String HEAD_URL;
    private String LEVEL;
    private int rownum;

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public String getMERCHANT_CN_NAME() {
        return MERCHANT_CN_NAME;
    }

    public void setMERCHANT_CN_NAME(String MERCHANT_CN_NAME) {
        this.MERCHANT_CN_NAME = MERCHANT_CN_NAME;
    }

    public String getPHONE() {
        return PHONE == null ? "" : PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }


    public String getHEAD_URL() {
        return HEAD_URL == null ? "" : HEAD_URL;
    }

    public void setHEAD_URL(String HEAD_URL) {
        this.HEAD_URL = HEAD_URL;
    }

    public String getLEVEL() {
        return LEVEL;
    }

    public void setLEVEL(String LEVEL) {
        this.LEVEL = LEVEL;
    }
}
