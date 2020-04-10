package com.linglingyi.com.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description: 图片
 * @date: 2019/5/14
 */
public class ImageModel implements Serializable {
    /**
     * 首页收款图片
     */
    @JSONField(name = "10G")
    private String swipeUrl;
    /**
     * 首页代还图片
     */
    @JSONField(name = "10H")
    private String cardUrl;
    /**
     * 首页信用生活
     */
    @JSONField(name = "10I")
    private String creditUrl;
    /**
     * 登录页背景图
     */
    @JSONField(name = "10L")
    private String loginBgUrl;
    /**
     * 启动页图片
     */
    @JSONField(name = "10K")
    private String launchUrl;

    public String getLaunchUrl() {
        return launchUrl == null ? "" : launchUrl;
    }

    public void setLaunchUrl(String launchUrl) {
        this.launchUrl = launchUrl;
    }

    public String getCreditUrl() {
        return creditUrl == null ? "" : creditUrl;
    }

    public void setCreditUrl(String creditUrl) {
        this.creditUrl = creditUrl;
    }

    public String getLoginBgUrl() {
        return loginBgUrl == null ? "" : loginBgUrl;
    }

    public void setLoginBgUrl(String loginBgUrl) {
        this.loginBgUrl = loginBgUrl;
    }

    public String getSwipeUrl() {
        return swipeUrl == null ? "" : swipeUrl;
    }

    public void setSwipeUrl(String swipeUrl) {
        this.swipeUrl = swipeUrl;
    }

    public String getCardUrl() {
        return cardUrl == null ? "" : cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }
}
