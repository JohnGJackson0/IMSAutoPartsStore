package com.ims.db;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Query("SELECT * FROM ITEM ORDER BY PART_NUMBER")
    DataSource.Factory<Integer,Item> getAllItems();
}
