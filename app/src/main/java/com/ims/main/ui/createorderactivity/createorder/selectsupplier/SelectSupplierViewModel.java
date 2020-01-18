package com.ims.main.ui.createorderactivity.createorder.selectsupplier;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Order;
import com.ims.model.Supplier;

public class SelectSupplierViewModel extends AndroidViewModel {
    private LiveData<PagedList<Supplier>> supplier;
    private AppRepository mRepository;

    protected SelectSupplierViewModel(Application app) {
        super(app);
        mRepository = new AppRepository(app);
    }

    protected LiveData<PagedList<Supplier>> getAllSuppliers() {
        supplier = new LivePagedListBuilder<>(
                mRepository.getAllSuppliers(), 15)
                .build();
        return supplier;
    }
    protected LiveData<Order> getNewestOrder(){
        return mRepository.getNewestOrder();
    }

    protected void updateOrder(Order a){
        mRepository.updateOrder(a);
    }
}
