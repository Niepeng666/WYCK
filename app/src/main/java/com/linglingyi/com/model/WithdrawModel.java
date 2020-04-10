package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/4
 */
public class WithdrawModel implements Serializable {


    /**
     * balance : 180.00
     * createTime : {"date":11,"day":4,"hours":11,"minutes":48,"month":6,"nanos":618000000,"seconds":34,"time":1562816914618,"timezoneOffset":-480,"year":119}
     * id : 984C3904D4AF422C9C912AFBA388D314
     * moneyType : 10B
     * paymentOrderId :
     * remark :
     * status : 10D
     * trxAmt : 10
     * userId : 220573419076823
     * userType : 10B
     */

    private String balance;
    private CreateTimeBean createTime;
    private String id;
    private String moneyType;
    private String paymentOrderId;
    private String remark;
    private String status;
    private double trxAmt;
    private String userId;
    private String userType;

    public double getTrxAmt() {
        return trxAmt;
    }

    public void setTrxAmt(double trxAmt) {
        this.trxAmt = trxAmt;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public CreateTimeBean getCreateTime() {
        return createTime;
    }

    public void setCreateTime(CreateTimeBean createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public String getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(String paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public static class CreateTimeBean {
        /**
         * date : 11
         * day : 4
         * hours : 11
         * minutes : 48
         * month : 6
         * nanos : 618000000
         * seconds : 34
         * time : 1562816914618
         * timezoneOffset : -480
         * year : 119
         */

        private int date;
        private int day;
        private int hours;
        private int minutes;
        private int month;
        private int nanos;
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

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
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
