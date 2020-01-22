package com.ims.main;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;

import com.ims.main.db.AppRepository;
import com.ims.model.Customer;
import com.ims.model.Item;
import com.ims.model.Supplier;

public class MainViewModel extends AndroidViewModel {
    private AppRepository mRepository;

    protected MainViewModel(Application app) {
        super(app);
        mRepository = new AppRepository(app);
    }

    protected void insertSupplier(Supplier a) {
        mRepository.insertSupplier(a);
    }

    protected void insertInventory(Item a) {
        mRepository.insertItem(a);
    }

    protected void updateItem(Item a){
        mRepository.updateItem(a);
    }

    public void insertCustomer(Customer customer) {
        mRepository.insertCustomer(customer);
    }
}