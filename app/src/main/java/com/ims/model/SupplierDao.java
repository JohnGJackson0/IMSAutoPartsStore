package com.ims.model;


import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

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
