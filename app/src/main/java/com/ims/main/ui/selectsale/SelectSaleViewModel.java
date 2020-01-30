package com.ims.main.ui.selectsale;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Sale;

public class SelectSaleViewModel extends AndroidViewModel {
    private AppRepository mRepository;

    public SelectSaleViewModel(Application app){
        super(app);
        mRepository = new AppRepository(app);
    }

    public LiveData<PagedList<Sale>> getSales() {
        return new LivePagedListBuilder<>(
                mRepository.getAllSales(), 15)
                .build();
    }

    public void setSale(Long saleNumber){
        mRepository.setSale(saleNumber);
    }
}