package com.linglingyi.com.model;

import android.text.TextUtils;

import com.linglingyi.com.utils.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/1
 */
public class CardPlanList implements Serializable {
    private Long planTime; //执行时间
    private String fromCard;  //消费卡
    private String toCard; //到账卡
    private String fromBindId;//支付卡ID
    private String toBindId;//到账卡ID
    private BigDecimal fromMoney;//消费金额
    private BigDecimal toMoney; //到账金额
    private BigDecimal payFee;  //手续费
    private String status;      //状态
    private String planItemId;  //计划详情id
    private String type;//类型 sale是消费，payment是还款
    private String groupNum;
    private HashMap<String, Area> diqu;//地区和商户
    private String industry;//只用来接受后台的商户，在显示计划详情时候用
    private String area;//只用来接受后台的地区，在显示计划详情时候用
    /**
     * 计划失败原因
     */
    private String message;
    private String acqCode;
    private boolean flag;//用来判断要不要显示补单

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getAcqCode() {
        return acqCode == null ? "" : acqCode;
    }

    public void setAcqCode(String acqCode) {
        this.acqCode = acqCode;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public HashMap<String, Area> getDiqu() {
        return diqu;
    }

    public String getDiquString() {
        if (diqu != null) {
//            if (diqu.get("area") != null) {
//                return diqu.get("province").getDivisionName() + diqu.get("city").getDivisionName() + diqu.get("area").getDivisionName();
//            } else {
            return diqu.get("province").getDivisionName() + "-" + diqu.get("city").getDivisionName();
//            }
        } else
            return "";
    }

    public String getMerString() {
        if (diqu != null) {
            return diqu.get("mer").getDivisionName();
        } else
            return "";
    }

    public void setDiqu(HashMap<String, Area> diqu) {
        this.diqu = diqu;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    @Override
    public String toString() {
        if (diqu != null && !TextUtils.equals("-1", diqu.get("province").getId())) {
            String area = diqu.get("province").getDivisionName() + "-" + diqu.get("city").getDivisionName();
            String industry = diqu.get("mer").getId();
            String industryName = diqu.get("mer").getDivisionName();
//            if (diqu.get("area") != null)
//                area += "-" + diqu.get("area").getDivisionName();
            // try {
            return "{" +
                    "planTime=" + planTime +
                    ", fromCard='" + fromCard + '\'' +
                    ", toCard='" + toCard + '\'' +
                    ", fromBindId='" + fromBindId + '\'' +
                    ", toBindId='" + toBindId + '\'' +
                    ", fromMoney=" + fromMoney +
                    ", toMoney=" + toMoney +
                    ", payFee=" + payFee +
                    ", status='" + status + '\'' +
                    //", planItemId='" + planItemId + '\'' +
                    ", type='" + type + '\'' +
                    ", groupNum='" + groupNum + '\'' +
                    ", customizecity='" + area + '\'' +
                    ", cityindustry='" + industry + '\'' +
                    ", cityindustryName='" + industryName + '\'' +
                    ", acqCode='" + acqCode + '\'' +
                    '}';
//            } catch (JSONException e) {
//                e.printStackTrace();
//                return "";
//            }
        } else {
            return "{" +
                    "planTime=" + planTime +
                    ", fromCard='" + fromCard + '\'' +
                    ", toCard='" + toCard + '\'' +
                    ", fromBindId='" + fromBindId + '\'' +
                    ", toBindId='" + toBindId + '\'' +
                    ", fromMoney=" + fromMoney +
                    ", toMoney=" + toMoney +
                    ", payFee=" + payFee +
                    ", status='" + status + '\'' +
                    //", planItemId='" + planItemId + '\'' +
                    ", type='" + type + '\'' +
                    ", groupNum='" + groupNum + '\'' +
                    ", customizecity='" + "" + '\'' +
                    ", cityindustry='" + "" + '\'' +
                    ", cityindustryName='" + "" + '\'' +
                    '}';
        }
    }

    public String getPlanItemId() {
        return planItemId;
    }

    public void setPlanItemId(String planItemId) {
        this.planItemId = planItemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Long planTime) {
        this.planTime = planTime;
    }

    public String getFromCard() {
        return fromCard;
    }

    public void setFromCard(String fromCard) {
        this.fromCard = fromCard;
    }

    public String getToCard() {
        return toCard;
    }

    public void setToCard(String toCard) {
        this.toCard = toCard;
    }

    public String getFromBindId() {
        return fromBindId;
    }

    public void setFromBindId(String fromBindId) {
        this.fromBindId = fromBindId;
    }

    public String getToBindId() {
        return toBindId;
    }

    public void setToBindId(String toBindId) {
        this.toBindId = toBindId;
    }

    public BigDecimal getFromMoney() {
        return fromMoney;
    }

    public void setFromMoney(BigDecimal fromMoney) {
        this.fromMoney = fromMoney;
    }

    public BigDecimal getToMoney() {
        return toMoney;
    }

    public void setToMoney(BigDecimal toMoney) {
        this.toMoney = toMoney;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
