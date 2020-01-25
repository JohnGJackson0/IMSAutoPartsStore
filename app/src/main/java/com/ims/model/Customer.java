package com.ims.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Customer {

    @NonNull
    @PrimaryKey
    private long id;

    @ColumnInfo(name="first_name")
    private String firstName;
    @ColumnInfo(name="last_name")
    private String lastName;

    private String email;
    private String address;

    @ColumnInfo(name="phone_number")
    private String phoneNumber;

    @ColumnInfo(name="on_specialty")
    private boolean onSpecialty;

    public Customer(Long id, String firstName, String lastName, String email, String address, String phoneNumber) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public boolean isOnSpecialty() {
        return onSpecialty;
    }

    public void setOnSpecialty(boolean onSpecialty) {
        this.onSpecialty = onSpecialty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
