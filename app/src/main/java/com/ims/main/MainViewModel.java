package com.ims.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.ims.main.db.AppRepository;
import com.ims.model.Customer;
import com.ims.model.Item;
import com.ims.model.Sale;
import com.ims.model.SaleInventory;
import com.ims.model.Supplier;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private AppRepository mRepository;

    public MainViewModel(Application app) {
        super(app);
        mRepository = new AppRepository(app);
    }

    public void insertSupplier(Supplier a) {
        mRepository.insertSupplier(a);
    }

    public void insertInventory(Item a) {
        mRepository.insertItem(a);
    }

    public void updateItem(Item a){
        mRepository.updateItem(a);
    }

    public void insertCustomer(Customer customer) {
        mRepository.insertCustomer(customer);
    }


    public void removeSaleInventories() {
        mRepository.removeSaleInventories();
    }

    public void insertSale(Sale sale) {
        mRepository.insertSale(sale);
    }

    public void insertSaleInventories(List<SaleInventory> saleInventories) {
        mRepository.insertSaleInventories(saleInventories);
    }
}