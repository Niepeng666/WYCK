package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/20
 */
public class SchoolBusinessModel implements Serializable {
    /**
     * id : 28
     * title : 测试1
     * image : http://120.24.180.28/image/uploadImage/information/692E06136B53488AA58E19127749B497.jpg
     * skipUrl : www.baidu.com
     * createTime : 2019-07-03 13:03:21
     */

    private int id;
    private String title;
    private String image;
    private String skipUrl;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
