package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/9/5
 */
public class UrlModel implements Serializable {

    /**
     * DK : http://wuyouchuangke.llyzf.cn:6442/lly-posp-proxy/apploan.html?merchantidNo=220573419092483&version=FTYJ
     * BK : http://wuyouchuangke.llyzf.cn:6442/lly-posp-proxy/applicationCard.html?merchantidNo=220573419092483&version=FTYJ
     * JF : http://wk.llyzf.cn:6442/lly-posp-proxy/wwwroot/index.html?&posturl=http%3A%2F%2Fwuyouchuangke.llyzf.cn%3A6442%2Flly-posp-proxy%2F&merchantNo=220573419092483&version=FTYJ-A-1.0.0
     */

    private String DK;
    private String BK;
    private String JF;
    private String DS;

    public String getDS() {
        return DS == null ? "" : DS;
    }

    public void setDS(String DS) {
        this.DS = DS;
    }

    public String getDK() {
        return DK;
    }

    public void setDK(String DK) {
        this.DK = DK;
    }

    public String getBK() {
        return BK;
    }

    public void setBK(String BK) {
        this.BK = BK;
    }

    public String getJF() {
        return JF;
    }

    public void setJF(String JF) {
        this.JF = JF;
    }
}
