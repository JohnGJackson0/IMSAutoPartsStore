package com.ims.main.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.ims.model.Item;
import com.ims.model.ItemDao;
import com.ims.model.Supplier;
import com.ims.model.SupplierDao;
import com.ims.main.db.converters.DateConverter;

@Database(entities = {Item.class, Supplier.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract ItemDao itemDao();
    public abstract SupplierDao supplierDao();

    protected static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ims-database")
                    .build();
        }
        return INSTANCE;
    }
}
