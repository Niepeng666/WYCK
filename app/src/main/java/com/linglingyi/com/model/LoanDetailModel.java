package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/3/3 19:37
 * @功能 贷款详情
 **/
public class LoanDetailModel implements Serializable {


    /**
     * actualRepaymentTime : null
     * agentId : 9
     * automaticRepayment : 10A
     * collectorId : 0
     * consultFee : 100
     * createDate : {"date":28,"day":4,"hours":15,"minutes":9,"month":1,"seconds":24,"time":1551337764000,"timezoneOffset":-480,"year":119}
     * createId : 0
     * createUser : null
     * delFlag : false
     * expireRepaymentTime : {"date":11,"day":1,"hours":15,"minutes":9,"month":2,"seconds":24,"time":1552288164000,"timezoneOffset":-480,"year":119}
     * id : 18
     * loanAmt : 1000
     * loanCycle : 11
     * loanNo : 55520190228150924077
     * loanOrderNo :
     * loanRate : 10
     * loanServiceFee : 100
     * loanStatus : 10E
     * loanTime : null
     * merchantId : 3
     * overdueDays : 0
     * overdueRate : 21
     * payAmt : 697
     * promoterId : 123
     * remarks :
     * repaymentAmt : 1000
     * repaymentOrderNo :
     * updateDate : {"date":28,"day":4,"hours":16,"minutes":48,"month":1,"seconds":43,"time":1551343723000,"timezoneOffset":-480,"year":119}
     * updateId : 2
     * updateUser : null
     */

    private CreateDateBean actualRepaymentTime;
    private int agentId;
    private String automaticRepayment;
    private int collectorId;
    private double consultFee;
    private CreateDateBean createDate;
    private int createId;
    private UpdateDateBean createUser;
    private boolean delFlag;
    private ExpireRepaymentTimeBean expireRepaymentTime;
    private int id;
    private double loanAmt;
    private int loanCycle;
    private String loanNo;
    private String loanOrderNo;
    private double loanRate;
    private double loanServiceFee;
    private String loanStatus;
    private UpdateDateBean loanTime;
    private int merchantId;
    private int overdueDays;
    private double overdueRate;
    private double payAmt;
    private int promoterId;
    private String remarks;
    private double repaymentAmt;
    private String repaymentOrderNo;
    private UpdateDateBean updateDate;
    private int updateId;
    private UpdateDateBean updateUser;

    public CreateDateBean getActualRepaymentTime() {
        return actualRepaymentTime;
    }

    public void setActualRepaymentTime(CreateDateBean actualRepaymentTime) {
        this.actualRepaymentTime = actualRepaymentTime;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getAutomaticRepayment() {
        return automaticRepayment;
    }

    public void setAutomaticRepayment(String automaticRepayment) {
        this.automaticRepayment = automaticRepayment;
    }

    public int getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
    }

    public double getConsultFee() {
        return consultFee;
    }

    public void setConsultFee(double consultFee) {
        this.consultFee = consultFee;
    }

    public CreateDateBean getCreateDate() {
        return createDate;
    }

    public void setCreateDate(CreateDateBean createDate) {
        this.createDate = createDate;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public UpdateDateBean getCreateUser() {
        return createUser;
    }

    public void setCreateUser(UpdateDateBean createUser) {
        this.createUser = createUser;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public ExpireRepaymentTimeBean getExpireRepaymentTime() {
        return expireRepaymentTime;
    }

    public void setExpireRepaymentTime(ExpireRepaymentTimeBean expireRepaymentTime) {
        this.expireRepaymentTime = expireRepaymentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(double loanAmt) {
        this.loanAmt = loanAmt;
    }

    public int getLoanCycle() {
        return loanCycle;
    }

    public void setLoanCycle(int loanCycle) {
        this.loanCycle = loanCycle;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public String getLoanOrderNo() {
        return loanOrderNo;
    }

    public void setLoanOrderNo(String loanOrderNo) {
        this.loanOrderNo = loanOrderNo;
    }

    public double getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(double loanRate) {
        this.loanRate = loanRate;
    }

    public double getLoanServiceFee() {
        return loanServiceFee;
    }

    public void setLoanServiceFee(double loanServiceFee) {
        this.loanServiceFee = loanServiceFee;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public UpdateDateBean getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(UpdateDateBean loanTime) {
        this.loanTime = loanTime;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
    }

    public double getOverdueRate() {
        return overdueRate;
    }

    public void setOverdueRate(double overdueRate) {
        this.overdueRate = overdueRate;
    }

    public double getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(double payAmt) {
        this.payAmt = payAmt;
    }

    public int getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(int promoterId) {
        this.promoterId = promoterId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getRepaymentAmt() {
        return repaymentAmt;
    }

    public void setRepaymentAmt(double repaymentAmt) {
        this.repaymentAmt = repaymentAmt;
    }

    public String getRepaymentOrderNo() {
        return repaymentOrderNo;
    }

    public void setRepaymentOrderNo(String repaymentOrderNo) {
        this.repaymentOrderNo = repaymentOrderNo;
    }

    public UpdateDateBean getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(UpdateDateBean updateDate) {
        this.updateDate = updateDate;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public UpdateDateBean getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(UpdateDateBean updateUser) {
        this.updateUser = updateUser;
    }

    public static class CreateDateBean {
        /**
         * date : 28
         * day : 4
         * hours : 15
         * minutes : 9
         * month : 1
         * seconds : 24
         * time : 1551337764000
         * timezoneOffset : -480
         * year : 119
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    public static class ExpireRepaymentTimeBean {
        /**
         * date : 11
         * day : 1
         * hours : 15
         * minutes : 9
         * month : 2
         * seconds : 24
         * time : 1552288164000
         * timezoneOffset : -480
         * year : 119
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    public static class UpdateDateBean {
        /**
         * date : 28
         * day : 4
         * hours : 16
         * minutes : 48
         * month : 1
         * seconds : 43
         * time : 1551343723000
         * timezoneOffset : -480
         * year : 119
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int seconds;
        private long time;
        private int timezoneOffset;
        private int year;

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }
}
