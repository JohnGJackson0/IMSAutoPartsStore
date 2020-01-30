package com.ims.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.paging.DataSource;

@Dao
public interface SaleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Sale sale);

    @Query("SELECT * FROM SALE ORDER BY id")
    DataSource.Factory<Integer,Sale> getSales();

    @Query("SELECT * FROM SALE WHERE onSale = 1 LIMIT 1")
    LiveData<Sale> getCurrentSale();

    @Query("UPDATE 'SALE' SET onSale = 0 WHERE NOT id = :saleNumber")
    void removeAllRecordsFromOnSaleWhereNot(Long saleNumber);

    @Query("UPDATE 'SALE' SET onSale = 1 WHERE id = :saleNumber")
    void setSale(Long saleNumber);
}
