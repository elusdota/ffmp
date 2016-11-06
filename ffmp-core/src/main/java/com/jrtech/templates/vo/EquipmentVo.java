package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.Equipment;

/**
 * Created by jiangliang on 2016/11/6.
 */
public class EquipmentVo {
    private Equipment equipment;
    private int quantity;
    private String location;

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
