package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by jiangliang on 2016/8/2.维修设备单或巡检设备单，elus
 */
@Entity
@Table(name = "TaskEquipemt")
@JsonIgnoreProperties(value = {"maintenanceTask"})
public class TaskEquipemt extends AbstractDomainObject {
    //任务
    @NotNull
    @ManyToOne
    private MaintenanceTask maintenanceTask;
    @NotNull
    @OneToOne
    private Equipment equipment;
    //说明
    private String description;

    public MaintenanceTask getMaintenanceTask() {
        return maintenanceTask;
    }

    public void setMaintenanceTask(MaintenanceTask maintenanceTask) {
        this.maintenanceTask = maintenanceTask;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
