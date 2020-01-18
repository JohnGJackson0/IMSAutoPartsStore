package com.ims.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Order order);

    @Query("SELECT * FROM `ORDER` WHERE is_pending = 1 LIMIT 1")
    LiveData<Order> getNewestOrder();

    @Query("UPDATE `ORDER` SET is_pending = 0")
    void setNewestOrderToFinalized();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Order order);

    @Query("SELECT COUNT(is_pending) FROM 'ORDER' WHERE is_pending = 1")
    LiveData<Long> countPendingOrders();
}
