package com.ims.main.ui.mainactivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.ims.db.AppRepository;
import com.ims.db.Item;
import com.ims.db.Supplier;

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
