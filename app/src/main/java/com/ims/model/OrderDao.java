package com.ims.model;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Order order);

    @Query("SELECT * FROM `ORDER` WHERE is_pending = 1 LIMIT 1")
    LiveData<Order> getNewestOrder();

    @Query("SELECT * FROM 'ORDER' WHERE ORDER_NUMBER = :orderNumber")
    Order getOrder(Long orderNumber);

    @Query("UPDATE `ORDER` SET is_pending = 0")
    void setNewestOrderToFinalized();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Order order);

    @Query("SELECT COUNT(is_pending) FROM 'ORDER' WHERE is_pending = 1")
    LiveData<Long> countPendingOrders();

    //@Query("UPDATE 'ORDER' SET on_finalization = 0")
    //void removeAllRecordsFromFinalization();

    @Query("SELECT ORDER_NUMBER FROM 'ORDER' WHERE on_finalization = 1 LIMIT 1")
    LiveData<Long> getCurrentFinalizationOrderNumber();

    @Query("SELECT * FROM 'ORDER' WHERE on_finalization = 1 LIMIT 1")
    LiveData<Order> getCurrentFinalizationOrder();

    @Query("SELECT * FROM 'ORDER' WHERE is_pending = 0 AND is_finished = 0")
    DataSource.Factory<Integer,Order> getEligibleOrdersForFinalization();

    @Delete
    void deleteOrder(Order order);

    @Query("UPDATE 'ORDER' SET on_finalization = 0 WHERE NOT ORDER_NUMBER = :orderNumber")
    void removeAllRecordsFromFinalizationWhereNot(Long orderNumber);

}
