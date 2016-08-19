package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by jiangliang on 2016/7/14.项目设备
 */
@Entity
@Table(name = "Equipment")
@JsonIgnoreProperties(value = {"owner"})
public class Equipment extends AbstractNamedObject {
    //设备所属项目
    @NotNull
    @ManyToOne
    private MaintenanceProject owner;
    @NotNull
    @ManyToOne
    //所属客户
    private Customer customer;
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
    //设备位置，设备的具体位置
    private String location;

    public MaintenanceProject getOwner() {
        return owner;
    }

    public void setOwner(MaintenanceProject owner) {
        this.owner = owner;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypemax() {
        return typemax;
    }

    public void setTypemax(String typemax) {
        this.typemax = typemax;
    }

    public String getTypemin() {
        return typemin;
    }

    public void setTypemin(String typemin) {
        this.typemin = typemin;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
