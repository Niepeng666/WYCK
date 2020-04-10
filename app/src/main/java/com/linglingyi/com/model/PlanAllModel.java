package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description: 查看计划中返回的数据，需要做处理
 * @date: 2019/4/2
 */
public class PlanAllModel implements Serializable {
    /**
     * progress : 0%
     * CITY_INDUSTRY : null
     * BANK_ACCOUNT : 5187107518653859
     * ACQ_CODE : 8087
     * CREATE_TIME : {"date":2,"day":2,"hours":9,"minutes":30,"month":3,"nanos":0,"seconds":36,"time":1554168636000,"timezoneOffset":-480,"year":119}
     * SURPLUS_SALE_MONEY : 12491
     * STATUS : 10A
     * RETURN_MONEY : 0
     * BACK_ID : null
     * SALE_FREE : 98.71
     * fred : 0
     * ID : FD3AC12DC7354D6B9436575D4E6CE8BB
     * PRO : 1
     * CB_AMT : 4791
     * TYPE : 10B
     * THAW_TRX : 0
     * RATE : 0.0079
     * ERR_MSG : null
     * PLAN_AMT : 12345
     * payed : 0
     * START_TIME : {"date":3,"day":3,"hours":0,"minutes":0,"month":3,"nanos":0,"seconds":0,"time":1554220800000,"timezoneOffset":-480,"year":119}
     * UPDATE_TIME : {"date":2,"day":2,"hours":9,"minutes":30,"month":3,"nanos":0,"seconds":36,"time":1554168636000,"timezoneOffset":-480,"year":119}
     * EVERY_NUM : 1
     * PAY_FREE : 3
     * BUCKLE_PAY : 0
     * BUCKLE_FEE : 0
     * numed : 0
     * GROUND_REGION : null
     * SURPLUS_PAYMENT_MONEY : 12389.29
     * saled : 0
     * END_TIME : {"date":5,"day":5,"hours":23,"minutes":59,"month":3,"nanos":0,"seconds":59,"time":1554479999000,"timezoneOffset":-480,"year":119}
     * IS_GROUND : 2
     * MERCHANT_ID : 1ED1FFD15FC849FAB8738B7942C1434E
     * CUSTOMIZE_CITY : null
     */

    private String progress;
    private Object CITY_INDUSTRY;
    private String BANK_ACCOUNT;
    private String ACQ_CODE;
    private CREATETIMEBean CREATE_TIME;
    private double SURPLUS_SALE_MONEY;
    private String STATUS;
    private double RETURN_MONEY;
    private Object BACK_ID;
    private double SALE_FREE;
    private String fred;
    private String ID;
    private double PRO;
    private double CB_AMT;
    private String TYPE;
    private double THAW_TRX;
    private double RATE;
    private Object ERR_MSG;
    private double PLAN_AMT;
    private String payed;
    private STARTTIMEBean START_TIME;
    private UPDATETIMEBean UPDATE_TIME;
    private int EVERY_NUM;
    private double PAY_FREE;
    private double BUCKLE_PAY;
    private double BUCKLE_FEE;
    private String numed;
    private Object GROUND_REGION;
    private double SURPLUS_PAYMENT_MONEY;
    private String saled;
    private ENDTIMEBean END_TIME;
    private int IS_GROUND;
    private String MERCHANT_ID;
    private Object CUSTOMIZE_CITY;
    private String ACQ_NAME;
    private double DISCOUNTS_MONEY;
    private String level;

    public String getLevel() {
        return level == null ? "" : level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getDISCOUNTS_MONEY() {
        return DISCOUNTS_MONEY;
    }

    public void setDISCOUNTS_MONEY(double DISCOUNTS_MONEY) {
        this.DISCOUNTS_MONEY = DISCOUNTS_MONEY;
    }

    public String getACQ_NAME() {
        return ACQ_NAME == null ? "" : ACQ_NAME;
    }

    public void setACQ_NAME(String ACQ_NAME) {
        this.ACQ_NAME = ACQ_NAME;
    }

    public String getProgress() {
        return progress == null ? "" : progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Object getCITY_INDUSTRY() {
        return CITY_INDUSTRY;
    }

    public void setCITY_INDUSTRY(Object CITY_INDUSTRY) {
        this.CITY_INDUSTRY = CITY_INDUSTRY;
    }

    public String getBANK_ACCOUNT() {
        return BANK_ACCOUNT == null ? "" : BANK_ACCOUNT;
    }

    public void setBANK_ACCOUNT(String BANK_ACCOUNT) {
        this.BANK_ACCOUNT = BANK_ACCOUNT;
    }

    public String getACQ_CODE() {
        return ACQ_CODE == null ? "" : ACQ_CODE;
    }

    public void setACQ_CODE(String ACQ_CODE) {
        this.ACQ_CODE = ACQ_CODE;
    }

    public CREATETIMEBean getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(CREATETIMEBean CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public double getSURPLUS_SALE_MONEY() {
        return SURPLUS_SALE_MONEY;
    }

    public void setSURPLUS_SALE_MONEY(double SURPLUS_SALE_MONEY) {
        this.SURPLUS_SALE_MONEY = SURPLUS_SALE_MONEY;
    }

    public String getSTATUS() {
        return STATUS == null ? "" : STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public double getRETURN_MONEY() {
        return RETURN_MONEY;
    }

    public void setRETURN_MONEY(double RETURN_MONEY) {
        this.RETURN_MONEY = RETURN_MONEY;
    }

    public Object getBACK_ID() {
        return BACK_ID;
    }

    public void setBACK_ID(Object BACK_ID) {
        this.BACK_ID = BACK_ID;
    }

    public double getSALE_FREE() {
        return SALE_FREE;
    }

    public void setSALE_FREE(double SALE_FREE) {
        this.SALE_FREE = SALE_FREE;
    }

    public String getFred() {
        return fred == null ? "" : fred;
    }

    public void setFred(String fred) {
        this.fred = fred;
    }

    public String getID() {
        return ID == null ? "" : ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getPRO() {
        return PRO;
    }

    public void setPRO(double PRO) {
        this.PRO = PRO;
    }

    public double getCB_AMT() {
        return CB_AMT;
    }

    public void setCB_AMT(double CB_AMT) {
        this.CB_AMT = CB_AMT;
    }

    public String getTYPE() {
        return TYPE == null ? "" : TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public double getTHAW_TRX() {
        return THAW_TRX;
    }

    public void setTHAW_TRX(double THAW_TRX) {
        this.THAW_TRX = THAW_TRX;
    }

    public double getRATE() {
        return RATE;
    }

    public void setRATE(double RATE) {
        this.RATE = RATE;
    }

    public Object getERR_MSG() {
        return ERR_MSG;
    }

    public void setERR_MSG(Object ERR_MSG) {
        this.ERR_MSG = ERR_MSG;
    }

    public double getPLAN_AMT() {
        return PLAN_AMT;
    }

    public void setPLAN_AMT(double PLAN_AMT) {
        this.PLAN_AMT = PLAN_AMT;
    }

    public String getPayed() {
        return payed == null ? "" : payed;
    }

    public void setPayed(String payed) {
        this.payed = payed;
    }

    public STARTTIMEBean getSTART_TIME() {
        return START_TIME;
    }

    public void setSTART_TIME(STARTTIMEBean START_TIME) {
        this.START_TIME = START_TIME;
    }

    public UPDATETIMEBean getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(UPDATETIMEBean UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public int getEVERY_NUM() {
        return EVERY_NUM;
    }

    public void setEVERY_NUM(int EVERY_NUM) {
        this.EVERY_NUM = EVERY_NUM;
    }

    public double getPAY_FREE() {
        return PAY_FREE;
    }

    public void setPAY_FREE(double PAY_FREE) {
        this.PAY_FREE = PAY_FREE;
    }

    public double getBUCKLE_PAY() {
        return BUCKLE_PAY;
    }

    public void setBUCKLE_PAY(double BUCKLE_PAY) {
        this.BUCKLE_PAY = BUCKLE_PAY;
    }

    public double getBUCKLE_FEE() {
        return BUCKLE_FEE;
    }

    public void setBUCKLE_FEE(double BUCKLE_FEE) {
        this.BUCKLE_FEE = BUCKLE_FEE;
    }

    public String getNumed() {
        return numed == null ? "" : numed;
    }

    public void setNumed(String numed) {
        this.numed = numed;
    }

    public Object getGROUND_REGION() {
        return GROUND_REGION;
    }

    public void setGROUND_REGION(Object GROUND_REGION) {
        this.GROUND_REGION = GROUND_REGION;
    }

    public double getSURPLUS_PAYMENT_MONEY() {
        return SURPLUS_PAYMENT_MONEY;
    }

    public void setSURPLUS_PAYMENT_MONEY(double SURPLUS_PAYMENT_MONEY) {
        this.SURPLUS_PAYMENT_MONEY = SURPLUS_PAYMENT_MONEY;
    }

    public String getSaled() {
        return saled == null ? "" : saled;
    }

    public void setSaled(String saled) {
        this.saled = saled;
    }

    public ENDTIMEBean getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(ENDTIMEBean END_TIME) {
        this.END_TIME = END_TIME;
    }

    public int getIS_GROUND() {
        return IS_GROUND;
    }

    public void setIS_GROUND(int IS_GROUND) {
        this.IS_GROUND = IS_GROUND;
    }

    public String getMERCHANT_ID() {
        return MERCHANT_ID == null ? "" : MERCHANT_ID;
    }

    public void setMERCHANT_ID(String MERCHANT_ID) {
        this.MERCHANT_ID = MERCHANT_ID;
    }

    public Object getCUSTOMIZE_CITY() {
        return CUSTOMIZE_CITY;
    }

    public void setCUSTOMIZE_CITY(Object CUSTOMIZE_CITY) {
        this.CUSTOMIZE_CITY = CUSTOMIZE_CITY;
    }

    public static class CREATETIMEBean implements Serializable {
        /**
         * date : 2
         * day : 2
         * hours : 9
         * minutes : 30
         * month : 3
         * nanos : 0
         * seconds : 36
         * time : 1554168636000
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

    public static class STARTTIMEBean implements Serializable {
        /**
         * date : 3
         * day : 3
         * hours : 0
         * minutes : 0
         * month : 3
         * nanos : 0
         * seconds : 0
         * time : 1554220800000
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

    public static class UPDATETIMEBean implements Serializable {
        /**
         * date : 2
         * day : 2
         * hours : 9
         * minutes : 30
         * month : 3
         * nanos : 0
         * seconds : 36
         * time : 1554168636000
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

    public static class ENDTIMEBean implements Serializable {
        /**
         * date : 5
         * day : 5
         * hours : 23
         * minutes : 59
         * month : 3
         * nanos : 0
         * seconds : 59
         * time : 1554479999000
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
