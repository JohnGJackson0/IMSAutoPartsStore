package com.ims.model;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Customer customer);

    @Query("SELECT * FROM 'CUSTOMER' ORDER BY ID")
    DataSource.Factory<Integer,Customer> getCustomers();
}
