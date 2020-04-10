package com.linglingyi.com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lilingfei
 * @description: 一卡多还
 * @date: 2019/10/25
 */
public class CardsModel implements Serializable {
    private String cityId;
    private String acqCode;
    private List<MakeCardModel> mMakeCardModelList;
    private boolean backOldCard;
    /**
     * 浙江省-台州市
     */
    private String area;
    /**
     * 输入的预留金
     */
    private String inputWorkFund;

    public String getInputWorkFund() {
        return inputWorkFund == null ? "" : inputWorkFund;
    }

    public void setInputWorkFund(String inputWorkFund) {
        this.inputWorkFund = inputWorkFund;
    }

    public String getArea() {
        return area == null ? "" : area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isBackOldCard() {
        return backOldCard;
    }

    public void setBackOldCard(boolean backOldCard) {
        this.backOldCard = backOldCard;
    }

    public String getCityId() {
        return cityId == null ? "" : cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAcqCode() {
        return acqCode == null ? "" : acqCode;
    }

    public void setAcqCode(String acqCode) {
        this.acqCode = acqCode;
    }

    public List<MakeCardModel> getMakeCardModelList() {
        if (mMakeCardModelList == null) {
            return new ArrayList<>();
        }
        return mMakeCardModelList;
    }

    public void setMakeCardModelList(List<MakeCardModel> makeCardModelList) {
        mMakeCardModelList = makeCardModelList;
    }
}
