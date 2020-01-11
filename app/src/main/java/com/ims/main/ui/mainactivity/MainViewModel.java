package com.ims.main.ui.mainactivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.ims.main.db.AppRepository;
import com.ims.model.Item;
import com.ims.model.Supplier;

public class MainViewModel extends AndroidViewModel {
    private LiveData<PagedList<Item>> items;
    private AppRepository mRepository;

    public MainViewModel(Application application) {
        super(application);
        mRepository = new AppRepository(application);
    }

    protected LiveData<PagedList<Item>> getAllItems() {
        items = new LivePagedListBuilder<>(
                mRepository.getAllItems(), 15)
                .build();
        return items;
    }

    protected void addItem(Item a) {
        mRepository.insertItem(a);
    }

    protected void addSupplier(Supplier a){
        mRepository.insertSupplier(a);
    }
}
