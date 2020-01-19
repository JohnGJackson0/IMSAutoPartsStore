package com.ims.model;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Item item);

    @Query("SELECT * FROM ITEM ORDER BY PART_NUMBER")
    DataSource.Factory<Integer,Item> getAllItems();

    @Query("SELECT * FROM ITEM WHERE pending_order_quantity > 0")
    DataSource.Factory<Integer,Item> getPendingOrders();

    @Query("SELECT * FROM ITEM WHERE approved_order_quantity > 0")
    DataSource.Factory<Integer,Item>  getApprovedItems();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Item item);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItems(List<Item> updateList);
}
