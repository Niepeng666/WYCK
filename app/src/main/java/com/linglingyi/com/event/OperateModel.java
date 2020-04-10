package com.linglingyi.com.event;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/14
 */
public class OperateModel implements Serializable {

    /**
     * createTime : null
     * createUserId :
     * departmentCode :
     * id :
     * imageUrl : http://47.112.13.157:80/image/title_image/25F937081FD34D038C81BB777E1BD5AE.jpg
     * title : 信用卡测试1
     * titleUrl : http://www.bejson.com/
     * type :
     */

    private Object createTime;
    private String createUserId;
    private String departmentCode;
    private String id;
    private String imageUrl;
    private String title;
    private String titleUrl;
    private String type;

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
