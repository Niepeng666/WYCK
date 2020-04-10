package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/30
 */
public class ApplyLoanEntity implements Serializable {
    /**
     * description : 最快3秒到账
     * iconPath : https://lion-cloud.oss-cn-hangzhou.aliyuncs.com/loan/924946155891064834/product-loanPlatformProduct-2b0f2821-f153-4a0a-bdb5-7224787bb0da.jpg
     * name : 还呗
     * pr_id : 984748941935378432
     */

    private String description;
    private String iconPath;
    private String name;
    private String pr_id;
    private String rate;
    private String amount;

    public String getAmount() {
        return amount == null ? "" : amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRate() {
        return rate == null ? "" : rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
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

}
