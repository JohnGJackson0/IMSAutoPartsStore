package com.ims.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(foreignKeys = @ForeignKey(entity = Supplier.class,
        parentColumns = "supplier_id",
        childColumns = "supplier_id",
        onDelete = CASCADE))
public class Item {
    public Item(String part_number, String supplierId, boolean isEnabled, String partDescription, long quantityOnHand, long quantityInBack) {
        this.partNumber = part_number;
        this.supplierId = supplierId;
        this.isEnabled = isEnabled;
        this.partDescription = partDescription;
        this.quantityOnHand = quantityOnHand;
        this.quantityInBack = quantityInBack;
    }

    public Item(@NonNull String partNumber, String supplierId, boolean isEnabled, String partDescription, long quantityOnHand, long quantityInBack, long inventoryMax, long inventoryMin, Date dateForNextFutureOrderArrival, long quantityForNextFutureOrderArrival) {
        this.partNumber = partNumber;
        this.supplierId = supplierId;
        this.isEnabled = isEnabled;
        this.partDescription = partDescription;
        this.quantityOnHand = quantityOnHand;
        this.quantityInBack = quantityInBack;
        this.inventoryMax = inventoryMax;
        this.inventoryMin = inventoryMin;
        this.dateForNextFutureOrderArrival = dateForNextFutureOrderArrival;
        this.quantityForNextFutureOrderArrival = quantityForNextFutureOrderArrival;
    }

    @NonNull
    @PrimaryKey
    @ColumnInfo(name="part_number")
    private String partNumber;

    @ColumnInfo(name="supplier_id")
    private String supplierId;

    @ColumnInfo(name="is_enabled")
    private boolean isEnabled;

    @ColumnInfo(name="part_description")
    private String partDescription;

    @ColumnInfo(name="quantity_on_hand")
    private long quantityOnHand;

    @ColumnInfo(name="quantity_in_back")
    private long quantityInBack;

    @ColumnInfo(name="inventory_max")
    private long inventoryMax;

    @ColumnInfo(name="inventory_min")
    private long inventoryMin;

    @ColumnInfo(name="date_for_next_future_order_arrival")
    private Date dateForNextFutureOrderArrival;

    @ColumnInfo(name="quantity_for_next_future_order_arrival")
    private long quantityForNextFutureOrderArrival;

    public Date getDateForNextFutureOrderArrival() {
        return dateForNextFutureOrderArrival;
    }

    public long getQuantityForNextFutureOrderArrival() {
        return quantityForNextFutureOrderArrival;
    }

    public long getInventoryMax() {
        return inventoryMax;
    }

    public long getInventoryMin() {
        return inventoryMin;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public long getQuantityOnHand() {
        return quantityOnHand;
    }

    public long getQuantityInBack() {
        return quantityInBack;
    }

    public void setPartNumber(@NonNull String partNumber) {
        this.partNumber = partNumber;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public void setQuantityOnHand(long quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public void setQuantityInBack(long quantityInBack) {
        this.quantityInBack = quantityInBack;
    }

    public void setInventoryMax(long inventoryMax) {
        this.inventoryMax = inventoryMax;
    }

    public void setDateForNextFutureOrderArrival(Date dateForNextFutureOrderArrival) {
        this.dateForNextFutureOrderArrival = dateForNextFutureOrderArrival;
    }

    public void setQuantityForNextFutureOrderArrival(long quantityForNextFutureOrderArrival) {
        this.quantityForNextFutureOrderArrival = quantityForNextFutureOrderArrival;
    }

    public void setInventoryMin(long inventoryMin) {
        this.inventoryMin = inventoryMin;
    }
}