package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by jiangliang on 2016/7/14.维保项目elus
 */
@Entity
@Table(name = "MaintenanceProject")
@JsonIgnoreProperties(value = {"equipments",})
public class MaintenanceProject extends AbstractNamedObject {
    //项目创建人
    @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.REFRESH })
    private Account owner;
    //负责部门
    @NotNull
    @ManyToOne(cascade = { CascadeType.REFRESH })
    private Organization delegate;
    //巡检标准
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "maintenanceProject", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private Collection<Inspection> inspections=new ArrayList<Inspection>();
    @NotNull
    @ManyToOne(cascade = { CascadeType.REFRESH })
    //使用单位
    private Customer customer;
    //建筑投入使用日期
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date inputDate;
    //设备表
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private Collection<Equipment> equipments=new ArrayList<Equipment>();
    //项目编号
    private String code;

    //地址
    private String address;

    //建筑总面积
    private Double area;

    //建筑总高度
    private Double totalHeight;

    //楼层,如“12，13”表示12，13楼
    private String floors;

    //使用性质
    private String nature;

    //消防安全管理人
    private String manager;

    //联系电话
    private String managerTelephone;

    //消防设施情况
    private String equipmentCase;
    //每月几号巡检
    private int days;
    //是否终止
    private boolean terminate;

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Collection<Equipment> getEquipments() {
        return equipments;
    }

    public Organization getDelegate() {
        return delegate;
    }

    public void setDelegate(Organization delegate) {
        this.delegate = delegate;
    }

    public Collection<Inspection> getInspections() {
        return inspections;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getTotalHeight() {
        return totalHeight;
    }

    public void setTotalHeight(Double totalHeight) {
        this.totalHeight = totalHeight;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerTelephone() {
        return managerTelephone;
    }

    public void setManagerTelephone(String managerTelephone) {
        this.managerTelephone = managerTelephone;
    }

    public String getEquipmentCase() {
        return equipmentCase;
    }

    public void setEquipmentCase(String equipmentCase) {
        this.equipmentCase = equipmentCase;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }
}
