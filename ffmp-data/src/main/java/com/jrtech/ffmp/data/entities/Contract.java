package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by suelmer on 2016/7/5.
 */
@Entity
@Table(name="contract")
public class Contract extends AbstractNamedObject{

    //地址
    private String address;
   //负责人
    private String manager;
    //负责人电话
    private String managerTel;
    //付款方式
    private String payment;
    //经办人
    private String agent;
    //金额
    private double amount;
    //税号
    private String TaxNO;
    //效期
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date expiry;

    //合同内容
    private String content;

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createTime;
    //附件
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Annexes> annexesList = new ArrayList<>();
   //客户
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<Annexes> getAnnexesList() {
        return annexesList;
    }

    public void setAnnexesList(List<Annexes> annexesList) {
        this.annexesList = annexesList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTaxNO() {
        return TaxNO;
    }

    public void setTaxNO(String taxNO) {
        TaxNO = taxNO;
    }
}
