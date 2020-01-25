package com.ims.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface SpecialtyOrdersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SpecialtyOrders specialOrder);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(SpecialtyOrders specialOrder);
}
