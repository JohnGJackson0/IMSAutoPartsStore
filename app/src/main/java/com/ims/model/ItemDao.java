package com.ims.model;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Query("SELECT * FROM ITEM ORDER BY PART_NUMBER")
    DataSource.Factory<Integer,Item> getAllItems();

    @Query("SELECT * FROM ITEM WHERE pending_order_quantity > 0")
    DataSource.Factory<Integer,Item> getPendingOrders();

    @Query("SELECT * FROM ITEM WHERE approved_order_quantity > 0")
    DataSource.Factory<Integer,Item>  getApprovedItems();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Item item);
}
