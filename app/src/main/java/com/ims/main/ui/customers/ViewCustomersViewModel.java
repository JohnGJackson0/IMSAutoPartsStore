package com.ims.main.ui.customers;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Customer;

public class ViewCustomersViewModel extends AndroidViewModel {
    private LiveData<PagedList<Customer>> customers;

    private AppRepository mRepository;

    public ViewCustomersViewModel(Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public LiveData<PagedList<Customer>> getCustomers() {
        customers = new LivePagedListBuilder<>(
                mRepository.getCustomers(), 15)
                .build();
        return customers;
    }
}
