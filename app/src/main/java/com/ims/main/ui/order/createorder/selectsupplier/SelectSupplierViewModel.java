package com.ims.main.ui.order.createorder.selectsupplier;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Order;
import com.ims.model.Supplier;

public class SelectSupplierViewModel extends AndroidViewModel {
    private LiveData<PagedList<Supplier>> supplier;
    private AppRepository mRepository;

    public SelectSupplierViewModel(Application app) {
        super(app);
        mRepository = new AppRepository(app);
    }

    public LiveData<PagedList<Supplier>> getAllSuppliers() {
        supplier = new LivePagedListBuilder<>(
                mRepository.getAllSuppliers(), 15)
                .build();
        return supplier;
    }
    public LiveData<Order> getNewestOrder(){
        return mRepository.getNewestOrder();
    }

    public void updateOrder(Order a){
        mRepository.updateOrder(a);
    }
}
