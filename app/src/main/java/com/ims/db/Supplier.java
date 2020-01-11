package com.ims.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Supplier {
    @ColumnInfo(name="supplier_id")
    @PrimaryKey
    private long supplier_id;

    @ColumnInfo(name="is_enabled")
    private boolean isEnabled;

    @ColumnInfo(name="supplier_name")
    private String supplierName;

    @ColumnInfo(name="address")
    private String address;

    @ColumnInfo(name="contact_name")
    private String contactName;

    @ColumnInfo(name="contact_email")
    private String contactEmail;

    @ColumnInfo(name="phone_number")
    private String phoneNumber;

    public Supplier(long supplier_id, boolean isEnabled, String supplierName, String address, String contactName, String contactEmail, String phoneNumber) {
        this.supplier_id = supplier_id;
        this.isEnabled = isEnabled;
        this.supplierName = supplierName;
        this.address = address;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
    }

    public long getSupplier_id() {
        return supplier_id;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
