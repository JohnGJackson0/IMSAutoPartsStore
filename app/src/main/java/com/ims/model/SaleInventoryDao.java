package com.ims.model;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface SaleInventoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SaleInventory saleInventory);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInventories(List<SaleInventory> saleInventories);

    @Query("DELETE FROM saleinventory")
    void removeSaleInventories();

    @Query("SELECT * FROM saleinventory WHERE saleId = :saleId")
    DataSource.Factory<Integer,SaleInventory> getSaleInventories(Long saleId);
}
