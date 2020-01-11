package com.ims.db;

import android.app.Application;
import android.arch.paging.DataSource;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private ItemDao mItemDao;
    private SupplierDao mSupplierDao;

    public AppRepository (Application app){
        mItemDao = AppDatabase.getAppDatabase(app).itemDao();
        mSupplierDao = AppDatabase.getAppDatabase(app).supplierDao();
    }

    public DataSource.Factory getAllItems() { return mItemDao.getAllItems(); }

    public void insertItem(Item a) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mItemDao.insert(a));
    }

    public void insertSupplier(Supplier a) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mSupplierDao.insert(a));
    }
}
