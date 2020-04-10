package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class IndustryModel implements Serializable {

    /**
     * acqMerchantName : 佛山市鹏飞网络科技有限公司
     * acqMerchantNo : 1903011122431174565
     */

    private String acqMerchantName;
    private String acqMerchantNo;

    public String getAcqMerchantName() {
        return acqMerchantName;
    }

    public void setAcqMerchantName(String acqMerchantName) {
        this.acqMerchantName = acqMerchantName;
    }

    public String getAcqMerchantNo() {
        return acqMerchantNo;
    }

    public void setAcqMerchantNo(String acqMerchantNo) {
        this.acqMerchantNo = acqMerchantNo;
    }
}
