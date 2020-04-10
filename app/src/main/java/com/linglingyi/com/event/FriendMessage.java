package com.linglingyi.com.event;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/20
 */
public class FriendMessage implements Serializable {
    private String time;//发布时间
    private String downsload;//下载量
    private String message;//消息
    private String images;//图片
    private String id;//信息id

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDownsload() {
        return downsload;
    }

    public void setDownsload(String downsload) {
        this.downsload = downsload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImages() {
        return images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImages(String images) {

        this.images = images;
    }
}
