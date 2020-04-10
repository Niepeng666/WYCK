package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/30
 */
public class ApplyRecordEntity implements Serializable {
    /**
     * createTime : 19-02-25
     * productName : 花旗银行
     * status : 10B 成功 70A失败 其他审核中
     */

    private String createTime;
    private String productName;
    private String status;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
