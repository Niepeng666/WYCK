package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/9
 */
public class ProvinceModel implements Serializable {
    private String proId;
    private String province;
    private String cityId;
    private String city;

    public String getProId() {
        return proId == null ? "" : proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProvince() {
        return province == null ? "" : province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityId() {
        return cityId == null ? "" : cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city == null ? "" : city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
