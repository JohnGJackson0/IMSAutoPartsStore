package com.ims.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Item {
    public Item(long part_number, long supplierId, boolean isEnabled, String partDescription, long quantityOnHand, long quantityInBack) {
        this.part_number = part_number;
        this.supplierId = supplierId;
        this.isEnabled = isEnabled;
        this.partDescription = partDescription;
        this.quantityOnHand = quantityOnHand;
        this.quantityInBack = quantityInBack;
    }

    @PrimaryKey
    private long part_number;

    @ColumnInfo(name="supplier_id")
    private long supplierId;

    @ColumnInfo(name="is_enabled")
    private boolean isEnabled;

    @ColumnInfo(name="part_description")
    private String partDescription;

    @ColumnInfo(name="quantity_on_hand")
    private long quantityOnHand;

    @ColumnInfo(name="quantity_in_back")
    private long quantityInBack;

    public long getPart_number() {
        return part_number;
    }

    public long getSupplierId() {
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
}