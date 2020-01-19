package com.ims.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.annotation.NonNull;

import java.math.BigDecimal;

@Entity(primaryKeys= { "order_number", "part_number" } )
public class OrderInventory {
    @NonNull
    @ColumnInfo(name="order_number")
    private Long orderNumber;

    @NonNull
    @ColumnInfo(name="part_number")
    private String partNumber;

    @ColumnInfo
    private long quantity;

    @ColumnInfo(name="extended_cost")
    private BigDecimal extentedCost;

    @ColumnInfo(name="is_pending")
    private boolean isPending;

    @ColumnInfo(name="is_valid")
    private boolean isValid;

    public OrderInventory(@NonNull Long orderNumber, @NonNull String partNumber) {
        this.orderNumber = orderNumber;
        this.partNumber = partNumber;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getExtentedCost() {
        return extentedCost;
    }

    public void setExtentedCost(BigDecimal extentedCost) {
        this.extentedCost = extentedCost;
    }
}
