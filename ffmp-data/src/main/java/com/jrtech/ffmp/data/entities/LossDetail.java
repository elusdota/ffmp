package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by jiangliang on 2016/8/15.报损单子表，elus
 */
@Entity
@JsonIgnoreProperties(value = {"loss"})
public class LossDetail extends AbstractNamedObject {
    //库存id
    private String inventory_id;
    //类型
    private String type;
    //生产厂家
    private String manufacturer;
    //型号
    private String model;
    //备注
    private String description;
    //数量
    private long quantity;
    //单价
    private double price;
    //金额
    private double amount;
    @NotNull
    @ManyToOne
    private Loss loss;

    public String getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Loss getLoss() {
        return loss;
    }

    public void setLoss(Loss loss) {
        this.loss = loss;
    }
}
