package com.ims.main.ui.order.specialtyorders;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ims.main.db.AppRepository;
import com.ims.model.Customer;
import com.ims.model.Order;
import com.ims.model.SpecialtyOrders;

public class SpecialtyOrdersViewModel extends AndroidViewModel {
    private AppRepository mRepository;

    public SpecialtyOrdersViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public LiveData<Order> getCurrentSpecialtyOrder() {
        return mRepository.getCurrentSpecialtyOrder();
    }

    public LiveData<Customer> getCurrentSpecialtyCustomer() {
        return mRepository.getCurrentSpecialtyCustomer();
    }

    public void insertSpecialtyOrder(SpecialtyOrders a) {
        mRepository.insertSpecialtyOrder(a);
    }

    public void removeAllSpecialtyOrders() {
        mRepository.removeAllSpecialtyOrdersAndCustomers();
    }
}