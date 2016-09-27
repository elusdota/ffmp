package com.jrtech.templates.vo;

import java.util.Date;

/**
 * Created by suelmer on 2016/7/19.
 */
public class PaymentVO {

    private String id;
    //期数
    private int period;

    //付款时间
    private Date paymentDate;

    //票据(普票，专票，收据)
    private String receipt;
    //收款金额
    private double paymentAmount;
    //是否确认收款
    private boolean confirmation;

    public PaymentVO(){}

    public PaymentVO(double paymentAmount,boolean confirmation, String id,Date paymentDate, int period, String receipt) {
        this.paymentAmount = paymentAmount;
        this.confirmation = confirmation;
        this.id = id;
        this.paymentDate = paymentDate;
        this.period = period;
        this.receipt = receipt;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
