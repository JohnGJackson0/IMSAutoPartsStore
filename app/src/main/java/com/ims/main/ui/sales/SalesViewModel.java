package com.ims.main.ui.sales;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Sale;
import com.ims.model.SaleInventory;

public class SalesViewModel extends AndroidViewModel {
    private AppRepository mRepository;

    public SalesViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    public LiveData<Sale> getSale() {
        return mRepository.getCurrentSale();
    }

    public LiveData<PagedList<SaleInventory>> getSaleInventories(Long id) {
       return new LivePagedListBuilder<>(
                mRepository.getSaleInventories(id), 15)
                .build();
    }
}
