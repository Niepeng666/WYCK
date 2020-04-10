package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/3/3 11:07
 * @功能
 **/
public class SmsInfo implements Serializable {
    /**
     * 短信内容
     */
    private String content;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 名字
     */
    private String name;
    /**
     * 短信类型
     */
    private String type;
    /**
     * 时间
     */
    private String sendTime;

    public SmsInfo(String content, String phone, String name, String type, String sendTime) {
        this.content = content;
        this.phone = phone;
        this.name = name;
        this.type = type;
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
