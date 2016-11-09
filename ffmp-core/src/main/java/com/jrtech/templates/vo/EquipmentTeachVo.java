package com.jrtech.templates.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jrtech.ffmp.data.entities.Equipment;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by jiangliang on 2016/11/9.
 */
public class EquipmentTeachVo{
    private Equipment equipment;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date expired;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date scrap;

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public Date getScrap() {
        return scrap;
    }

    public void setScrap(Date scrap) {
        this.scrap = scrap;
    }
}
