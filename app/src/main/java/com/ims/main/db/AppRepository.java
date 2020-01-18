package com.ims.main.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;

import com.ims.model.Item;
import com.ims.model.ItemDao;
import com.ims.model.Order;
import com.ims.model.OrderDao;
import com.ims.model.OrderInventory;
import com.ims.model.OrderInventoryDao;
import com.ims.model.Supplier;
import com.ims.model.SupplierDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private ItemDao mItemDao;
    private SupplierDao mSupplierDao;
    private OrderDao mOrderDao;
    private OrderInventoryDao mOrderInventoryDao;

    public AppRepository (Application app){
        mItemDao = AppDatabase.getAppDatabase(app).itemDao();
        mSupplierDao = AppDatabase.getAppDatabase(app).supplierDao();
        mOrderDao = AppDatabase.getAppDatabase(app).orderDao();
        mOrderInventoryDao = AppDatabase.getAppDatabase(app).orderInventoryDao();
    }

    public DataSource.Factory getAllItems() { return mItemDao.getAllItems(); }

    public DataSource.Factory<Integer, Supplier> getAllSuppliers() { return mSupplierDao.getSuppliers(); }

    public DataSource.Factory<Integer,Item> getPendingOrders() { return mItemDao.getPendingOrders(); }

    public DataSource.Factory<Integer, OrderInventory> getPendingInventoryOrders() {return mOrderInventoryDao.getPendingInventoryOrders();}

    public DataSource.Factory<Integer,Item> getApprovedItems(){
        return mItemDao.getApprovedItems();
    }

    public void insertItem(Item a) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mItemDao.insert(a));
    }

    public void insertOrder(Order a) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mOrderDao.insert(a));
    }

    public void insertSupplier(Supplier a) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mSupplierDao.insert(a));
    }

    public void insertOrderInventory(OrderInventory a) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mOrderInventoryDao.insert(a));
    }

    public void insertOrderInventories(List<OrderInventory> a){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mOrderInventoryDao.insertMulitple(a));
    }

    public void updateItem(Item a){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mItemDao.update(a));
    }

    public void finalizeNewestOrder(){
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mOrderInventoryDao.setNewestOrderToFinalized());
        myExecutor.execute(() -> mOrderDao.setNewestOrderToFinalized());
    }

    public LiveData<Long> countPendingOrders() {
        return mOrderDao.countPendingOrders();
    }

    public void updateOrder(Order a) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mOrderDao.update(a));
    }

    public LiveData<Order> getNewestOrder() {
        return mOrderDao.getNewestOrder();
    }

}
