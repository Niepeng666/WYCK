package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/3
 */
public class ImageScrollModel implements Serializable {


    /**
     * abortDate : null
     * checkOpinion :
     * checkOrderReason : 1
     * checkPassTime : null
     * createTime : {"date":11,"day":4,"hours":10,"minutes":48,"month":6,"nanos":0,"seconds":22,"time":1562813302000,"timezoneOffset":-480,"year":119}
     * createUser : F9946CAE0D574FB1BDFDAE922F7821E1
     * departmentCode : 00010001000000000000000000000000
     * disposeStatus : 10A
     * id : 25A7305035D6438C89545510501BC473
     * lastUpdateTime : null
     * orderPaymentId :
     * singleNo : http://120.77.156.217:80/image/checkOrder_image/25A7305035D6438C89545510501BC473/5222DD6F0AD444AFADF0F5FE652BBB48.png
     * status : 10A
     */

    private Object abortDate;
    private String checkOpinion;
    private String checkOrderReason;
    private Object checkPassTime;
    private CreateTimeBean createTime;
    private String createUser;
    private String departmentCode;
    private String disposeStatus;
    private String id;
    private Object lastUpdateTime;
    private String orderPaymentId;
    private String singleNo;
    private String status;

    public Object getAbortDate() {
        return abortDate;
    }

    public void setAbortDate(Object abortDate) {
        this.abortDate = abortDate;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    public String getCheckOrderReason() {
        return checkOrderReason;
    }

    public void setCheckOrderReason(String checkOrderReason) {
        this.checkOrderReason = checkOrderReason;
    }

    public Object getCheckPassTime() {
        return checkPassTime;
    }

    public void setCheckPassTime(Object checkPassTime) {
        this.checkPassTime = checkPassTime;
    }

    public CreateTimeBean getCreateTime() {
        return createTime;
    }

    public void setCreateTime(CreateTimeBean createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(String disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Object lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getOrderPaymentId() {
        return orderPaymentId;
    }

    public void setOrderPaymentId(String orderPaymentId) {
        this.orderPaymentId = orderPaymentId;
    }

    public String getSingleNo() {
        return singleNo;
    }

    public void setSingleNo(String singleNo) {
        this.singleNo = singleNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class CreateTimeBean implements Serializable{
        /**
         * date : 11
         * day : 4
         * hours : 10
         * minutes : 48
         * month : 6
         * nanos : 0
         * seconds : 22
         * time : 1562813302000
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
