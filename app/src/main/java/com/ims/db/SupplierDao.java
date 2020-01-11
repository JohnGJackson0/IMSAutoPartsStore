package com.ims.db;


import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface SupplierDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Supplier supplier);

    @Query("SELECT * FROM SUPPLIER")
    DataSource.Factory<Integer, Supplier> getSuppliers();
}
