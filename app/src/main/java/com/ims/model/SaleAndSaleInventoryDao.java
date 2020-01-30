package com.ims.model;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface SaleAndSaleInventoryDao {
    @Transaction
    @Query("SELECT * FROM 'SALE' WHERE id = :saleId")
    DataSource.Factory<Integer,SaleAndSaleInventory> getSaleAndSaleInventory(long saleId);

}
