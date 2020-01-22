package com.ims.main.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ims.main.db.converters.BigDecimalConverter;
import com.ims.main.db.converters.DateConverter;
import com.ims.model.Customer;
import com.ims.model.CustomerDao;
import com.ims.model.Item;
import com.ims.model.ItemDao;
import com.ims.model.Order;
import com.ims.model.OrderDao;
import com.ims.model.OrderInventory;
import com.ims.model.OrderInventoryAndItemInfoDao;
import com.ims.model.OrderInventoryDao;
import com.ims.model.Supplier;
import com.ims.model.SupplierDao;

@Database(entities = {Item.class, Supplier.class, Order.class, OrderInventory.class, Customer.class}, version = 1)
@TypeConverters({DateConverter.class, BigDecimalConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract ItemDao itemDao();
    public abstract SupplierDao supplierDao();
    public abstract OrderDao orderDao();
    public abstract OrderInventoryDao orderInventoryDao();
    public abstract OrderInventoryAndItemInfoDao orderInventoryAndItemInfoDao();
    public abstract CustomerDao customerDao();

    protected static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ims-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
