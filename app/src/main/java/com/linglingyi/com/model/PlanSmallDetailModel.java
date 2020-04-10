package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/2
 */
public class PlanSmallDetailModel implements Serializable {

    /**
     * cityIndustry : 冠荣广告有限公司
     * customizeCity : 广东省-汕头市
     * fromIncreaseId : 5187107518653859
     * groupNumber : 5187107518653859
     * id : B0DFBB84F91B4C0AA5974F1532287C79
     * industryName : 冠荣广告有限公司
     * merchantId : 1ED1FFD15FC849FAB8738B7942C1434E
     * message :
     * money : 1042
     * orderId :
     * planId : FD3AC12DC7354D6B9436575D4E6CE8BB
     * planTime : {"date":3,"day":3,"hours":8,"minutes":54,"month":3,"nanos":0,"seconds":14,"time":1554252854000,"timezoneOffset":-480,"year":119}
     * status : 10A
     * toIncreaseId : 5187107518653859
     * toMoney : 1042
     * type : sale
     */

    private String cityIndustry;
    private String customizeCity;
    private String fromIncreaseId;
    private String groupNumber;
    private String id;
    private String industryName;
    private String merchantId;
    private String message;
    private double money;
    private String orderId;
    private String planId;
    private PlanTimeBean planTime;
    private String status;
    private String toIncreaseId;
    private double toMoney;
    private String type;
    /**
     * 查询是否有补单计划
     */
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getCityIndustry() {
        return cityIndustry;
    }

    public void setCityIndustry(String cityIndustry) {
        this.cityIndustry = cityIndustry;
    }

    public String getCustomizeCity() {
        return customizeCity;
    }

    public void setCustomizeCity(String customizeCity) {
        this.customizeCity = customizeCity;
    }

    public String getFromIncreaseId() {
        return fromIncreaseId;
    }

    public void setFromIncreaseId(String fromIncreaseId) {
        this.fromIncreaseId = fromIncreaseId;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public PlanTimeBean getPlanTime() {
        return planTime;
    }

    public void setPlanTime(PlanTimeBean planTime) {
        this.planTime = planTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToIncreaseId() {
        return toIncreaseId;
    }

    public void setToIncreaseId(String toIncreaseId) {
        this.toIncreaseId = toIncreaseId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getToMoney() {
        return toMoney;
    }

    public void setToMoney(double toMoney) {
        this.toMoney = toMoney;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class PlanTimeBean implements Serializable{
        /**
         * date : 3
         * day : 3
         * hours : 8
         * minutes : 54
         * month : 3
         * nanos : 0
         * seconds : 14
         * time : 1554252854000
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
