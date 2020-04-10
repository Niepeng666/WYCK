package com.linglingyi.com.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lilingfei
 * @description: 一卡多还
 * @date: 2019/10/25
 */
public class CardsPlanModel implements Serializable {

    /**
     * balanecMoney : 2342
     * bankAccount : 5187107518653859
     * bankId : 182B810BE08A4CC2A2B35158F8CBDA8F
     * debtMoney : 2345
     * endDate : 11-26
     * planItemList : [{"acqCode":"8106","bindID":"182B810BE08A4CC2A2B35158F8CBDA8F","cardNo":"5187107518653859","cityIndustry":"2756390015","groupNum":1,"industryName":"吕梁市创业咖啡餐饮有限公司","money":1000,"status":"10A","time":"2019-10-29 10:39:49","type":"sale"},{"acqCode":"8106","bindID":"182B810BE08A4CC2A2B35158F8CBDA8F","cardNo":"5187107518653859","cityIndustry":"","groupNum":2,"industryName":"","money":881.8,"status":"10A","time":"2019-10-29 15:11:21","type":"payment"},{"acqCode":"8106","bindID":"182B810BE08A4CC2A2B35158F8CBDA8F","cardNo":"5187107518653859","cityIndustry":"2756270019","groupNum":3,"industryName":"吕梁市离石区晴天化妆品经销部","money":898,"status":"10A","time":"2019-10-29 16:39:55","type":"sale"},{"acqCode":"8106","bindID":"182B810BE08A4CC2A2B35158F8CBDA8F","cardNo":"5187107518653859","cityIndustry":"","groupNum":4,"industryName":"","money":936.5,"status":"10A","time":"2019-10-29 22:32:08","type":"payment"},{"acqCode":"8106","bindID":"182B810BE08A4CC2A2B35158F8CBDA8F","cardNo":"5187107518653859","cityIndustry":"2756520017","groupNum":5,"industryName":"吕梁市三农餐饮服务有限公司","money":1000,"status":"10A","time":"2019-10-30 01:02:46","type":"sale"},{"acqCode":"8106","bindID":"182B810BE08A4CC2A2B35158F8CBDA8F","cardNo":"5187107518653859","cityIndustry":"","groupNum":6,"industryName":"","money":922.6,"status":"10A","time":"2019-10-30 04:41:45","type":"payment"}]
     */
private String bankName;
    private double balanecMoney;
    private String bankAccount;
    private String bankCode;
    private String bankId;
    private double debtMoney;
    private String endDate;
    private List<PlanItemListBean> planItemList;
    private String channelName;
    private String acqCode;
    /**
     * 预留金总额
     */
    private String totalPrice;
    /**
     * 手续费
     */
    private String saleFee;
    /**
     * 笔数费
     */
    private String numFee;
    /**
     * 手续费小计 手续费 笔数费 手续费小计只在主卡上显示
     */
    private String totalFee;
    private String cityId;
    /**
     * 还款时间
     */
    private String payTime;
    private boolean isExpend;

    public boolean isExpend() {
        return isExpend;
    }

    public void setExpend(boolean expend) {
        isExpend = expend;
    }

    public String getBankCode() {
        return bankCode == null ? "" : bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName == null ? "" : bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPayTime() {
        return payTime == null ? "" : payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getCityId() {
        return cityId == null ? "" : cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getChannelName() {
        return channelName == null ? "" : channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAcqCode() {
        return acqCode == null ? "" : acqCode;
    }

    public void setAcqCode(String acqCode) {
        this.acqCode = acqCode;
    }

    public String getTotalPrice() {
        return totalPrice == null ? "" : totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSaleFee() {
        return saleFee == null ? "" : saleFee;
    }

    public void setSaleFee(String saleFee) {
        this.saleFee = saleFee;
    }

    public String getNumFee() {
        return numFee == null ? "" : numFee;
    }

    public void setNumFee(String numFee) {
        this.numFee = numFee;
    }

    public String getTotalFee() {
        return totalFee == null ? "" : totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public double getBalanecMoney() {
        return balanecMoney;
    }

    public void setBalanecMoney(double balanecMoney) {
        this.balanecMoney = balanecMoney;
    }

    public double getDebtMoney() {
        return debtMoney;
    }

    public void setDebtMoney(double debtMoney) {
        this.debtMoney = debtMoney;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<PlanItemListBean> getPlanItemList() {
        return planItemList;
    }

    public void setPlanItemList(List<PlanItemListBean> planItemList) {
        this.planItemList = planItemList;
    }

    public static class PlanItemListBean implements Serializable {
        /**
         * acqCode : 8106
         * bindID : 182B810BE08A4CC2A2B35158F8CBDA8F
         * cardNo : 5187107518653859
         * cityIndustry : 2756390015
         * groupNum : 1
         * industryName : 吕梁市创业咖啡餐饮有限公司
         * money : 1000
         * status : 10A
         * time : 2019-10-29 10:39:49
         * type : sale
         */

        private String acqCode;
        private String bindID;
        private String cardNo;
        private String cityIndustry;
        private int groupNum;
        private String industryName;
        private double money;
        private String status;
        private String time;
        private String type;
        private String cityName;

        public String getCityName() {
            return cityName == null ? "" : cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getAcqCode() {
            return acqCode;
        }

        public void setAcqCode(String acqCode) {
            this.acqCode = acqCode;
        }

        public String getBindID() {
            return bindID;
        }

        public void setBindID(String bindID) {
            this.bindID = bindID;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCityIndustry() {
            return cityIndustry;
        }

        public void setCityIndustry(String cityIndustry) {
            this.cityIndustry = cityIndustry;
        }

        public int getGroupNum() {
            return groupNum;
        }

        public void setGroupNum(int groupNum) {
            this.groupNum = groupNum;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
