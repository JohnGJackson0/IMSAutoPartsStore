package com.ims.main.ui.order.selectcustomer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Customer;

public class SelectCustomerViewModel extends AndroidViewModel {
    private AppRepository mRepository;

    public SelectCustomerViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public LiveData<PagedList<Customer>> getCustomers(){
        return new LivePagedListBuilder<>(
                mRepository.getCustomers(),15)
                .build();
    }

    public void setSpecialtyCustomer(long customerNumber) {
         mRepository.setOnSpecialtyCustomer(customerNumber);
    }

}
