package com.jrtech.templates.vo;

import java.util.Date;

/**
 * Created by suelmer on 2016/11/8.
 */
public class EquipmentSaveVo {
    private String id;

    private String name;

    private String maintenanceProjectId;
    //所属客户id
    private String  customerId;
    //编码
    private String code;
    //大类
    private String typemax;
    //小类
    private String typemin;
    //生产厂家
    private String manufacturer;
    //型号
    private String model;
    //备注
    private String description;
    //数量
    private long quantity;
    //设备位置，设备的具体位置披露
    private String location;
    //生产日期
    private Date productionDate;
    //投入使用日期
    private Date inputDate;
    //设备状态，功能是否正常，组件是否齐全，是否有障碍阻挡使用，外观是否完好，标识是否可见
    private String nowstate;

    public String getTypemin() {
        return typemin;
    }

    public void setTypemin(String typemin) {
        this.typemin = typemin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaintenanceProjectId() {
        return maintenanceProjectId;
    }

    public void setMaintenanceProjectId(String maintenanceProjectId) {
        this.maintenanceProjectId = maintenanceProjectId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNowstate() {
        return nowstate;
    }

    public void setNowstate(String nowstate) {
        this.nowstate = nowstate;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getTypemax() {
        return typemax;
    }

    public void setTypemax(String typemax) {
        this.typemax = typemax;
    }
}
