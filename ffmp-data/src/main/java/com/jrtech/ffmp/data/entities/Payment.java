package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 付款方式
 * Created by suelmer on 2016/7/19.
 */
@Entity
@Table(name = "Payment")
public class Payment extends AbstractDomainObject {

    // 期数
    private int period;

    //付款时间
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date paymentDate;

    //票据(普票，专票，收据)
    private String receipt;

    //收款金额
    private double paymentAmount;
    //是否确认收款
    private boolean confirmation;


    public Payment(){

    }
    public Payment(double paymentAmount,boolean confirmation, Date paymentDate, int period, String receipt) {
        this.paymentAmount = paymentAmount;
        this.confirmation = confirmation;
        this.paymentDate = paymentDate;
        this.period = period;
        this.receipt = receipt;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }
}
