package com.ims.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

@Entity
public class SaleInventory {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    private long saleId;

    private BigDecimal lineSaleAmount;

    private String partNumber;

    private int amount;

    public SaleInventory(long saleId, BigDecimal lineSaleAmount, String partNumber, int amount) {
        this.saleId = saleId;
        this.lineSaleAmount = lineSaleAmount;
        this.partNumber = partNumber;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSaleId() {
        return saleId;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

    public BigDecimal getLineSaleAmount() {
        return lineSaleAmount;
    }

    public void setLineSaleAmount(BigDecimal lineSaleAmount) {
        this.lineSaleAmount = lineSaleAmount;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }
}
