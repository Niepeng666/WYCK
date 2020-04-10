package com.linglingyi.com.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/10/29
 */
public class CardsSmallPlanModel implements Serializable {

    /**
     * bankAccount : 6222523134353333
     * bankCode : 301
     * bankName : 交通银行
     * itemList : [{"acqCode":"8106","cityIndustry":"2207700010","customizeCity":"2207700010","fromIncreaseId":"E891BCE7DCD642F4B90BC623B3BEBE75","groupNumber":"2","id":"5E1A80FD4A36471DB1424A42E2C00038","industryName":"晋城市城区惠路由通讯经销部","merchantId":"6FA0EDC94FB24A2B875545DC4B374301","money":200,"orderId":"","paymentOrderId":"","planId":"858AFFCBE99D4DEB8203CE749A457873","planTime":{"date":30,"day":3,"hours":0,"minutes":0,"month":9,"nanos":0,"seconds":0,"time":1572364800000,"timezoneOffset":-480,"year":119},"status":"10A","toIncreaseId":"E891BCE7DCD642F4B90BC623B3BEBE75","toMoney":200,"type":"sale"},{"acqCode":"8106","cityIndustry":"","customizeCity":"","fromIncreaseId":"E891BCE7DCD642F4B90BC623B3BEBE75","groupNumber":"1","id":"8EE59CB963C445258348569C5234B1CE","industryName":"","merchantId":"6FA0EDC94FB24A2B875545DC4B374301","money":221.7,"orderId":"","paymentOrderId":"","planId":"858AFFCBE99D4DEB8203CE749A457873","planTime":{"date":30,"day":3,"hours":0,"minutes":0,"month":9,"nanos":0,"seconds":0,"time":1572364800000,"timezoneOffset":-480,"year":119},"status":"10A","toIncreaseId":"E891BCE7DCD642F4B90BC623B3BEBE75","toMoney":221.7,"type":"payment"},{"acqCode":"8106","cityIndustry":"","customizeCity":"","fromIncreaseId":"E891BCE7DCD642F4B90BC623B3BEBE75","groupNumber":"3","id":"D3229CAE2CE543AD8C00FE38EF7DBA7A","industryName":"","merchantId":"6FA0EDC94FB24A2B875545DC4B374301","money":192.2,"orderId":"","paymentOrderId":"","planId":"858AFFCBE99D4DEB8203CE749A457873","planTime":{"date":30,"day":3,"hours":0,"minutes":0,"month":9,"nanos":0,"seconds":0,"time":1572364800000,"timezoneOffset":-480,"year":119},"status":"10A","toIncreaseId":"E891BCE7DCD642F4B90BC623B3BEBE75","toMoney":192.2,"type":"payment"},{"acqCode":"8106","cityIndustry":"2207700010","customizeCity":"2207700010","fromIncreaseId":"E891BCE7DCD642F4B90BC623B3BEBE75","groupNumber":"4","id":"F30C6C13BEEE46D19E0F886A130D3964","industryName":"晋城市城区惠路由通讯经销部","merchantId":"6FA0EDC94FB24A2B875545DC4B374301","money":194.4,"orderId":"","paymentOrderId":"","planId":"858AFFCBE99D4DEB8203CE749A457873","planTime":{"date":30,"day":3,"hours":0,"minutes":0,"month":9,"nanos":0,"seconds":0,"time":1572364800000,"timezoneOffset":-480,"year":119},"status":"10A","toIncreaseId":"E891BCE7DCD642F4B90BC623B3BEBE75","toMoney":194.4,"type":"sale"}]
     * paymentMoney : 413.9
     * FROM_INCREASE_ID : E891BCE7DCD642F4B90BC623B3BEBE75
     * successMoney : 0
     */

    private String bankAccount;
    private String bankCode;
    private String bankName;
    private double paymentMoney;
    private String FROM_INCREASE_ID;
    private double successMoney;
    private List<ItemListBean> itemList;
    /**
     * 计划状态
     */
    private String status;
    private StartTimeBean startTime;
    private EndTimeBean endTime;
    private boolean isExpend;

    public boolean isExpend() {
        return isExpend;
    }

    public void setExpend(boolean expend) {
        isExpend = expend;
    }

    public StartTimeBean getStartTime() {
        return startTime;
    }

    public void setStartTime(StartTimeBean startTime) {
        this.startTime = startTime;
    }

    public EndTimeBean getEndTime() {
        return endTime;
    }

    public void setEndTime(EndTimeBean endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(double paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public String getFROM_INCREASE_ID() {
        return FROM_INCREASE_ID;
    }

    public void setFROM_INCREASE_ID(String FROM_INCREASE_ID) {
        this.FROM_INCREASE_ID = FROM_INCREASE_ID;
    }

    public double getSuccessMoney() {
        return successMoney;
    }

    public void setSuccessMoney(double successMoney) {
        this.successMoney = successMoney;
    }

    public List<ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBean> itemList) {
        this.itemList = itemList;
    }

    public static class StartTimeBean implements Serializable {
        /**
         * date : 30
         * day : 3
         * hours : 0
         * minutes : 0
         * month : 9
         * nanos : 0
         * seconds : 0
         * time : 1572364800000
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

    public static class EndTimeBean implements Serializable {
        /**
         * date : 30
         * day : 3
         * hours : 0
         * minutes : 0
         * month : 9
         * nanos : 0
         * seconds : 0
         * time : 1572364800000
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

    public static class ItemListBean implements Serializable {
        /**
         * acqCode : 8106
         * cityIndustry : 2207700010
         * customizeCity : 2207700010
         * fromIncreaseId : E891BCE7DCD642F4B90BC623B3BEBE75
         * groupNumber : 2
         * id : 5E1A80FD4A36471DB1424A42E2C00038
         * industryName : 晋城市城区惠路由通讯经销部
         * merchantId : 6FA0EDC94FB24A2B875545DC4B374301
         * money : 200
         * orderId :
         * paymentOrderId :
         * planId : 858AFFCBE99D4DEB8203CE749A457873
         * planTime : {"date":30,"day":3,"hours":0,"minutes":0,"month":9,"nanos":0,"seconds":0,"time":1572364800000,"timezoneOffset":-480,"year":119}
         * status : 10A
         * toIncreaseId : E891BCE7DCD642F4B90BC623B3BEBE75
         * toMoney : 200
         * type : sale
         */

        private String acqCode;
        private String cityIndustry;
        private String customizeCity;
        private String fromIncreaseId;
        private String groupNumber;
        private String id;
        private String industryName;
        private String merchantId;
        private double money;
        private String orderId;
        private String paymentOrderId;
        private String planId;
        private PlanTimeBean planTime;
        private String status;
        private String toIncreaseId;
        private double toMoney;
        private String type;

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

        public String getAcqCode() {
            return acqCode;
        }

        public void setAcqCode(String acqCode) {
            this.acqCode = acqCode;
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


        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getPaymentOrderId() {
            return paymentOrderId;
        }

        public void setPaymentOrderId(String paymentOrderId) {
            this.paymentOrderId = paymentOrderId;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class PlanTimeBean implements Serializable {
            /**
             * date : 30
             * day : 3
             * hours : 0
             * minutes : 0
             * month : 9
             * nanos : 0
             * seconds : 0
             * time : 1572364800000
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
}
