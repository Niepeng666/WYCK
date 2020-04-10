package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/30
 */
public class ApplyCreditEntity implements Serializable {
    /**
     * channelId : 1004281757568073728
     * iconPath : https://finance-increment.oss-cn-hangzhou.aliyuncs.com/platform/bankIcon-cd98dbe5-7ed8-45fb-9a1c-18f9900f11ff.jpg
     * imagePath : https://finance-increment.oss-cn-hangzhou.aliyuncs.com/platform/channelImage-e58e80c7-4291-4751-9f80-c3346192cd41.jpg
     * name : 花旗银行
     * pr_id : 1004272868491526145
     * searchUrl : https://www.citibank.com.cn/CNGCB/ICARD/appsta/showNext.do?JFP_TOKEN=TT869KEL
     */

    private String channelId;
    private String iconPath;
    private String imagePath;
    private String name;
    private String pr_id;
    private String searchUrl;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPr_id() {
        return pr_id;
    }

    public void setPr_id(String pr_id) {
        this.pr_id = pr_id;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

}
