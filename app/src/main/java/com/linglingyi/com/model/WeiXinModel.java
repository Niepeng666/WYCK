package com.linglingyi.com.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/8
 */
public class WeiXinModel implements Serializable {
    /**
     * sign : 07AB9F098088018BF0EA56304FF3D426
     * timestamp : 1552891682
     * noncestr : 8rS5gm5X1KFZp4xh
     * partnerid : 1528624121
     * prepayid : wx18144802900067a9aad67ecd0137427600
     * package : Sign=WXPay
     * appid : wxabe70d9cbd95f9ec
     */

    private String sign;
    private String timestamp;
    private String noncestr;
    private String partnerid;
    private String prepayid;
    @JSONField(name = "package")
    private String packageX;
    private String appid;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
