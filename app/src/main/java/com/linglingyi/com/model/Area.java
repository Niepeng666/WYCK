package com.linglingyi.com.model;

import com.linglingyi.com.utils.StringUtil;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class Area implements Serializable {

    /**
     * id : C4A6502DE9A6483DBB6F789CB7C69BAC
     * divisionName : 贵州省
     * areaCode : 7000
     * divisionCode : 520000
     */

    private String id;
    private String divisionName;
    private String areaCode;
    private String divisionCode;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }
}
