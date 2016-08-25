package com.jrtech.templates.vo;

/**
 * Created by suelmer on 2016/8/20.
 */
public class TaskEquipemtVO {
    //任务ID
    private String maintenanceTaskId;
    //设备编码
    private String equipmentCode;
    //说明
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getMaintenanceTaskId() {
        return maintenanceTaskId;
    }

    public void setMaintenanceTaskId(String maintenanceTaskId) {
        this.maintenanceTaskId = maintenanceTaskId;
    }
}
