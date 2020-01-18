package com.ims.main.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.ims.main.db.converters.BigDecimalConverter;
import com.ims.main.db.converters.DateConverter;
import com.ims.model.Item;
import com.ims.model.ItemDao;
import com.ims.model.Order;
import com.ims.model.OrderDao;
import com.ims.model.OrderInventory;
import com.ims.model.OrderInventoryDao;
import com.ims.model.Supplier;
import com.ims.model.SupplierDao;

@Database(entities = {Item.class, Supplier.class, Order.class, OrderInventory.class}, version = 1)
@TypeConverters({DateConverter.class, BigDecimalConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract ItemDao itemDao();
    public abstract SupplierDao supplierDao();
    public abstract OrderDao orderDao();
    public abstract OrderInventoryDao orderInventoryDao();

    protected static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ims-database")
                    .build();
        }
        return INSTANCE;
    }
}
