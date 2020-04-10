package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * ${tags}
 *
 * @Title: ${enclosing_method}
 * @author:wujun
 */
public class MyOrderModel implements Serializable {


    /**
     * address :
     * deliverCompany :
     * deliverNumber :
     * fare : 0
     * goodsId : F1B007E583AB4EA3BB2571C5FE140851
     * goodsImage : http://112.74.46.255:80/image/goods_image/AD51F1B8AAAC45CEB679C292699820A6.jpg?0.3298642655475672?1541991295325
     * goodsName : 提额秘籍测试
     * id : 08C965DDD06F403E8B98A92F11082070
     * isShow :
     * merchantId :
     * merchantName :
     * orderNo : 20181122152035503
     * payTime : null
     * price : 10
     * quantity : 1
     * status : 10A
     * unitPrice : 0
     * userName :
     * userPhone :
     */

    private String address;
    private String deliverCompany;
    private String deliverNumber;
    private int fare;
    private String goodsId;
    private String goodsImage;
    private String goodsName;
    private String id;
    private String isShow;
    private String merchantId;
    private String merchantName;
    private String orderNo;
    private Object payTime;
    private int price;
    private int quantity;
    private String status;
    private int unitPrice;
    private String userName;
    private String userPhone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliverCompany() {
        return deliverCompany;
    }

    public void setDeliverCompany(String deliverCompany) {
        this.deliverCompany = deliverCompany;
    }

    public String getDeliverNumber() {
        return deliverNumber;
    }

    public void setDeliverNumber(String deliverNumber) {
        this.deliverNumber = deliverNumber;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Object getPayTime() {
        return payTime;
    }

    public void setPayTime(Object payTime) {
        this.payTime = payTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
