package com.ims.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity
public class Supplier {
    @NonNull
    @ColumnInfo(name="supplier_id")
    @PrimaryKey
    private String supplierId;

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

    public Supplier(String supplierId, boolean isEnabled, String supplierName, String address, String contactName, String contactEmail, String phoneNumber) {
        this.supplierId = supplierId;
        this.isEnabled = isEnabled;
        this.supplierName = supplierName;
        this.address = address;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
    }

    public String getSupplierId() {
        return supplierId;
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

    public void setSupplierId(@NonNull String supplierId) {
        this.supplierId = supplierId;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString(){
        return getSupplierId().concat(getSupplierName());
    }
}
