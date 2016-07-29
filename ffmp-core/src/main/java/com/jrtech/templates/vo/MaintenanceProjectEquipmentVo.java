package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.Equipment;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jiangliang on 2016/7/29.
 */
public class MaintenanceProjectEquipmentVo {
    private String id;
    private Collection<Equipment> equipments = new ArrayList<Equipment>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Collection<Equipment> equipments) {
        this.equipments = equipments;
    }
}
