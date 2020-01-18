package com.ims.model;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
    void insertMulitple(List<OrderInventory> orderInventories);

}
