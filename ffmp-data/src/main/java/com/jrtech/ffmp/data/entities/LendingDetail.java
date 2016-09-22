package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by jiangliang on 2016/9/22.
 */
@Entity
@JsonIgnoreProperties(value = {"lending"})
public class LendingDetail extends AbstractDomainObject {
    //库存
    @NotNull
    @ManyToOne(cascade = { CascadeType.REFRESH })
    private Inventory inventory;
    //数量
    private long quantity;
    @NotNull
    @ManyToOne
    private Lending lending;

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Lending getLending() {
        return lending;
    }

    public void setLending(Lending lending) {
        this.lending = lending;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
