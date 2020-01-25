package com.ims.model;

import androidx.lifecycle.LiveData;
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

    @Query("UPDATE 'CUSTOMER' SET  on_specialty = 1 WHERE id = :customerNumber")
    void setOnSpecialtyCustomer(long customerNumber);

    @Query("UPDATE 'CUSTOMER' SET on_specialty = 0 WHERE NOT id = :customerNumber")
    void removeAllRecordsFromSpecialtyWhereNot(long customerNumber);

    @Query("SELECT * FROM 'CUSTOMER' WHERE on_specialty = 1 LIMIT 1")
    LiveData<Customer> getCurrentSpecialtyCustomer();

    @Query("UPDATE 'CUSTOMER' SET on_specialty = 0")
    void removeAllSpecialtyCustomers();
}
