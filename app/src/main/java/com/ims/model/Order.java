package com.ims.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    //for use in choosing the finalization to be displayed after selection
    @ColumnInfo(name="on_finalization")
    private boolean onFinalization;

    @ColumnInfo(name="is_finished")
    private boolean isFinished;

    public Order(String comments, boolean isPending, String supplierNumber) {
        this.comments = comments;
        this.isPending = isPending;
        this.supplierNumber = supplierNumber;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isOnFinalization() {
        return onFinalization;
    }

    public void setOnFinalization(boolean onFinalization) {
        this.onFinalization = onFinalization;
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
