package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/10/14 15:34
 * @功能
 **/
public class OperateModel implements Serializable {

    private String id;
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
