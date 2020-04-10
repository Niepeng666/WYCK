package com.linglingyi.com.model;


/**
 * @作者 chenlanxin
 * @创建日期 2019/1/16 15:47
 * @功能
 **/
public class PhoneDto {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 联系人
     */
    private String linkPerson;

    public PhoneDto(String linkPerson, String phone) {
        this.phone = phone;
        this.linkPerson = linkPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }
}
