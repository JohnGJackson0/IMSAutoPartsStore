package com.ims.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

@Entity
public class Sale {

    @NonNull
    @PrimaryKey
    private long id;

    //allowing null for walk-ins
    private long customerNumber;

    private long soldOn;

    private BigDecimal soldFor;

    private boolean onSale;

    public Sale(long id, long soldOn, BigDecimal soldFor) {
        this.id=id;
        this.soldOn = soldOn;
        this.soldFor = soldFor;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public long getSoldOn() {
        return soldOn;
    }

    public void setSoldOn(long soldOn) {
        this.soldOn = soldOn;
    }

    public BigDecimal getSoldFor() {
        return soldFor;
    }

    public void setSoldFor(BigDecimal soldFor) {
        this.soldFor = soldFor;
    }
}
