package com.ims.model;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderInventoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderInventory orderInventory);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(OrderInventory orderInventory);

    @Query("SELECT * FROM ORDERINVENTORY WHERE is_pending = 1")
    DataSource.Factory<Integer,OrderInventory> getPendingInventoryOrders();

    @Query("UPDATE ORDERINVENTORY SET is_pending = 0")
    void setNewestOrderToFinalized();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMultiple(List<OrderInventory> orderInventories);
}
