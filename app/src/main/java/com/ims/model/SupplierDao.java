package com.ims.model;


import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SupplierDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Supplier supplier);

    @Query("SELECT * FROM SUPPLIER")
    DataSource.Factory<Integer, Supplier> getSuppliers();

    @Query("SELECT * FROM SUPPLIER")
    LiveData<List<Supplier>> getSuppliersWithoutPagination();
}
