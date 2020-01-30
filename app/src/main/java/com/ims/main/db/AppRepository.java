package com.ims.main.db;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.ims.model.Customer;
import com.ims.model.CustomerDao;
import com.ims.model.Item;
import com.ims.model.ItemDao;
import com.ims.model.Order;
import com.ims.model.OrderDao;
import com.ims.model.OrderInventory;
import com.ims.model.OrderInventoryAndItemInfo;
import com.ims.model.OrderInventoryAndItemInfoDao;
import com.ims.model.OrderInventoryDao;
import com.ims.model.Sale;
import com.ims.model.SaleAndSaleInventory;
import com.ims.model.SaleAndSaleInventoryDao;
import com.ims.model.SaleDao;
import com.ims.model.SaleInventory;
import com.ims.model.SaleInventoryDao;
import com.ims.model.SpecialtyOrders;
import com.ims.model.SpecialtyOrdersDao;
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
    private OrderInventoryAndItemInfoDao mOrderInventoryAndItemInfoDao;
    private CustomerDao mCustomerDao;
    private SpecialtyOrdersDao mSpecialtyOrdersDao;
    private SaleDao mSaleDao;
    private SaleInventoryDao mSaleInventoryDao;
    private SaleAndSaleInventoryDao mSaleAndSaleInventoryDao;

    public AppRepository (Application app){
        mItemDao = AppDatabase.getAppDatabase(app).itemDao();
        mSupplierDao = AppDatabase.getAppDatabase(app).supplierDao();
        mOrderDao = AppDatabase.getAppDatabase(app).orderDao();
        mOrderInventoryDao = AppDatabase.getAppDatabase(app).orderInventoryDao();
        mOrderInventoryAndItemInfoDao = AppDatabase.getAppDatabase(app).orderInventoryAndItemInfoDao();
        mCustomerDao = AppDatabase.getAppDatabase(app).customerDao();
        mSpecialtyOrdersDao = AppDatabase.getAppDatabase(app).specialtyOrdersDao();
        mSaleDao = AppDatabase.getAppDatabase(app).saleDao();
        mSaleInventoryDao = AppDatabase.getAppDatabase(app).saleInventoryDao();
        mSaleAndSaleInventoryDao = AppDatabase.getAppDatabase(app).SaleAndSaleInventoryDao();
    }

    public DataSource.Factory getAllItems() { return mItemDao.getAllItems(); }

    public DataSource.Factory<Integer, Supplier> getAllSuppliers() { return mSupplierDao.getSuppliers(); }

    public DataSource.Factory<Integer,Customer> getCustomers() {
        return mCustomerDao.getCustomers();
    }

    public DataSource.Factory<Integer,Item> getPendingOrders() { return mItemDao.getPendingOrders(); }

    public DataSource.Factory<Integer, OrderInventory> getPendingInventoryOrders() {return mOrderInventoryDao.getPendingInventoryOrders();}

    public DataSource.Factory<Integer, SaleInventory> getSaleInventories(long id) {return mSaleInventoryDao.getSaleInventories(id);}

    public DataSource.Factory<Integer, OrderInventoryAndItemInfo> getOrderInventoryAndItemData(Long orderNumber){
        return mOrderInventoryAndItemInfoDao.getItemAndItemInfoFromOrderPaged(orderNumber);
    }
    public DataSource.Factory<Integer, SaleAndSaleInventory> getSaleAndInventory(long saleNumber){
        return mSaleAndSaleInventoryDao.getSaleAndSaleInventory(saleNumber);
    }

    public DataSource.Factory<Integer, Sale> getAllSales() {
        return mSaleDao.getSales();
    }

    public DataSource.Factory<Integer,Item> getApprovedItems(){
        return mItemDao.getApprovedItems();
    }
    public DataSource.Factory getAllOrdersInFinalizationState() {
        return mOrderDao.getEligibleOrdersForFinalization();
    }

    public DataSource.Factory<Integer,Order> getAllFinishedOrders() {
        return mOrderDao.getFinishedOrders();
    }

    public void updateItems(List<Item> updateList) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mItemDao.updateItems(updateList));
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
        myExecutor.execute(() -> mOrderInventoryDao.insertMultiple(a));
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

    public LiveData<Long> getCurrentFinalizationOrderNumber() {
        return mOrderDao.getCurrentFinalizationOrderNumber();
    }

    public void updateOrder(Order a) {
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(() -> mOrderDao.update(a));
    }

    public LiveData<Order> getNewestOrder() {
        return mOrderDao.getNewestOrder();
    }


    public LiveData<Order> getCurrentFinalizationOrder() {
        return mOrderDao.getCurrentFinalizationOrder();
    }

    public LiveData<Order> getCurrentSpecialtyOrder() {
        return mOrderDao.getCurrentSpecialtyOrder();
    }
    public void deleteOrder(Order order) {
        mOrderDao.deleteOrder(order);
    }

    public void removeAllFinalizationOrdersWhereNot(Long orderNumber) {
        mOrderDao.removeAllRecordsFromFinalizationWhereNot(orderNumber);
    }

    public void insertCustomer(Customer customer) {
        mCustomerDao.insert(customer);
    }

    public void insertSale(Sale sale) {
        mSaleDao.insert(sale);
    }

    public void insertSaleInventory(SaleInventory saleInventory) {
        mSaleInventoryDao.insert(saleInventory);
    }

    public void setOnSpecialtyOrder(long orderNumber) {
        // We need a specific query because we don't know which query will finish first
        // as they are in background threads. If the first query finished last it wont
        // effect the second.
        mOrderDao.removeAllRecordsFromSpecialtyWhereNot(orderNumber);
        mOrderDao.setOnSpecialtyOrder(orderNumber);
    }

    public void setOnSpecialtyCustomer(long customerNumber){
        mCustomerDao.setOnSpecialtyCustomer(customerNumber);
        mCustomerDao.removeAllRecordsFromSpecialtyWhereNot(customerNumber);
    }

    public LiveData<Customer> getCurrentSpecialtyCustomer() {
        return mCustomerDao.getCurrentSpecialtyCustomer();
    }

    public void insertSpecialtyOrder(SpecialtyOrders order){
        mSpecialtyOrdersDao.insert(order);
    }

    public void removeAllSpecialtyOrdersAndCustomers() {
        mOrderDao.removeAllSpecialtyOrders();
        mCustomerDao.removeAllSpecialtyCustomers();
    }
    public void insertSaleInventories(List<SaleInventory> mSaleInventory) {
        mSaleInventoryDao.insertInventories(mSaleInventory);
    }

    public void removeSaleInventories() {
        mSaleInventoryDao.removeSaleInventories();
    }

    public LiveData<Sale> getCurrentSale() {
        return mSaleDao.getCurrentSale();
    }

    public void setSale(Long saleNumber) {
        mSaleDao.removeAllRecordsFromOnSaleWhereNot(saleNumber);
        mSaleDao.setSale(saleNumber);
    }

}
