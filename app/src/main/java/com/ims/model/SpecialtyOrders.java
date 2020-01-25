package com.ims.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SpecialtyOrders {

    @PrimaryKey
    @ColumnInfo(name="order_number")
    private long orderNumber;
    @ColumnInfo(name="customer_number")
    private long customerId;

    public SpecialtyOrders(long orderNumber, long customerId) {
        this.orderNumber = orderNumber;
        this.customerId = customerId;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
