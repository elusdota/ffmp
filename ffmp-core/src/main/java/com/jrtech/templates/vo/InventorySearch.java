package com.jrtech.templates.vo;

import java.util.Date;

/**
 * Created by jiangliang on 2016/6/29.bootstrap-table采购，出库，入库查询虚拟实体对象，elus
 */
public class InventorySearch extends TableGetDataParameters {
    //入库类型
    private String inventoryType;
    //名称
    private String name;
    //类型
    private String type;
    //生产厂家
    private String manufacturer;
    //型号
    private String model;
    //单据编号
    private String number;
    //起始日期
    private Date startdate;
    //结束日期
    private Date enddate;
    //执行人
    private String executor;

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }
}
