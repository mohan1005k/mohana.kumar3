package com.CNU2016.WebServices.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mohanakumar on 09/07/16.
 */
@Entity
@Table(name="Payment")
public class Payment {

    @Column(name="PaymentId")
    @Id
    private int paymentId;

    public String getMode() {
        return mode;
    }

    public Payment()
    {

    }
    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getOrder_OrderId() {
        return order_OrderId;
    }

    public void setOrder_OrderId(int order_OrderId) {
        this.order_OrderId = order_OrderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    private String mode;
    private Double amount;


    private int order_OrderId;

}
