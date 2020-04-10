package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/4/3
 */
public class CardHonorModel implements Serializable {

    /**
     * id : F0841AFB1862428CAFFD8E4C6184139E
     * status : 10B
     * createTime : 2019-08-26 09:58:35
     * track2 : 18257341578
     * icSerNo : http://daifu.llyzf.cn:8080/hatchet-lly/apply/index.html?orderId=0E5B9BF6CCEA41A79653D103FB730004
     */

    private String id;
    private String status;
    private String createTime;
    private String track2;
    private String icSerNo;
    private String note;

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTrack2() {
        return track2;
    }

    public void setTrack2(String track2) {
        this.track2 = track2;
    }

    public String getIcSerNo() {
        return icSerNo;
    }

    public void setIcSerNo(String icSerNo) {
        this.icSerNo = icSerNo;
    }
}
