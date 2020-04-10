package com.linglingyi.com.model;

import com.wuyouchuangke.com.R;
import com.linglingyi.com.utils.StringUtil;

import java.io.Serializable;

/**
 * @作者 chenlanxin
 * @创建日期 2019/4/12 16:33
 * @功能
 **/
public class OrderModel implements Serializable {

    /**
     * createTime : 2019年04月15日 09:06:33
     * goodsImage : http://120.24.154.88/image/shop_1.jpg
     * goodsName : 测试1
     * goodsPrice : 1000分+6.00元
     * goodsSpecification :
     * id : A05027FACD1941358B6EA6D6DE520E3F
     * postNumber : 暂无
     * status : 10C
     */


    private String goodsImage;
    private String goodsName;
    private String goodsPrice;
    private String goodsSpecification;
    private String id;
    private String postNumber;
    private String status;
    private String pay;
    private int goodsCount;
    private CreateTimeBean createTime;
    private CreateTimeBean updateTime;

    public CreateTimeBean getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(CreateTimeBean updateTime) {
        this.updateTime = updateTime;
    }

    public CreateTimeBean getCreateTime() {
        return createTime;
    }

    public void setCreateTime(CreateTimeBean createTime) {
        this.createTime = createTime;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getPay() {
        return pay;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusString() {
        switch (status) {
            case "10A"://待支付 显示前往支付
                return "待支付";
            case "10B"://支付中
                return "支付中";
            case "10C"://待发货
                return "待发货";
            case "10D"://待签收 显示确认收货按钮
                return "待签收";
            case "10E"://已签收
                return "已签收";
            case "10F"://已取消
                return "已取消";
            case "70A"://支付失败
                return "支付失败";
            default:
                return "未知状态";
        }
    }

    public boolean isPay() {
        switch (status) {
            case "10A"://待支付 显示前往支付
                return false;
            case "10B"://支付中
                return false;
            case "10C"://待发货
                return true;
            case "10D"://待签收 显示确认收货按钮
                return true;
            case "10E"://已签收
                return true;
            case "10F"://已取消
                return false;
            case "70A"://支付失败
                return false;
            default:
                return false;
        }
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public static class CreateTimeBean implements Serializable {
        /**
         * date : 10
         * day : 5
         * hours : 15
         * minutes : 38
         * month : 4
         * nanos : 0
         * seconds : 35
         * time : 1557473915000
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
