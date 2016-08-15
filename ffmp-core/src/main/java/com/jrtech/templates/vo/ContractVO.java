package com.jrtech.templates.vo;


import com.jrtech.ffmp.data.entities.Payment;

import java.util.Date;
import java.util.Set;

/**
 * 合同vo对象
 * Created by suelmer on 2016/7/28.
 */
public class ContractVO {

    private String id;
    //合同名称
    private String name;
    //地址
    private String address;
    //负责人
    private String manager;
    //负责人电话
    private String managerTel;
    //经办人
    private String agent;
    //金额
    private double amount;
    //税号
    private String taxNO;
    //合同内容
    private String content;
    //合同类别
    private String contractType;
    //效期
    private Date expiry;
    //客户
    private String customerId;
    //付款方式
    private Set<Payment> paymentSet;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxNO() {
        return taxNO;
    }

    public void setTaxNO(String taxNO) {
        this.taxNO = taxNO;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerTel() {
        return managerTel;
    }

    public void setManagerTel(String managerTel) {
        this.managerTel = managerTel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Payment> getPaymentSet() {
        return paymentSet;
    }

    public void setPaymentSet(Set<Payment> paymentSet) {
        this.paymentSet = paymentSet;
    }
}


