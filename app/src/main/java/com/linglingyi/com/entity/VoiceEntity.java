package com.linglingyi.com.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author dyx
 * @date on 2019/4/23
 * @describe
 */
public class VoiceEntity implements MultiItemEntity {
    public static final int LEFT_TYPE_NOTICE = 1;
    public static final int LEFT_YTPE_CONTENT = 2;
    public static final int RIGHT_TYPE = 3;
    private int itemType;

    private String inputContent;

    public String getInputContent() {
        return inputContent;
    }

    public VoiceEntity setInputContent(String inputContent) {
        this.inputContent = inputContent;
        return this;
    }

    public VoiceEntity(){}
    public VoiceEntity(int type, String content){
        inputContent = content;
        itemType = type;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public VoiceEntity setItemType(int itemType) {
        this.itemType = itemType;
        return this;
    }

    private String money;
    private String startTime;
    private String endTime;
    private String area;
    private boolean complete;

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
