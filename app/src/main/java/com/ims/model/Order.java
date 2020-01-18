package com.ims.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Order {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="order_number")
    private long orderNumber;

    //todo
    //@ColumnInfo(name="date_of_arrival_estimate")
    //private Date estimatedArrivalDate;

    private String comments;

    @ColumnInfo(name="is_pending")
    private boolean isPending;

    @ColumnInfo(name="supplier_number")
    private String supplierNumber;


    public Order(String comments, boolean isPending, String supplierNumber) {
        this.comments = comments;
        this.isPending = isPending;
        this.supplierNumber = supplierNumber;
    }

    public Order() { }

    public Order(boolean isPending) {this.isPending=isPending; }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

}
