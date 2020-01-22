package com.ims.main.ui.suppliers;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.ims.main.db.AppRepository;
import com.ims.model.Supplier;

public class SupplierViewModel extends AndroidViewModel {
    private LiveData<PagedList<Supplier>> supplier;
    private AppRepository mRepository;

    protected SupplierViewModel(Application app) {
        super(app);
        mRepository = new AppRepository(app);
    }

    protected LiveData<PagedList<Supplier>> getAllSuppliers() {
        supplier = new LivePagedListBuilder<>(
                mRepository.getAllSuppliers(), 15)
                .build();
        return supplier;
    }
}
