package com.ims.model;


import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface OrderInventoryAndItemInfoDao {

    @Transaction
    @Query("SELECT * FROM orderInventory WHERE order_number = :orderNumber")
    DataSource.Factory<Integer,OrderInventoryAndItemInfo> getItemAndItemInfoFromOrderPaged(Long orderNumber);
}